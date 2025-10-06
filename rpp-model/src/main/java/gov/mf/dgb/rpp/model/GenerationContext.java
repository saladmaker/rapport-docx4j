package gov.mf.dgb.rpp.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.jstach.jstachio.JStachio;
import io.jstach.jstachio.spi.JStachioFactory;
import jakarta.xml.bind.JAXBException;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.docx4j.wml.Br;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase;
import org.docx4j.wml.R;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public final class GenerationContext {

    static final String ARAB_CONFIG = "arab.properties";

    static final String FRENCH_CONFIG = "french.properties";

    static final String FOOTER_TEXT = "footer.text";

    private static final ObjectFactory F = Context.getWmlObjectFactory();

    private static final JStachio JSTACHIO = JStachioFactory.builder().build();
    private Long imageCounter = 1L;
    private PageLayout layout;
    private final LanguageDirection direction;
    private final WordprocessingMLPackage document;
    private final Map<String, String> config;
    private final Year target;
    public static GenerationContext of(LanguageDirection direction, WordprocessingMLPackage doc,
            Map<String, String> mappers, Year target) {
        String configResourceName = (direction == LanguageDirection.LTR)
                ? FRENCH_CONFIG
                : ARAB_CONFIG;

        try (var raw = GenerationContext.class.getClassLoader().getResourceAsStream(configResourceName);
                var reader = new InputStreamReader(raw, StandardCharsets.UTF_8)) {
            Properties properties = new Properties();
            properties.load(reader);
            Map<String, String> config = properties.stringPropertyNames().stream()
                    .collect(Collectors.toUnmodifiableMap(
                            name -> name,
                            name -> mapVariable(properties.getProperty(name), mappers)));

            var context = new GenerationContext(direction, doc, config, target);
            context.applyFooterFromConfig(Writable.FOOTER_STYLE, FOOTER_TEXT);
            return context;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    private GenerationContext(LanguageDirection direction, WordprocessingMLPackage document,
            Map<String, String> config, Year target) {
        this.direction = direction;
        this.document = document;
        this.config = config;
        this.layout = PageLayout.PORTRAIT;
        this.target = target;

    }
    LanguageDirection direction(){
        return direction;
    }
    Year target(){
        return target;
    }
    void applyLayout(PageLayout layout) {
        if (this.layout == layout) return;

        Body body = document.getMainDocumentPart().getJaxbElement().getBody();
        SectPr currentSectPr = body.getSectPr();
        if (currentSectPr == null) {
            currentSectPr = F.createSectPr();
            body.setSectPr(currentSectPr);
        }

        // Create section break paragraph with COPIED section properties
        P breakerP = F.createP();
        PPr ppr = F.createPPr();
        SectPr sectBreak = F.createSectPr();

        // Copy current layout to the section break (this applies to the previous section)
        if (currentSectPr.getPgSz() != null) {
            sectBreak.setPgSz(currentSectPr.getPgSz());
        } else {
            sectBreak.setPgSz(this.layout.toPgSz());
        }
        if (currentSectPr.getPgMar() != null) {
            sectBreak.setPgMar(currentSectPr.getPgMar());
        } else {
            sectBreak.setPgMar(this.layout.toPgMar());
        }

        // IMPORTANT: Copy footer references to section break
        for (var ref : currentSectPr.getEGHdrFtrReferences()) {
            sectBreak.getEGHdrFtrReferences().add(ref);
        }

        SectPr.Type sectType = new SectPr.Type();
        sectType.setVal("nextPage");
        sectBreak.setType(sectType);

        ppr.setSectPr(sectBreak);
        breakerP.setPPr(ppr);
        document.getMainDocumentPart().addObject(breakerP);

        // Apply new layout to the main section properties
        currentSectPr.setPgSz(layout.toPgSz());
        currentSectPr.setPgMar(layout.toPgMar());

        this.layout = layout;
    }


    void insertScaledImage(byte[] image, String styleId, double scaleFactor, double distortionTolerance) {
        Objects.requireNonNull(image, "image cannot be null!");

        try {
            int imgWidthPx, imgHeightPx;
            try (ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(image))) {
                Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
                if (!readers.hasNext()) {
                    throw new IllegalArgumentException("Unsupported image format");
                }

                ImageReader reader = readers.next();
                try {
                    reader.setInput(iis);
                    imgWidthPx = reader.getWidth(0);
                    imgHeightPx = reader.getHeight(0);
                } finally {
                    reader.dispose();
                }
            }

            // Convert px -> TWIPs -> EMU (staying consistent with PageLayout units)
            double imgWidthTwips = imgWidthPx * 15;  // 96 DPI: px * 15 = TWIPs
            double imgHeightTwips = imgHeightPx * 15;

            double availableWidthTwips = layout.usableWidth().doubleValue();
            double availableHeightTwips = layout.usableHeight().doubleValue();

            double widthRatio = availableWidthTwips / imgWidthTwips;
            double heightRatio = availableHeightTwips / imgHeightTwips;

            double scale = Math.min(widthRatio, heightRatio) * scaleFactor;
            double widthScale = scale;
            double heightScale = scale;

            // Allow distortion if within tolerance
            if (Math.max(widthRatio, heightRatio) * scaleFactor / scale <= 1.0 + distortionTolerance) {
                widthScale = widthRatio * scaleFactor;
                heightScale = heightRatio * scaleFactor;
            }

            // Convert final TWIPs to EMU (TWIPs * 635 = EMU)
            long finalWidthEmu = Math.round(imgWidthTwips * widthScale * 635);
            long finalHeightEmu = Math.round(imgHeightTwips * heightScale * 635);

            // Create image with unique IDs
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(document, image);
            long docPrId = imageCounter++;
            Inline inline = imagePart.createImageInline("img", "embedded image", docPrId, (int)docPrId + 1,
                    finalWidthEmu, finalHeightEmu, false);

            // Create paragraph
            Drawing drawing = F.createDrawing();
            drawing.getAnchorOrInline().add(inline);
            P p = createStyledParagraph(styleId);
            R r = F.createR();
            r.getContent().add(drawing);
            p.getContent().add(r);
            document.getMainDocumentPart().addObject(p);

        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read image: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert image: " + e.getMessage(), e);
        }
    }


    String staticContent(String key){
        Objects.requireNonNull(key, "key can not be null!!");

        String content = config.get(key);
        if(null == content){
            throw new NoSuchElementException("no static content for key :" + key);
        }
        return direction.escape(content);
    }

    void addStaticContent(String styleId, String key){
        String text = staticContent(key);
        if(text.lines().count() > 1) {
            addContentWithManualBreak(styleId, text);
        }
        addContent(styleId, text);
    }
    void addContent(String styleId, String text){
        Objects.requireNonNull(text, "text can not be null!");
        Objects.requireNonNull(styleId, "style id can not be null!");
        document.getMainDocumentPart().addStyledParagraphOfText(styleId, text);
    }
    void addContentWithManualBreak(String styleId, String text) {
        Objects.requireNonNull(text, "text can not be null!");
        P p = createStyledParagraph(styleId);
        addTextWithManualBreaks(p, text);
        document.getMainDocumentPart().addObject(p);
    }
    void addRenderedContent(Object view){
        String viewContent = null;
        try {
            viewContent = JSTACHIO.execute(view);
            Object tableObject = XmlUtils.unmarshalString(viewContent);
            document.getMainDocumentPart().addObject(tableObject);
        } catch (JAXBException e) {
            System.out.println("generated view: \n" + viewContent);
            throw new RuntimeException(e);
        }

    }

    private static P createStyledParagraph(String styleId) {
        Objects.requireNonNull(styleId, "style id can not be null!");
        if(styleId.isBlank()){
            throw new IllegalArgumentException("style id can not be empty!!" + styleId);
        }
        P p = F.createP();
        PPr ppr = F.createPPr();
        PPrBase.PStyle ps = F.createPPrBasePStyle();
        ps.setVal(styleId);
        ppr.setPStyle(ps);
        p.setPPr(ppr);
        return p;
    }
    static P createNumberedParagraph(String text, BigInteger numId, BigInteger ilvl) {
        P p = F.createP();

        // Paragraph properties - NO pStyle reference
        PPr ppr = F.createPPr();

        // Numbering only
        PPrBase.NumPr numPr = F.createPPrBaseNumPr();
        PPrBase.NumPr.Ilvl ilvlEl = F.createPPrBaseNumPrIlvl();
        ilvlEl.setVal(ilvl);
        numPr.setIlvl(ilvlEl);
        PPrBase.NumPr.NumId numIdEl = F.createPPrBaseNumPrNumId();
        numIdEl.setVal(numId);
        numPr.setNumId(numIdEl);
        ppr.setNumPr(numPr);

        p.setPPr(ppr);

        // Text run
        R r = F.createR();
        Text t = F.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);

        return p;
    }
    void applyFooter(String styleId, String text) {
        Objects.requireNonNull(text, "footer text cannot be null!");
        Objects.requireNonNull(styleId, "style id can not be null!");

        try {
            MainDocumentPart documentPart = document.getMainDocumentPart();

            // Create footer part
            FooterPart footerPart = new FooterPart();

            // Create footer using existing paragraph creation logic
            Ftr footer = F.createFtr();
            P footerParagraph = createStyledParagraph(styleId);

            // Use extracted method for text with potential line breaks
            String escapedText = direction.escape(text);
            addTextWithManualBreaks(footerParagraph, escapedText);

            footer.getContent().add(footerParagraph);
            footerPart.setJaxbElement(footer);

            // Add footer part to document and get relationship ID
            String footerPartId = documentPart.addTargetPart(footerPart).getId();

            // Add footer reference to section properties
            Body body = documentPart.getJaxbElement().getBody();
            SectPr sectPr = body.getSectPr();
            if (sectPr == null) {
                sectPr = F.createSectPr();
                body.setSectPr(sectPr);
            }

            FooterReference footerRef = F.createFooterReference();
            footerRef.setId(footerPartId);
            footerRef.setType(HdrFtrRef.DEFAULT);
            sectPr.getEGHdrFtrReferences().add(footerRef);

        } catch (Exception e) {
            throw new RuntimeException("Failed to apply footer: " + e.getMessage(), e);
        }
    }


    void applyFooterFromConfig(String styleId, String configKey) {
        String text = staticContent(configKey);
        applyFooter(styleId, text);
    }

    private static String mapVariable(String configValue, Map<String, String> mappers) {
        if (configValue == null || mappers.isEmpty())
            return configValue;

        StringBuilder sb = new StringBuilder();
        Matcher matcher = Pattern.compile("\\$\\{([^}]+)}").matcher(configValue);
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = mappers.getOrDefault(key, matcher.group(0)); // fallback to original ${var}
            // Escape replacement to avoid issues with backslashes or $ signs
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    private static void addTextWithManualBreaks(P paragraph, String text) {
        Objects.requireNonNull(paragraph, "paragraph cannot be null!");
        Objects.requireNonNull(text, "text cannot be null!");

        String[] lines = text.split("\\r?\\n", -1);
        for (int i = 0; i < lines.length; i++) {
            R r = F.createR();
            Text t = F.createText();
            t.setValue(lines[i]);
            r.getContent().add(t);
            paragraph.getContent().add(r);

            if (i < lines.length - 1) {
                Br br = F.createBr();
                br.setType(STBrType.TEXT_WRAPPING);
                paragraph.getContent().add(br);
            }
        }
    }
}

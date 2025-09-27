package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.math.BigInteger;
import java.util.List;

@Prototype.Blueprint
interface LaMissionBlueprint extends Writable {

    String MISSION_1_TITLE_KEY = "section1.lamission.title.text";

    @Option.Required
    String intro();

    @Option.Singular
    List<Mission> missions();



    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);
        context.addStaticContent(HEADING_2_STYLE, MISSION_1_TITLE_KEY);
        context.addContentWithManualBreak(PARAGRAPH_STYLE, intro());

        writeMissions(document, context);

    }

    private void writeMissions(WordprocessingMLPackage document, GenerationContext context) {

        for (Mission m : missions()) {
            document.getMainDocumentPart().addObject(
                    GenerationContext.createNumberedParagraph(m.mission(), MULTI_LEVEL_LIST, BigInteger.ZERO)
            );

            for (String sm : m.subMissions()) {
                document.getMainDocumentPart().addObject(
                        GenerationContext.createNumberedParagraph(sm, MULTI_LEVEL_LIST, BigInteger.ONE)
                );
            }
        }
    }
}

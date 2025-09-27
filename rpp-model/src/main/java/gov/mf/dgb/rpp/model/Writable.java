package gov.mf.dgb.rpp.model;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.math.BigInteger;

public interface Writable {

    String HEADING_1_STYLE = "Heading1";
    String HEADING_2_STYLE = "Heading2";
    String HEADING_3_STYLE = "Heading3";
    BigInteger MULTI_LEVEL_LIST = BigInteger.valueOf(2);
    String IMG_STYLE = "imageStyle";
    String FOOTER_STYLE = "Footer";
    String STICKY_TITLE_STYLE = "NormalTitle";
    String NORMAL_STYLE = "Normal";
    String BOLD_STYLE = "NormalBold";
    String PARAGRAPH_STYLE = "Text";

    void write(WordprocessingMLPackage  document, GenerationContext context);

}
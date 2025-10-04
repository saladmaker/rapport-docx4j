package gov.mf.dgb.rpp.model;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import io.helidon.builder.api.Prototype;

@Prototype.Blueprint
interface AuSujetBlueprint extends Writable{
    String AUSJT_1_TITLE_KEY = "section1.ausujetprotefeuille.title.text";

    LaMission laMission();

    LeMinistere leMinistere();

    FichePortefeuille fichePortefeuille();

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);
        context.addStaticContent(HEADING_1_STYLE, AUSJT_1_TITLE_KEY);
        laMission().write(document, context);
        leMinistere().write(document, context);
        fichePortefeuille().write(document, context);
    }
    
}

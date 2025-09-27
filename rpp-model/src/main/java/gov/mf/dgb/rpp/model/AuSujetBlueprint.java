package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import io.helidon.builder.api.Prototype;

@Prototype.Blueprint
interface AuSujetBlueprint extends Writable{
    String AUSJT_1_TITLE_KEY = "section1.ausujetprotefeuille.title.text";

    @Option.Required
    LaMission laMission();

    @Option.Required
    LeMinistere leMinistere();

    @Option.Required
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

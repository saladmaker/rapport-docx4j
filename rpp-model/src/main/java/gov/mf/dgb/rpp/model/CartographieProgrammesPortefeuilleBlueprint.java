package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.util.List;

@Prototype.Blueprint(createEmptyPublic = false)
interface CartographieProgrammesPortefeuilleBlueprint extends Writable{

    String CARTOGRAPHIE_TITLE = "section1.cartographie.title.text";

    @Option.Singular
    List<ProgrammeStructure> programmeStructures();

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);
        context.addStaticContent(HEADING_2_STYLE, CARTOGRAPHIE_TITLE);
        ViewCartographie viewCartographie = ViewCartographie.of(context, this);
        context.addRenderedContent(viewCartographie);
    }
}

package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


@Prototype.Blueprint
@Prototype.CustomMethods(FichePortefeuilleSupport.class)
interface FichePortefeuilleBlueprint extends Writable {
    String FCHPORT_1_TITLE_KEY = "section1.ficheportefeuille.title.text";
    String FCHPORT_2_GEST_KEY = "section1.ficheportefeuille.gestionnaire.text";


    @Option.Singular
    List<RepartitionProgramme> versionBRepartitionProgrammes();

    @Option.Singular
    List<RepartitionProgramme> repartitionProgrammes();

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);

        context.addStaticContent(HEADING_2_STYLE, FCHPORT_1_TITLE_KEY);

        context.addStaticContent(BOLD_STYLE, FCHPORT_2_GEST_KEY);

        RepartitionProgrammesView tableView =
                RepartitionProgrammesView.of(repartitionProgrammes(), context.direction());
        context.addRenderedContent(tableView);

    }
}

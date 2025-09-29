package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

//todo make validation before building the prototype
//validate uniqueness of programmes in repartitions
@Prototype.Blueprint
@Prototype.CustomMethods(FichePortefeuilleSupport.class)
interface FichePortefeuilleBlueprint extends Writable {
    String FCHPORT_1_TITLE_KEY = "section1.ficheportefeuille.title.text";
    String FCHPORT_2_GEST_KEY = "section1.ficheportefeuille.gestionnaire.text";

    String FCHPORT_3_TABLE_1_TEXT = "section1.ficheportefeuille.table.1.title";
    String FCHPORT_4_TABLE_2_TEXT = "section1.ficheportefeuille.table.2.title";
    String FCHPORT_5_TABLE_3_TEXT = "section1.ficheportefeuille.table.3.title";

    @Option.Singular
    List<RepartitionProgramme> versionBRepartitionProgrammes();

    @Option.Singular
    List<RepartitionProgramme> repartitionProgrammes();

    @Option.Singular
    List<RepartitionCentreResponsabilite> repartitionProgrammeCentreResps();

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);

        context.addStaticContent(HEADING_2_STYLE, FCHPORT_1_TITLE_KEY);

        context.addStaticContent(BOLD_STYLE, FCHPORT_2_GEST_KEY);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_3_TABLE_1_TEXT);
        RepartitionProgrammesView versionBRepartitions =
                RepartitionProgrammesView.of(repartitionProgrammes(), context.direction());
        context.addRenderedContent(versionBRepartitions);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_4_TABLE_2_TEXT);
        RepartitionProgrammesView repartitionProgrammesTable =
                RepartitionProgrammesView.of(repartitionProgrammes(), context.direction());
        context.addRenderedContent(repartitionProgrammesTable);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_5_TABLE_3_TEXT);
        RepartitionProgrammesCentreResponsabiliteView repartitionProgrammesCentreResponsabiliteTable =
                RepartitionProgrammesCentreResponsabiliteView.of(repartitionProgrammeCentreResps(), context.direction());
        context.addRenderedContent(repartitionProgrammesCentreResponsabiliteTable);


    }
}

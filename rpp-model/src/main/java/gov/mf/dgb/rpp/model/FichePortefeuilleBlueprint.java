package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

//todo
//augment prototypes instances with document wide variable like Annee(1,2,3,4,5)
@Prototype.Blueprint(decorator = FichePortefeuilleSupport.BuilderDecorator.class)
@Prototype.CustomMethods(FichePortefeuilleSupport.class)
interface FichePortefeuilleBlueprint extends Writable {

    String FCHPORT_1_TITLE_KEY = "section1.ficheportefeuille.title.text";
    String FCHPORT_2_GEST_KEY = "section1.ficheportefeuille.gestionnaire.text";

    String FCHPORT_3_TABLE_1_TEXT = "section1.ficheportefeuille.table.1.title";
    String FCHPORT_4_TABLE_2_TEXT = "section1.ficheportefeuille.table.2.title";
    String FCHPORT_5_TABLE_3_TEXT = "section1.ficheportefeuille.table.3.title";
    String FCHPORT_6_TABLE_4_TEXT = "section1.ficheportefeuille.table.4.title";
    String FCHPORT_7_TABLE_5_TEXT = "section1.ficheportefeuille.table.5.title";
    String FCHPORT_8_TABLE_6_TEXT = "section1.ficheportefeuille.table.6.title";
    String FCHPORT_9_TABLE_7_TEXT = "section1.ficheportefeuille.table.7.title";

    @Option.Singular
    List<RepartitionProgramme> versionBRepartitionProgrammes();

    @Option.Singular
    List<RepartitionProgramme> repartitionProgrammes();

    @Option.Singular
    List<RepartitionCentreResponsabilite> repartitionProgrammeCentreResps();

    @Option.Singular
    List<RepartitionTitre> repartitionProgrammesTitres();

    @Option.Singular
    List<RepartitionCentreResponsabiliteTitre> repartitionPortefeuilleCentreResponTitres();

    @Option.Singular
    List<Evolution> evolutionDepensesProgrammes();

    @Option.Access("")
    List<Evolution> evolutionPostesServices();

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);

        context.addStaticContent(HEADING_2_STYLE, FCHPORT_1_TITLE_KEY);

        context.addStaticContent(BOLD_STYLE, FCHPORT_2_GEST_KEY);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_3_TABLE_1_TEXT);
        ViewRepartitionProgrammes versionBRepartitions =
                ViewRepartitionProgrammes.of(repartitionProgrammes(), context.direction());
        context.addRenderedContent(versionBRepartitions);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_4_TABLE_2_TEXT);
        ViewRepartitionProgrammes repartitionProgrammesTable =
                ViewRepartitionProgrammes.of(repartitionProgrammes(), context.direction());
        context.addRenderedContent(repartitionProgrammesTable);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_5_TABLE_3_TEXT);
        ViewRepartitionProgrammesCentreResponsabilite repartitionProgrammesCentreResponsabiliteTable =
                ViewRepartitionProgrammesCentreResponsabilite.of(repartitionProgrammeCentreResps(), context.direction());
        context.addRenderedContent(repartitionProgrammesCentreResponsabiliteTable);

        context.applyLayout(PageLayout.LANDSCAPE);
        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_6_TABLE_4_TEXT);
        ViewRepartitionTitre viewRepartitionTitre =
                ViewRepartitionTitre.of(context, ViewType.PROGRAMME, repartitionProgrammesTitres());
        context.addRenderedContent(viewRepartitionTitre);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_7_TABLE_5_TEXT);
        ViewCentreResponsabiliteTitre repartitionPortefeuilleCentreRespTitresView =
                ViewCentreResponsabiliteTitre.of(context, repartitionPortefeuilleCentreResponTitres());
        context.addRenderedContent(repartitionPortefeuilleCentreRespTitresView);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_8_TABLE_6_TEXT);
        ViewEvolution evolutionDepensesProgrammes =
                ViewEvolution.of(context, ViewType.PROGRAMME, evolutionDepensesProgrammes());
        context.addRenderedContent(evolutionDepensesProgrammes);

        context.addStaticContent(STICKY_TITLE_STYLE, FCHPORT_9_TABLE_7_TEXT);
        ViewEvolutionPostesCentreResponsabilite evolutionPostesCentreResponsabilite =
                ViewEvolutionPostesCentreResponsabilite.of(context, evolutionPostesServices());
        context.addRenderedContent(evolutionPostesCentreResponsabilite);
    }
    default void writeTable(){
    }
}

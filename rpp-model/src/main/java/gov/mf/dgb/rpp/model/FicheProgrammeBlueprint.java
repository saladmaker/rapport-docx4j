package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.util.List;

@Prototype.Blueprint
interface FicheProgrammeBlueprint extends Writable {

    String FCHPROG_1_TITLE_KEY = "section2.planification.title.1.text";
    String FCHPROG_2_SUB_TITLE_1_KEY = "section2.planification.title.2.text";
    String FCHPORG_3_SUB_TITLE_2_KEY = "section2.planification.title.3.text";
    String FCHPROG_4_SUB_TITLE_3_KEY = "section2.ficheprogramme.title.1.text";
    String FCHPROG_5_STICKY_TITLE_1_KEY = "section2.ficheprogramme.title.2.text";
    String FCHPROG_6_STICKY_TITLE_2_KEY = "section2.ficheprogramme.title.3.text";
    String FCHPROG_7_TABLE_3_TEXT = "section2.ficheprogramme.table.3.title";
    String FCHPROG_8_TABLE_4_TEXT = "section2.ficheprogramme.table.4.title";
    String FCHPROG_9_TABLE_5_TEXT = "section2.ficheprogramme.table.5.title";

    String ETAT_COMPLEMETAIRE_HEADING = "etat.complementaire.title";
    String ETAT_COMPLEMENTAIRE_POSTES_SALAIRE = "etat.complementaire.evolution.postes.salariale.table.title";
    String ETAT_COMPLEMANTAIRE_EVOLUTION_DEPENSES_POST = "etat.complementaire.evolution.depenses.pricipaux.ost.title";
    String ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_TYPE_OST = "etat.complementaire.evolution.depenses.type.ost.title";

    //make it augmented by builder decorator
    @Option.Required
    int counter();

    String name();

    String gestionnaire();

    @Option.Singular
    List<RepartitionCentreResponsabiliteTitre> repartitionProgrammeCentreRespTitre();

    @Option.Singular
    List<RepartitionTitre> repartitionSousProgrammeTitre();

    @Option.Singular
    List<Evolution> evolutionDepenseSousProgramme();

    @Option.Singular
    List<Projet> projetEnCours();

    @Option.Singular
    List<Projet> GPEEnCours();

    @Option.Singular
    List<PostesOuvertMassSalarial> evolutionPostOuvertMassSalarials();

    @Option.Singular
    List<Evolution> evolutionDepensesOrganismesSousTutelle();

    @Option.Singular
    List<Evolution> evolutionDepenseOSTPartType();

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);

        //heading 1 formatted
        context.addFormattedStaticContent(HEADING_1_STYLE, FCHPROG_1_TITLE_KEY, counter());

        //heading 2
        context.addStaticContent(HEADING_2_STYLE, FCHPROG_2_SUB_TITLE_1_KEY);

        //heading 3 formatted
        context.addFormattedStaticContent(HEADING_3_STYLE, FCHPORG_3_SUB_TITLE_2_KEY, counter(), name());

        context.applyLayout(PageLayout.LANDSCAPE);
        //heading 2
        context.addStaticContent(HEADING_2_STYLE, FCHPROG_4_SUB_TITLE_3_KEY);

        //nom du programme
        context.addFormattedStaticContent(STICKY_TITLE_STYLE, FCHPROG_5_STICKY_TITLE_1_KEY, counter(), name());

        //gestionnaire
        context.addFormattedStaticContent(STICKY_TITLE_STYLE, FCHPROG_6_STICKY_TITLE_2_KEY, gestionnaire());

        //add repartition centre de responsabilite- titre
        context.addFormattedStaticContent(HEADING_3_STYLE, FCHPROG_7_TABLE_3_TEXT, counter(), context.annee());
        ViewCentreResponsabiliteTitre viewCentreResponsabiliteTitre =
                ViewCentreResponsabiliteTitre.of(context, repartitionProgrammeCentreRespTitre());
        context.addRenderedContent(viewCentreResponsabiliteTitre);

        //add repartition sous-programme titre
        context.addFormattedStaticContent(HEADING_3_STYLE, FCHPROG_8_TABLE_4_TEXT, counter(), context.annee());
        ViewRepartitionSousprogrammesTitre viewRepartitionSousprogrammesTitre =
                ViewRepartitionSousprogrammesTitre.of(context, repartitionSousProgrammeTitre());
        context.addRenderedContent(viewRepartitionSousprogrammesTitre);

        //add evolution depenses par sous-programme
        context.addStaticContent(HEADING_3_STYLE, FCHPROG_9_TABLE_5_TEXT);
        ViewEvolutionDepensesSousprogrammes viewEvolutionDepensesSousprogrammes =
                ViewEvolutionDepensesSousprogrammes.of(context, evolutionDepenseSousProgramme());
        context.addRenderedContent(viewEvolutionDepensesSousprogrammes);
        System.out.println(evolutionDepenseSousProgramme());




        /*===== États complémentaires ====*/
        context.addStaticContent(HEADING_2_STYLE, ETAT_COMPLEMETAIRE_HEADING);

        //postes ouverts et mass salariale
        context.addStaticContent(HEADING_3_STYLE, ETAT_COMPLEMENTAIRE_POSTES_SALAIRE);
        ViewEvolutionPostesOuvertMassSalarial viewEvolutionPostesOuvertMassSalarial =
                ViewEvolutionPostesOuvertMassSalarial.of(context, evolutionPostOuvertMassSalarials());
        context.addRenderedContent(viewEvolutionPostesOuvertMassSalarial);

        //repartition titre sous programme


        //principaux organismes sous tutelle
        context.addStaticContent(STICKY_TITLE_STYLE, ETAT_COMPLEMANTAIRE_EVOLUTION_DEPENSES_POST);
        ViewEvolutionDepensesPrincipauxOrganismes viewEvolutionDepensesPrincipauxOrganismes =
                ViewEvolutionDepensesPrincipauxOrganismes.of(context, evolutionDepensesOrganismesSousTutelle());
        context.addRenderedContent(viewEvolutionDepensesPrincipauxOrganismes);

        context.addStaticContent(STICKY_TITLE_STYLE, ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_TYPE_OST);
        ViewEvolutionDepensesOrganismesSTParType viewEvolutionDepensesOrganismesSTParType =
                ViewEvolutionDepensesOrganismesSTParType.of(context, evolutionDepenseOSTPartType());
        context.addRenderedContent(viewEvolutionDepensesOrganismesSTParType);


    }
}

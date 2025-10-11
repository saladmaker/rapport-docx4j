package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.util.List;

@Prototype.Blueprint(createEmptyPublic = false)
interface FicheProgrammeBlueprint extends Writable {

    String FCHPROG_1_TITLE_KEY = "section2.planification.title.1.text";
    String FCHPROG_2_SUB_TITLE_1_KEY = "section2.planification.title.2.text";
    String FCHPORG_3_SUB_TITLE_2_KEY = "section2.planification.title.3.text";
    String FCHROG_PLANIFICATION_JUSTIFICATION_TITLE = "section2.planification.justification.title";

    String FCHPROG_4_SUB_TITLE_3_KEY = "section2.ficheprogramme.title.1.text";
    String FCHPROG_5_STICKY_TITLE_1_KEY = "section2.ficheprogramme.title.2.text";
    String FCHPROG_6_STICKY_TITLE_2_KEY = "section2.ficheprogramme.title.3.text";
    String FCHPROG_7_TABLE_3_TEXT = "section2.ficheprogramme.table.3.title";
    String FCHPROG_8_TABLE_4_TEXT = "section2.ficheprogramme.table.4.title";
    String FCHPROG_9_TABLE_5_TEXT = "section2.ficheprogramme.table.5.title";

    String PRINCIP_PROJET_HEADING = "princip.projet";
    String PRINCIP_PROJET_EN_COURS = "princip.projet.en.cours.title";


    String ETAT_COMPLEMENTAIRE_HEADING = "etat.complementaire.title";
    String ETAT_COMPLEMENTAIRE_POSTES_SALAIRE = "etat.complementaire.evolution.postes.salariale.table.title";
    String ETAT_COMPLEMANTAIRE_EVOLUTION_DEPENSES_POST = "etat.complementaire.evolution.depenses.pricipaux.ost.title";
    String ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_TYPE_OST = "etat.complementaire.evolution.depenses.type.ost.title";
    String ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_TERRITOITRE = "etat.complementaire.evolution.depenses.territoire.title";
    String ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_FINANCEMENT = "etat.complementaire.evolution.depenses.source.financement.title";

    //todo make it augmented by builder decorator
    @Option.Required
    int counter();

    String name();

    String gestionnaire();

    @Option.Singular
    List<String> axesStrategiques();
               //exesStrategiques

    @Option.Singular
    List<String> objectivesStrategiques();

    @Option.Singular
    List<String> initiativesImportantes();

    @Option.Singular
    List<String> justificationDepensePersonnel();

    @Option.Singular
    List<String> justificationDepenseFonctionnementServices();

    @Option.Singular
    List<String> justificationDepenseInvestissements();

    @Option.Singular
    List<String> justificationDepenseTransferts();

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

    @Option.Singular
    List<Evolution> evolutionDepenseTerritoire();

    @Option.Singular
    List<Evolution> evolutionDepenseSourceFinancement();


    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);

        //heading 1 formatted
        context.addFormattedStaticContent(HEADING_1_STYLE, FCHPROG_1_TITLE_KEY, counter());

        //heading 2
        context.addStaticContent(HEADING_2_STYLE, FCHPROG_2_SUB_TITLE_1_KEY);

        //heading 3 formatted
        context.addFormattedStaticContent(HEADING_3_STYLE, FCHPORG_3_SUB_TITLE_2_KEY, counter(), name());
        ViewFicheProgramme viewFicheProgramme = ViewFicheProgramme.of(context, this);
        context.addRenderedContent(viewFicheProgramme);

        context.addFormattedStaticContent(HEADING_2_STYLE, FCHROG_PLANIFICATION_JUSTIFICATION_TITLE, context.annee());
        ViewJustificationDepense viewJustificationDepense = ViewJustificationDepense.of(context, this);
        context.addRenderedContent(viewJustificationDepense);

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

        /*===== États complémentaires ====*/
        context.addStaticContent(HEADING_2_STYLE, ETAT_COMPLEMENTAIRE_HEADING);

        //postes ouverts et mass salariale
        context.addStaticContent(HEADING_3_STYLE, ETAT_COMPLEMENTAIRE_POSTES_SALAIRE);
        ViewEvolutionPostesOuvertMassSalarial viewEvolutionPostesOuvertMassSalarial =
                ViewEvolutionPostesOuvertMassSalarial.of(context, evolutionPostOuvertMassSalarials());
        context.addRenderedContent(viewEvolutionPostesOuvertMassSalarial);

        //principaux organismes sous tutelle
        context.addStaticContent(STICKY_TITLE_STYLE, ETAT_COMPLEMANTAIRE_EVOLUTION_DEPENSES_POST);
        ViewEvolutionDepensesPrincipauxOrganismes viewEvolutionDepensesPrincipauxOrganismes =
                ViewEvolutionDepensesPrincipauxOrganismes.of(context, evolutionDepensesOrganismesSousTutelle());
        context.addRenderedContent(viewEvolutionDepensesPrincipauxOrganismes);

        //depense par type OST
        context.addStaticContent(STICKY_TITLE_STYLE, ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_TYPE_OST);
        ViewEvolutionDepensesOrganismesSTParType viewEvolutionDepensesOrganismesSTParType =
                ViewEvolutionDepensesOrganismesSTParType.of(context, evolutionDepenseOSTPartType());
        context.addRenderedContent(viewEvolutionDepensesOrganismesSTParType);

        //depense par territoire
        context.addStaticContent(HEADING_3_STYLE, ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_TERRITOITRE);
        ViewEvolutionDepensesTerritoires viewEvolutionDepensesTerritoires =
                ViewEvolutionDepensesTerritoires.of(context, evolutionDepenseTerritoire());
        context.addRenderedContent(viewEvolutionDepensesTerritoires);

        //source de financement
        context.addStaticContent(HEADING_3_STYLE, ETAT_COMPLEMENTAIRE_EVOLUTION_DEPENSES_FINANCEMENT);
        ViewEvolutionDepenseSourceFinancement viewEvolutionDepenseSourceFinancement =
                ViewEvolutionDepenseSourceFinancement.of(context, evolutionDepenseSourceFinancement());
        context.addRenderedContent(viewEvolutionDepenseSourceFinancement);

        context.addFormattedStaticContent(HEADING_2_STYLE, PRINCIP_PROJET_HEADING, counter());
        context.addStaticContent(HEADING_3_STYLE, PRINCIP_PROJET_EN_COURS);
        ViewProjetEnCours viewProjetEnCours = ViewProjetEnCours.of(context, projetEnCours());
        context.addRenderedContent(viewProjetEnCours);
    }
}

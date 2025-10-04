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

    @Override
    default void write(WordprocessingMLPackage document, GenerationContext context) {
        context.applyLayout(PageLayout.PORTRAIT);

        //heading 1 formatted
        String titleFormat = context.staticContent(FCHPROG_1_TITLE_KEY);
        String title = titleFormat.formatted(counter());
        context.addContent(HEADING_1_STYLE, title);

        //heading 2
        context.addStaticContent(HEADING_2_STYLE, FCHPROG_2_SUB_TITLE_1_KEY);

        //heading 3 formatted
        String presentationFormat = context.staticContent(FCHPORG_3_SUB_TITLE_2_KEY);
        String presentation = presentationFormat.formatted(counter(), name());
        context.addContent(HEADING_3_STYLE, presentation);

        //heading 2
        context.addStaticContent(HEADING_2_STYLE, FCHPROG_4_SUB_TITLE_3_KEY);

        //nom du programme
        String stickyNomProgrammeFormat = context.staticContent(FCHPROG_5_STICKY_TITLE_1_KEY);
        var stickyNomProgramme = stickyNomProgrammeFormat.formatted(context, name());
        context.addContent(STICKY_TITLE_STYLE, stickyNomProgramme);

        //gestionnaire
        String stickyGestionnaireFromat = context.staticContent(FCHPROG_6_STICKY_TITLE_2_KEY);
        var stickyGestionaaire = stickyGestionnaireFromat.formatted(gestionnaire());
        context.addContent(STICKY_TITLE_STYLE, stickyGestionaaire);

    }
}

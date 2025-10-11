package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewProjetEnCours extends ViewProjetEnCoursBase{
    String HEADER_TITLE = "princip.projet.en.cours.header.title";//variable(projet, grand projet de l'etat
    String HEADER_AE_REEVALUATION = "princip.projet.en.cours.header.ae.reevaluation";
    String HEADER_CP_ANNEE = "princip.projet.en.cours.header.cp.annee";
    String HEADER_CP_ANNEE_PLUS_1 = "princip.projet.en.cours.header.cp.annee.1";
    String HEADER_CP_ANNEE_PLUS_2 = "princip.projet.en.cours.header.cp.annee.2";

    default List<ProjetView> projets(){
        return delegates().stream()
                .map(e-> new ProjetView(context(), e))
                .toList();
    }
    static ViewProjetEnCours of(GenerationContext context, List<Projet> delegates){
        return switch (context.direction()){
            case LTR -> new ViewProjetEnCoursFR(context, delegates);
            case RTL -> new ViewProjetEnCoursAR(context, delegates);
        };
    }
    @JStache(path = "templates/section2/projet.en.cours.fr.mustache")
    record ViewProjetEnCoursFR(GenerationContext context, List<Projet> delegates)
            implements ViewProjetEnCours{}
    @JStache(path = "templates/section2/projet.en.cours.ar.mustache")
    record ViewProjetEnCoursAR(GenerationContext context, List<Projet> delegates)
            implements ViewProjetEnCours{}

    @Override
    default String headerTitle() {
        return context().staticContent(HEADER_TITLE);
    }

    @Override
    default String headerCPAnneeFormat() {
        return context().staticContent(HEADER_CP_ANNEE);
    }

    @Override
    default String headerCPAnneePlus1Format() {
        return context().staticContent(HEADER_CP_ANNEE_PLUS_1);
    }

    @Override
    default String headerCPAnneePlus2Format() {
        return context().staticContent(HEADER_CP_ANNEE_PLUS_2);
    }

    @Override
    default String headerAEReevaluationFormat() {
        return context().staticContent(HEADER_AE_REEVALUATION);
    }
}

package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.ToLongFunction;

sealed interface ViewNouveauProjets {
    String HEADER_AE = "princip.projet.nouveau.projet.header.ae";
    String HEADER_CP_ANNEE = "princip.projet.nouveau.projet.header.cp.annee";
    String HEADER_CP_ANNEE_PLUS_1 = "princip.projet.nouveau.projet.header.cp.annee.1";
    String HEADER_CP_ANNEE_PLUS_2 = "princip.projet.nouveau.projet.header.cp.annee.2";

    String headerTitle();

    GenerationContext context();

    List<NouveauProjet> delegates();

    default List<NouveauProjetView> projets(){
        return delegates().stream()
                .map(NouveauProjetView::new)
                .toList();
    }
    static ViewNouveauProjets ofProjet(GenerationContext context, List<NouveauProjet> projets){
        return switch (context.direction()){
            case LTR -> new PlainNouveauProjetView.PlainNouveauProjetViewFR(context, projets);
            case RTL -> new PlainNouveauProjetView.PlainNouveauProjetViewAR(context, projets);
        };
    }
    static ViewNouveauProjets ofGPE(GenerationContext context, List<NouveauProjet> projets){
        return switch (context.direction()){
            case LTR -> new NouveauGPEView.NouveauGPEViewFR(context, projets);
            case RTL -> new NouveauGPEView.NouveauGPEViewAR(context, projets);
        };
    }
    sealed interface PlainNouveauProjetView extends ViewNouveauProjets{
        String HEADER_TITLE = "princip.projet.nouveau.projet.projet.header.title";

        @Override
        default String headerTitle(){
            return context().staticContent(HEADER_TITLE);
        }

        record PlainNouveauProjetViewAR(GenerationContext context, List<NouveauProjet> delegates) implements PlainNouveauProjetView {
        }
        @JStache(path = "templates/section2/projet.nouveau.fr.mustache")
        record PlainNouveauProjetViewFR(GenerationContext context, List<NouveauProjet> delegates) implements PlainNouveauProjetView {
        }
    }
    sealed interface NouveauGPEView extends ViewNouveauProjets{
        String HEADER_TITLE = "princip.projet.nouveau.projet.gpe.header.title";

        @Override
        default String headerTitle(){
            return context().staticContent(HEADER_TITLE);
        }

        record NouveauGPEViewAR(GenerationContext context, List<NouveauProjet> delegates) implements NouveauGPEView {
        }
        @JStache(path = "templates/section2/projet.nouveau.fr.mustache")
        record NouveauGPEViewFR(GenerationContext context, List<NouveauProjet> delegates) implements NouveauGPEView {
        }
    }

    default String headerAE(){
        return context().formattedStaticContent(HEADER_AE, context().annee());
    }

    default String headerCPAnnee(){
        return context().formattedStaticContent(HEADER_CP_ANNEE, context().annee());
    }
    default String headerCPAnneePlus1(){
        return context().formattedStaticContent(HEADER_CP_ANNEE_PLUS_1, context().anneePlus1());
    }
    default String headerCPAnneePlus2(){
        return context().formattedStaticContent(HEADER_CP_ANNEE_PLUS_2, context().anneePlus2());
    }
    default String totalCoutEstGlobal(){
        return formattedTotal(NouveauProjetBlueprint::couteEstGlobal);
    }
    default String totalAE(){
        return formattedTotal(NouveauProjetBlueprint::AEDemande);
    }
    default String totalCPAnnee(){
        return formattedTotal(NouveauProjetBlueprint::CPAnnee);
    }
    default String totalCPAnneePlus1(){
        return formattedTotal(NouveauProjetBlueprint::CPAnneePlus1);
    }
    default String totalCPAnneePlus2(){
        return formattedTotal(NouveauProjetBlueprint::CPAnneePlus2Suivante);
    }
    default String totalCharges(){
        return formattedTotal(NouveauProjetBlueprint::chargesRecurAnnuellMoyennesPrevus);
    }

    default <T> String formattedTotal(ToLongFunction<NouveauProjetBlueprint> mapper){
        var total = delegates().stream()
                .mapToLong(mapper)
                .sum();
        return NumberFormatter.format(total);
    }
    record NouveauProjetView(NouveauProjet delegate){

        String name(){
            return delegate.name();
        }

        String dateDebut(){
            return delegate.dateDebut().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        String dateFin(){
            return delegate.dateFin().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        String couteEstGlobal(){
            return NumberFormatter.format(delegate.couteEstGlobal());
        }

        String AEDemande(){
            return NumberFormatter.format(delegate.AEDemande());
        }

        String CPAnnee(){
            return NumberFormatter.format(delegate.CPAnnee());
        }

        String CPAnneePlus1(){
            return NumberFormatter.format(delegate.CPAnneePlus1());
        }

        String CPAnneePlus2Suivante(){
            return NumberFormatter.format(delegate.CPAnneePlus2Suivante());
        }

        String chargesRecurAnnuellMoyennesPrevus(){
            return NumberFormatter.format(delegate.chargesRecurAnnuellMoyennesPrevus());
        }
    }

}

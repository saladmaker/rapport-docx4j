package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.ToLongFunction;

sealed interface ViewProjetEnCours extends Viewable {

    String HEADER_AE_REEVALUATION = "princip.projet.en.cours.header.ae.reevaluation";

    String CP_ANNEE = "princip.projet.en.cours.header.cp.annee";

    String CP_ANNEE_PLUS_1 = "princip.projet.en.cours.header.cp.annee.1";

    String CP_ANNEE_PLUS_2 = "princip.projet.en.cours.header.cp.annee.2";

    List<Projet> delegates();

    String headerTitle();

    default List<ProjetView> projets() {
        return delegates().stream()
                .map(e -> new ProjetView(context(), e))
                .toList();
    }
    static ViewProjetEnCours ofProjet(GenerationContext context, List<Projet> projets){
        return switch (context.direction()){
            case LTR -> new ViewPlainProjetEnCours.ViewPlainProjetEnCoursFR(context, projets);
            case RTL -> new ViewPlainProjetEnCours.ViewPlainProjetEnCoursAR(context, projets);
        };
    }
    static ViewProjetEnCours ofGPE(GenerationContext context, List<Projet> projets){
        return switch (context.direction()){
            case LTR -> new GPEEnCours.GPEEnCoursFR(context, projets);
            case RTL -> new GPEEnCours.GPEEnCoursAR(context, projets);
        };
    }


    sealed interface ViewPlainProjetEnCours extends ViewProjetEnCours {
        String HEADER_TITLE = "princip.projet.en.cours.projet.header.title";

        @Override
        default String headerTitle() {
            return context().staticContent(HEADER_TITLE);
        }

        @JStache(path = "templates/section2/projet.en.cours.fr.mustache")
        record ViewPlainProjetEnCoursFR(GenerationContext context, List<Projet> delegates) implements ViewPlainProjetEnCours {
        }

        @JStache(path = "templates/section2/projet.en.cours.ar.mustache")
        record ViewPlainProjetEnCoursAR(GenerationContext context, List<Projet> delegates) implements ViewPlainProjetEnCours {
        }
    }


    sealed interface GPEEnCours extends ViewProjetEnCours {
        String HEADER_TITLE = "princip.projet.en.cours.gpe.header.title";
        static GPEEnCours of(GenerationContext context, List<Projet> delegates){
            return switch (context.direction()){
                case LTR -> new GPEEnCoursFR(context, delegates);
                case RTL -> new GPEEnCoursAR(context, delegates);
            };
        }
        @Override
        default String headerTitle() {
            return context().staticContent(HEADER_TITLE);
        }

        @JStache(path = "templates/section2/projet.en.cours.fr.mustache")
        record GPEEnCoursFR(GenerationContext context, List<Projet> delegates) implements GPEEnCours {
        }

        @JStache(path = "templates/section2/projet.en.cours.ar.mustache")
        record GPEEnCoursAR(GenerationContext context, List<Projet> delegates) implements GPEEnCours {
        }
    }

    default String headerAEReevaluation() {
        return context().formattedStaticContent(HEADER_AE_REEVALUATION, context().annee());
    }

    default String headerCPAnnee() {
        return context().formattedStaticContent(CP_ANNEE, context().annee());
    }

    default String headerCPAnneePlus1() {
        return context().formattedStaticContent(CP_ANNEE_PLUS_1, context().anneePlus1());
    }

    default String headerCPAnneePlus2() {
        return context().formattedStaticContent(CP_ANNEE_PLUS_2, context().anneePlus2());
    }


    default String totalCouteEstGlobal() {
        return totalFormatted(ProjetBlueprint::couteEstGlobal);
    }

    default String averageTauxAvancement() {
        var average = delegates().stream()
                .mapToDouble(Projet::tauxAvancement)
                .average()
                .orElse(0.00d);
        //todo make it only print two integer percentage
        return String.format("%.2f%%", average);
    }

    default String totalAE() {
        return totalFormatted(ProjetBlueprint::AEreevaluationDemandee);
    }

    default String totalCPAneee() {
        return totalFormatted(ProjetBlueprint::CPAnnee);
    }

    default String TotalCPAnneePlus1() {
        return totalFormatted(ProjetBlueprint::CPAnneePlus1);
    }

    default String totalCPAnneePlus2() {
        return totalFormatted(ProjetBlueprint::CPAnneePlus2);
    }

    default String totalCharges() {
        return totalFormatted(ProjetBlueprint::chargesRecurAnnuellMoyennesPrevus);
    }
    default String totalFormatted(ToLongFunction<ProjetBlueprint> mapper){
        var total = delegates().stream()
                .mapToLong(mapper)
                .sum();
        return NumberFormatter.format(total);
    }

    record ProjetView(GenerationContext context, Projet delegate) {

        String name() {
            return delegate.name();
        }

        String dateDebut() {
            return delegate.dateDebut().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        String dateFin() {
            return delegate.dateFin().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        String couteEstGlobal() {
            return NumberFormatter.format(delegate.couteEstGlobal());
        }

        String tauxAvancement() {
            //todo only integer percentage
            return context.formatContent("%d%%", delegate.tauxAvancement().intValue());
        }

        Boolean respetEcheanciers() {
            return delegate.respetEcheanciers();
        }

        String AEreevaluationDemandee() {
            return NumberFormatter.format(delegate.AEreevaluationDemandee());
        }

        String CPAnnee() {
            return NumberFormatter.format(delegate.CPAnnee());
        }

        String CPAnneePlus1() {
            return NumberFormatter.format(delegate.CPAnneePlus1());
        }

        String CPAnneePlus2() {
            return NumberFormatter.format(delegate.CPAnneePlus2());
        }

        String chargesRecurAnnuellMoyennesPrevus() {
            return NumberFormatter.format(delegate.chargesRecurAnnuellMoyennesPrevus());
        }
    }
}

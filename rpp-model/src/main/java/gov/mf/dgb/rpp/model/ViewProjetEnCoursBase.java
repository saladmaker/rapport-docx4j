package gov.mf.dgb.rpp.model;

import java.time.format.DateTimeFormatter;
import java.util.List;

interface ViewProjetEnCoursBase extends Viewable{

    List<Projet> delegates();

    String headerTitle();
    String headerAEReevaluationFormat();
    String headerCPAnneeFormat();
    String headerCPAnneePlus1Format();
    String headerCPAnneePlus2Format();

    default String headerAEReevaluation(){
        return headerAEReevaluationFormat().formatted(context().annee());
    }
    default String headerCPAnnee(){
        return headerCPAnneeFormat().formatted(context().annee());
    }
    default String headerCPAnneePlus1(){
        return headerCPAnneePlus1Format().formatted(context().anneePlus1());
    }
    default String headerCPAnneePlus2(){
        return headerCPAnneePlus2Format().formatted(context().anneePlus2());
    }


    default String totalCouteEstGlobal(){
        var total = delegates().stream()
                .mapToLong(Projet::couteEstGlobal)
                .sum();
        return NumberFormatter.format(total);
    }
    default String averageTauxAvancement(){
        var average = delegates().stream()
                .mapToDouble(Projet::tauxAvancement)
                .average()
                .orElse(0.00d);
        //todo make it only print two integer percentage
        return String.format("%.2f%%", average);
    }
    default String totalAE(){
        var total = delegates().stream()
                .mapToLong(Projet::AEreevaluationDemandee)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalCPAneee(){
        var total = delegates().stream()
                .mapToLong(Projet::CPAnnee)
                .sum();
        return NumberFormatter.format(total);
    }
    default String TotalCPAnneePlus1(){
        var total = delegates().stream()
                .mapToLong(Projet::CPAnneePlus1)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalCPAnneePlus2(){
        var total = delegates().stream()
                .mapToLong(Projet::CPAnneePlus2)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalCharges(){
        var total = delegates().stream()
                .mapToLong(Projet::chargesRecurAnnuellMoyennesPrevus)
                .sum();
        return NumberFormatter.format(total);
    }
    class ProjetView {

        private final Projet delegate;
        private final GenerationContext context;

        ProjetView(GenerationContext context, Projet delegate){
            this.context = context;
            this.delegate = delegate;
        }

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

        String tauxAvancement(){
            //todo only integer percentage
            var format = delegate.tauxAvancement() + "%";
            return context.direction().escape(format);
        }

        Boolean respetEcheanciers(){
            return delegate.respetEcheanciers();
        }

        String AEreevaluationDemandee(){
            return NumberFormatter.format(delegate.AEreevaluationDemandee());
        }

        String CPAnnee(){
            return NumberFormatter.format(delegate.CPAnnee());
        }

        String CPAnneePlus1(){
            return NumberFormatter.format(delegate.CPAnneePlus1());
        }

        String CPAnneePlus2(){
            return NumberFormatter.format(delegate.CPAnneePlus2());
        }

        String chargesRecurAnnuellMoyennesPrevus(){
            return NumberFormatter.format(delegate.chargesRecurAnnuellMoyennesPrevus());
        }
    }
}

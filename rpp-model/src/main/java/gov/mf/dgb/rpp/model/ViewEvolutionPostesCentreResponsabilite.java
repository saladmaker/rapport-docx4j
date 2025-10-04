package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewEvolutionPostesCentreResponsabilite {

    List<Evolution> delegates();

    GenerationContext context();

    default List<ViewEvolutionPosetes> evolutions(){
        return delegates().stream()
                .map(e-> new ViewEvolutionPosetes(e, context()))
                .toList();
    }

    static ViewEvolutionPostesCentreResponsabilite of(GenerationContext context, List<Evolution> evolutions){
        return switch(context.direction()){
            case LTR -> new ViewEvolutionPostesCentreResponsabiliteFR(context, evolutions);
            case RTL -> new ViewEvolutionPostesCentreResponsabiliteAR(context, evolutions);
        };
    }

    @JStache(path = "templates/section1/evolution.postes.centre.responsabilite.fr.mustache")
    record ViewEvolutionPostesCentreResponsabiliteFR(GenerationContext context, List<Evolution> delegates)
        implements ViewEvolutionPostesCentreResponsabilite{}

    @JStache(path = "templates/section1/evolution.postes.centre.responsabilite.ar.mustache")
    record ViewEvolutionPostesCentreResponsabiliteAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionPostesCentreResponsabilite{}

    default String totalAnneeMoins2(){
        var total = delegates().stream()
                .mapToLong(Evolution::anneeMoins2)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalAnneeMoins1(){
        var total = delegates().stream()
                .mapToLong(Evolution::anneeMoins1)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalAnnee(){
        var total = delegates().stream()
                .mapToLong(Evolution::annee)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalAnneePlus1(){
        var total = delegates().stream()
                .mapToLong(Evolution::anneePlus1)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalAnneePlus2(){
        var total = delegates().stream()
                .mapToLong(Evolution::anneePlus2)
                .sum();
        return NumberFormatter.format(total);
    }

    record ViewEvolutionPosetes(Evolution delegate, GenerationContext context){
        String name(){
            return context.staticContent(delegate.name());
        }

        String anneeMoins2(){
            return NumberFormatter.format(delegate.anneeMoins2());
        }
        String anneeMoins1(){
            return NumberFormatter.format(delegate.anneeMoins1());
        }
        String annee(){
            return NumberFormatter.format(delegate.annee());
        }
        String anneePlus1(){
            return NumberFormatter.format(delegate.anneePlus1());
        }
        String anneePlus2(){
            return NumberFormatter.format(delegate.anneePlus2());
        }
    }
}

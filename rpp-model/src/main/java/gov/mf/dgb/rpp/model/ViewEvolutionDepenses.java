package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewEvolutionDepenses {

    List<Evolution> delegates();

    GenerationContext context();

    ViewType viewType();

    static ViewEvolutionDepenses of(GenerationContext context, ViewType viewType, List<Evolution> evolutions) {
        return switch (context.direction()) {
            case LTR -> new ViewEvolutionDepensesFR(context, viewType, evolutions);
            case RTL -> new ViewEvolutionDepensesAR(context, viewType, evolutions);
        };
    }


    @JStache(path = "templates/evolution.depenses.fr.mustache")
    record ViewEvolutionDepensesFR(GenerationContext context, ViewType viewType, List<Evolution> delegates)
            implements ViewEvolutionDepenses {
    }

    @JStache(path = "templates/evolution.depenses.ar.mustache")
    record ViewEvolutionDepensesAR(GenerationContext context, ViewType viewType, List<Evolution> delegates)
            implements ViewEvolutionDepenses {
    }

    default List<ViewEvolutionDepense> evolutions() {
        return delegates().stream()
                .map(ViewEvolutionDepense::new)
                .toList();
    }
    default String header(){
        return context().staticContent(viewType().name());
    }

    default String totalAnneeMoins2() {
        var total = delegates().stream()
                .mapToLong(Evolution::anneeMoins2)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalAnneeMoins1() {
        var total = delegates().stream()
                .mapToLong(Evolution::anneeMoins1)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalAnnee() {
        var total = delegates().stream()
                .mapToLong(Evolution::annee)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalAnneePlus1() {
        var total = delegates().stream()
                .mapToLong(Evolution::anneePlus1)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalAnneePlus2() {
        var total = delegates().stream()
                .mapToLong(Evolution::anneePlus2)
                .sum();
        return NumberFormatter.format(total);
    }


    record ViewEvolutionDepense(Evolution delegate) {
        String name() {
            return delegate.name();
        }

        String anneeMoins2() {
            return NumberFormatter.format(delegate.anneeMoins2());
        }

        String anneeMoins1() {
            return NumberFormatter.format(delegate.anneeMoins1());
        }

        String annee() {
            return NumberFormatter.format(delegate.annee());
        }

        String anneePlus1() {
            return NumberFormatter.format(delegate.anneePlus1());
        }

        String anneePlus2() {
            return NumberFormatter.format(delegate.anneePlus2());
        }

    }
}

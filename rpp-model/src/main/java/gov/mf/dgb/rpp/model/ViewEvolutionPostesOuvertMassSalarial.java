package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.function.ToLongFunction;

sealed interface ViewEvolutionPostesOuvertMassSalarial extends Viewable{
    String HEADERS_ANNEE_MOINS_2 = "d";

    List<PostesOuvertMassSalarial> delegates();

    GenerationContext context();

    static ViewEvolutionPostesOuvertMassSalarial of(GenerationContext context, List<PostesOuvertMassSalarial> delegates){
        return switch (context.direction()){
            case LTR -> new ViewEvolutionPostesOuvertMassSalarialFR(context, delegates);
            case RTL -> new ViewEvolutionPostesOuvertMassSalarialAR(context, delegates);
        };
    }

    @JStache(path = "templates/section2/evolution.postes.ouvert.mass.salarial.fr.mustache")
    record ViewEvolutionPostesOuvertMassSalarialFR(GenerationContext context, List<PostesOuvertMassSalarial> delegates)
            implements ViewEvolutionPostesOuvertMassSalarial {
    }

    @JStache(path = "templates/section2/evolution.postes.ouvert.mass.salarial.ar.mustache")
    record ViewEvolutionPostesOuvertMassSalarialAR(GenerationContext context, List<PostesOuvertMassSalarial> delegates)
            implements ViewEvolutionPostesOuvertMassSalarial {
    }

    default List<ViewEvolution> evolutions() {
        return delegates().stream()
                .map(e -> new ViewEvolution(context(), e))
                .toList();
    }

    record ViewEvolution(GenerationContext context, PostesOuvertMassSalarial delegate) {
        String serviceType() {
            return context.staticContent(delegate.serviceType().name());
        }

        String postesAnneeMoins2() {
            return NumberFormatter.format(delegate.postesAnneeMoins2());
        }

        String postesAnneeMoins1() {
            return NumberFormatter.format(delegate.postesAnneeMoins1());
        }

        String postesAnnee() {
            return NumberFormatter.format(delegate.postesAnnee());
        }

        String massSalarialAnneeMoins2() {
            return NumberFormatter.format(delegate.massSalarialAnneeMoins2());
        }

        String massSalarialAnneeMoins1() {
            return NumberFormatter.format(delegate.massSalarialAnneeMoins1());
        }

        String massSalarialAnnee() {
            return NumberFormatter.format(delegate.massSalarialAnnee());
        }

        String nombre() {
            return NumberFormatter.format(delegate.nombre());
        }

        String variationPostes() {
            return String.format("%.2f%%", delegate.variationPostes());
        }

        String variationSalarial() {
            return NumberFormatter.format(delegate.variationSalarial());
        }
    }

    default String totalPostesAnneeMoins2() {
        return totalFormatted(PostesOuvertMassSalarial::postesAnneeMoins2);

    }

    default String totalPostesAnneeMoins1() {
        return totalFormatted(PostesOuvertMassSalarial::postesAnneeMoins1);
    }

    default String totalPostesAnnee() {
        return totalFormatted(PostesOuvertMassSalarial::postesAnnee);
    }

    default String totalNombre() {
        return totalFormatted(PostesOuvertMassSalarial::nombre);
    }

    default String averageVariation() {
        var average = delegates().stream()
                .mapToDouble(PostesOuvertMassSalarial::variationPostes)
                .average();

        return average.isPresent()
                ? String.format("%.2f%%", average.getAsDouble())
                : "-";
    }

    default String totalMassSalarialAnneeMoins2() {
        return totalFormatted(PostesOuvertMassSalarial::massSalarialAnneeMoins2);
    }

    default String totalMassSalarialAnneeMoins1() {
        return totalFormatted(PostesOuvertMassSalarial::massSalarialAnneeMoins1);

    }

    default String totalMassSalarialAnnee() {
        return totalFormatted(PostesOuvertMassSalarial::massSalarialAnnee);

    }

    default String totalVariationSalarial() {
        return totalFormatted(PostesOuvertMassSalarial::variationSalarial);
    }

    default String totalFormatted(ToLongFunction<PostesOuvertMassSalarial> mapper) {
        var total = delegates().stream()
                .mapToLong(mapper)
                .sum();
        return NumberFormatter.format(total);
    }
}

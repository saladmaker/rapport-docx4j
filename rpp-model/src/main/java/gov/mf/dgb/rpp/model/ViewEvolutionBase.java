package gov.mf.dgb.rpp.model;

import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Objects;

interface ViewEvolutionBase extends Viewable {

    List<Evolution> delegates();

    GenerationContext context();

    List<? extends EvolutionViewBase> evolutions();

    String headerTitle();

    String totalTitle();

    String headerAnneeMoins2Format();

    String headerAnneeMoins1Format();

    String headerAnneeFormat();

    String headerAnneePlus1Format();

    String headerAnneePlus2Format();

    default String headerAnneeMoins2() {
        return context().formatContent(headerAnneeMoins2Format(), context().anneeMoins2());
    }

    default String headerAnneeMoins1() {
        return context().formatContent(headerAnneeMoins1Format(), context().anneeMoins1());
    }

    default String headerAnnee() {
        return context().formatContent(headerAnneeFormat(), context().annee());
    }

    default String headerAnneePlus1() {
        return context().formatContent(headerAnneePlus1Format(), context().anneePlus1());
    }

    default String headerAnneePlus2() {
        return context().formatContent(headerAnneePlus2Format(), context().anneePlus2());
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

    public static abstract class EvolutionViewBase {
        protected final Evolution delegate;
        protected final GenerationContext context;

        EvolutionViewBase(GenerationContext context,
                                 Evolution delegate) {
            this.delegate = delegate;
            this.context = context;
        }

        public abstract String name();

        public String anneeMoins2() {
            return NumberFormatter.format(delegate.anneeMoins2());
        }

        public String anneeMoins1() {
            return NumberFormatter.format(delegate.anneeMoins1());
        }

        public String annee() {
            return NumberFormatter.format(delegate.annee());
        }

        public String anneePlus1() {
            return NumberFormatter.format(delegate.anneePlus1());
        }

        public String anneePlus2() {
            return NumberFormatter.format(delegate.anneePlus2());
        }

    }
}

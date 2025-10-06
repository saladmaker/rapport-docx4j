package gov.mf.dgb.rpp.model;

import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Objects;

interface ViewEvolutionBase extends Viewable {

    List<Evolution> delegates();

    GenerationContext context();

    String headerTitle();

    String totalTitle();

    String headerAnneeMoins2Format();

    String headerAnneeMoins1Format();

    String headerAnneeFormat();

    String headerAnneePlus1Format();

    String headerAnneePlus2Format();

    default List<? extends EvolutionView> evolutions() {
        return delegates().stream()
                .map(EvolutionView::new)
                .toList();
    }
    default String headerAnneeMoins2() {
        var text = headerAnneeMoins2Format().formatted(anneeMoins2());
        return escaped(text);
    }

    default String headerAnneeMoins1() {
        var text = headerAnneeMoins1Format().formatted(anneeMoins1());
        return escaped(text);
    }

    default String headerAnnee() {
        var text = headerAnneeFormat().formatted(annee());
        return escaped(text);
    }

    default String headerAnneePlus1() {
        var text = headerAnneePlus1Format().formatted(anneePlus1());
        return escaped(text);
    }

    default String headerAnneePlus2() {
        var text = headerAnneePlus2Format().formatted(anneePlus2());
        return escaped(text);
    }


    default int anneeMoins2() {
        return context().target().get(ChronoField.YEAR) - 2;
    }

    default int anneeMoins1() {
        return context().target().get(ChronoField.YEAR) - 1;
    }

    default int annee() {
        return context().target().get(ChronoField.YEAR);
    }

    default int anneePlus1() {
        return context().target().get(ChronoField.YEAR) + 1;
    }

    default int anneePlus2() {
        return context().target().get(ChronoField.YEAR) + 2;
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

    static class EvolutionView {
        private final Evolution delegate;

        public EvolutionView(Evolution delegate) {
            this.delegate = delegate;
        }

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

        public Evolution delegate() {
            return delegate;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (EvolutionView) obj;
            return Objects.equals(this.delegate, that.delegate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(delegate);
        }

        @Override
        public String toString() {
            return "EvolutionView[" +
                    "delegate=" + delegate + ']';
        }

    }
}

package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

sealed interface ViewEvolutionDepensesProgrammes extends ViewEvolutionBase {
    String TOTAL_TITLE = "section1.ficheportefeuille.table.6.total.title";
    String HEADER_TITLE = "section1.ficheportefeuille.table.6.header.title";
    String HEADERS_PREFIX ="section1.ficheportefeuille.table.6.headers.";

    @Override
    default List<? extends EvolutionViewBase> evolutions() {
        return delegates().stream()
                .map(e -> new EvolutionDepenseProgrammeView(context(), e))
                .toList();
    }

    static ViewEvolutionDepensesProgrammes of(GenerationContext context, List<Evolution> delegates) {
        return switch (context.direction()) {
            case LTR -> new ViewEvolutionDepensesProgrammesFR(context, delegates);
            case RTL -> new ViewEvolutionDepensesProgrammesAR(context, delegates);
        };
    }

    @JStache(path = "templates/evolution.fr.mustache")
    record ViewEvolutionDepensesProgrammesFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesProgrammes {
    }

    @JStache(path = "templates/evolution.ar.mustache")
    record ViewEvolutionDepensesProgrammesAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesProgrammes {
    }

    @Override
    default String headerTitle() {
        return context().staticContent(HEADER_TITLE);
    }

    @Override
    default String totalTitle() {
        return context().staticContent(TOTAL_TITLE);
    }

    @Override
    default String headerAnneeMoins2Format() {
        return context().staticContent(HEADERS_PREFIX + "1");
    }

    @Override
    default String headerAnneeMoins1Format() {
        return context().staticContent(HEADERS_PREFIX + "2");

    }

    @Override
    default String headerAnneeFormat() {
        return context().staticContent(HEADERS_PREFIX + "3");

    }

    @Override
    default String headerAnneePlus1Format() {
        return context().staticContent(HEADERS_PREFIX + "4");
    }

    @Override
    default String headerAnneePlus2Format() {
        return context().staticContent(HEADERS_PREFIX + "5");
    }

    final class EvolutionDepenseProgrammeView extends EvolutionViewBase {
        private EvolutionDepenseProgrammeView(GenerationContext context, Evolution delegate) {
            super(context, delegate);
        }

        @Override
        public String name() {
            return delegate.name();
        }
    }
}

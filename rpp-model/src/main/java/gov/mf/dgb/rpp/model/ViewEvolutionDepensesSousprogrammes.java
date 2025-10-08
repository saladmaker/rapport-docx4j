package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

sealed interface ViewEvolutionDepensesSousprogrammes extends ViewEvolutionBase {
    String TOTAL_TITLE = "section2.ficheprogramme.table.5.total.title";
    String HEADER_TITLE = "section2.ficheprogramme.table.5.header.title";
    String HEADERS_PREFIX = "section2.ficheprogramme.table.5.headers.";

    @Override
    default List<EvolutionDepenseSousprogrammeView> evolutions() {
        return delegates().stream()
                .map(e -> new EvolutionDepenseSousprogrammeView(context(), e))
                .toList();
    }

    static ViewEvolutionDepensesSousprogrammes of(GenerationContext context, List<Evolution> evolutions) {
        return switch (context.direction()){
            case LTR -> new ViewEvolutionDepensesSousprogrammesFR(context, evolutions);
            case RTL -> new ViewEvolutionDepensesSousprogrammesAR(context, evolutions);
        };
    }

    @JStache(path = "templates/evolution.fr.mustache")
    record ViewEvolutionDepensesSousprogrammesFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesSousprogrammes {
    }

    @JStache(path = "templates/evolution.ar.mustache")
    record ViewEvolutionDepensesSousprogrammesAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesSousprogrammes {
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

    final class EvolutionDepenseSousprogrammeView extends EvolutionViewBase {
        private EvolutionDepenseSousprogrammeView(GenerationContext context, Evolution delegate) {
            super(context, delegate);
        }

        @Override
        public String name() {
            return delegate.name();
        }
    }
}

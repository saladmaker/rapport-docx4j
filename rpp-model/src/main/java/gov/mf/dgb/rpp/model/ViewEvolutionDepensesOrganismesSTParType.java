package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewEvolutionDepensesOrganismesSTParType extends ViewEvolutionBase {
    String TOTAL_TITLE = "etat.complementaire.evolution.depenses.type.ost.total.title";
    String HEADER_TITLE = "etat.complementaire.evolution.depenses.type.ost.header.title";
    String HEADERS_PREFIX = "etat.complementaire.evolution.depenses.type.ost.headers.";

    @Override
    default List<? extends EvolutionViewBase> evolutions() {
        return delegates().stream()
                .map(e -> new EvolutionDepenseOrganismesSTParTypeView(context(), e))
                .toList();
    }

    static ViewEvolutionDepensesOrganismesSTParType of(GenerationContext context, List<Evolution> delegates) {
        return switch (context.direction()){
            case LTR -> new ViewEvolutionDepensesOrganismesSTParTypeFR(context, delegates);
            case RTL -> new ViewEvolutionDepensesOrganismesSTParTypeAR(context, delegates);
        };
    }

    @JStache(path = "templates/evolution.fr.mustache")
    record ViewEvolutionDepensesOrganismesSTParTypeFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesOrganismesSTParType {
    }

    @JStache(path = "templates/evolution.ar.mustache")
    record ViewEvolutionDepensesOrganismesSTParTypeAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesOrganismesSTParType {
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

    final class EvolutionDepenseOrganismesSTParTypeView extends EvolutionViewBase {
        private EvolutionDepenseOrganismesSTParTypeView(GenerationContext context, Evolution delegate) {
            super(context, delegate);
        }

        @Override
        public String name() {
            return delegate.name();
        }
    }
}

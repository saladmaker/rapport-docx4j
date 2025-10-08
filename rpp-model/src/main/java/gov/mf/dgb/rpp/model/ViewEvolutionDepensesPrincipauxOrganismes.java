package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewEvolutionDepensesPrincipauxOrganismes extends ViewEvolutionBase{
    String TOTAL_TITLE = "etat.complementaire.evolution.depenses.pricipaux.ost.total.title";
    String HEADER_TITLE = "etat.complementaire.evolution.depenses.pricipaux.ost.header.title";
    String HEADERS_PREFIX ="etat.complementaire.evolution.depenses.pricipaux.ost.headers.";

    @Override
    default List<EvolutionDepensesPrincipauxOrganismesView> evolutions() {
        return delegates().stream()
                .map(e -> new EvolutionDepensesPrincipauxOrganismesView(context(), e))
                .toList();
    }
    static ViewEvolutionDepensesPrincipauxOrganismes of(GenerationContext context, List<Evolution> delegates){
        return switch (context.direction()){
            case LTR -> new ViewEvolutionDepensesPrincipauxOrganismesFR(context, delegates);
            case RTL -> new ViewEvolutionDepensesPrincipauxOrganismesAR(context, delegates);
        };
    }

    @JStache(path = "templates/section2/evolution.principe.ost.fr.mustache")
    record ViewEvolutionDepensesPrincipauxOrganismesFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesPrincipauxOrganismes{
    }
    @JStache(path = "templates/section2/evolution.principe.ost.ar.mustache")
    record ViewEvolutionDepensesPrincipauxOrganismesAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesPrincipauxOrganismes{
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
    final class EvolutionDepensesPrincipauxOrganismesView extends EvolutionViewBase {
        private EvolutionDepensesPrincipauxOrganismesView(GenerationContext context, Evolution delegate) {
            super(context, delegate);
        }

        @Override
        public String name() {
            return delegate.name();
        }
    }
}

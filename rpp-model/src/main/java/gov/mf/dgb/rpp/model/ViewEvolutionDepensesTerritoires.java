package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

sealed interface ViewEvolutionDepensesTerritoires extends ViewEvolutionBase{

    String HEADER_TITLE = "etat.complementaire.evolution.depenses.territoire.header.title";
    String TOTAL_TITLE = "etat.complementaire.evolution.depenses.territoire.total.title";
    String HEADERS_PREFIX = "etat.complementaire.evolution.depenses.territoire.headers.";

    @Override
    default List<? extends EvolutionViewBase> evolutions() {
        return delegates().stream()
                .map(e-> new EvolutionTerritoireView(context(), e))
                .toList();
    }
    static ViewEvolutionDepensesTerritoires of(GenerationContext context, List<Evolution> evolutions){
        return switch (context.direction()){
            case LTR -> new ViewEvolutionDepensesTerritoiresFR(context, evolutions);
            case RTL -> new ViewEvolutionDepensesTerritoiresAR(context, evolutions);
        };
    }

    @JStache(path = "templates/evolution.fr.mustache")
    record ViewEvolutionDepensesTerritoiresFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesTerritoires{
    }
    @JStache(path = "templates/evolution.ar.mustache")
    record ViewEvolutionDepensesTerritoiresAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesTerritoires{
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

    final class EvolutionTerritoireView extends EvolutionViewBase{
        private EvolutionTerritoireView(GenerationContext context, Evolution delegate){
            super(context, delegate);
        }

        @Override
        public String name() {
            return delegate.name();
        }

    }
}

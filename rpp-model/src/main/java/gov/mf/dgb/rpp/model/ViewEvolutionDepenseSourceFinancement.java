package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

sealed interface ViewEvolutionDepenseSourceFinancement extends ViewEvolutionBase{
    String HEADER_TITLE = "etat.complementaire.evolution.depenses.source.financement.header.title";
    String TOTAL_TITLE = "etat.complementaire.evolution.depenses.source.financement.total.title";
    String HEADERS_PREFIX = "etat.complementaire.evolution.depenses.source.financement.headers.";

    static ViewEvolutionDepenseSourceFinancement of(GenerationContext context, List<Evolution> delegate){
        return switch (context.direction()){
            case LTR -> new ViewEvolutionDepenseSourceFinancementFR(context, delegate);
            case RTL -> new ViewEvolutionDepenseSourceFinancementAR(context, delegate);
        };
    }
    @JStache(path = "templates/evolution.fr.mustache")
    record ViewEvolutionDepenseSourceFinancementFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepenseSourceFinancement{}

    @JStache(path = "templates/evolution.ar.mustache")
    record ViewEvolutionDepenseSourceFinancementAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepenseSourceFinancement{}

    @Override
    default List<EvolutionSourceFinancement> evolutions(){
        return delegates().stream()
                .map(e-> new EvolutionSourceFinancement(context(), e))
                .toList();
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
        return context().staticContent(HEADERS_PREFIX + "2");
    }

    @Override
    default String headerAnneePlus1Format() {
        return context().staticContent(HEADERS_PREFIX + "3");
    }

    @Override
    default String headerAnneePlus2Format() {
        return context().staticContent(HEADERS_PREFIX + "4");
    }
    final class EvolutionSourceFinancement extends EvolutionViewBase{
        private EvolutionSourceFinancement(GenerationContext context, Evolution delegate){
            super(context, delegate);
        }
        @Override
        public String name() {
            return delegate.name();
        }
    }
}

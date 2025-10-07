package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewEvolutionPostes extends ViewEvolutionBase{
    String TOTAL_TITLE = "section1.ficheportefeuille.table.7.total.title";
    String HEADER_TITLE = "section1.ficheportefeuille.table.7.header.title";
    String HEADERS_PREFIX ="section1.ficheportefeuille.table.7.headers.";

    @Override
    default List<? extends EvolutionPostesView> evolutions(){
        return delegates().stream()
                .map(e-> new EvolutionPostesView(context(), e))
                .toList();
    }

    static ViewEvolutionPostes of(GenerationContext context, List<Evolution> delegates){
        return switch (context.direction()){
            case LTR -> new ViewEvolutionPostesFR(context, delegates);
            case RTL -> new ViewEvolutionPostesAR(context, delegates);
        };
    }

    @JStache(path = "templates/evolution.fr.mustache")
    record ViewEvolutionPostesFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionPostes{
    }

    @JStache(path = "templates/evolution.ar.mustache")
    record ViewEvolutionPostesAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionPostes{
    }

    @Override
    default String headerTitle(){
        return context().staticContent(HEADER_TITLE);
    }
    @Override
    default String totalTitle(){
        return context().staticContent(TOTAL_TITLE);
    }
    @Override
    default String headerAnneeMoins2Format(){
        return context().staticContent(HEADERS_PREFIX + "1");
    }

    @Override
    default String headerAnneeMoins1Format(){
        return context().staticContent(HEADERS_PREFIX + "2");
    }
    @Override
    default String headerAnneeFormat(){
        return context().staticContent(HEADERS_PREFIX + "3");
    }

    @Override
    default String headerAnneePlus1Format(){
        return context().staticContent(HEADERS_PREFIX + "4");
    }

    @Override
    default String headerAnneePlus2Format(){
        return context().staticContent(HEADERS_PREFIX + "5");
    }
    final class EvolutionPostesView extends EvolutionViewBase{
        private EvolutionPostesView(GenerationContext context, Evolution delegate){
            super(context, delegate);
        }

        @Override
        public String name() {
            return context.staticContent(delegate.name());
        }


    }
}

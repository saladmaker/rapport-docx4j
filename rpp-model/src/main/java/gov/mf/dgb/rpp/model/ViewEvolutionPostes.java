package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewEvolutionPostes extends ViewEvolutionBase{
    String TABLE_PREFIX = "section1.ficheportefeuille.table.7.";
    String HEADERS = TABLE_PREFIX + "headers.";

    @Override
    default List<? extends ViewEvolutionBase.EvolutionView> evolutions(){
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
        return context().staticContent(HEADERS + "0");
    }
    @Override
    default String totalTitle(){
        return context().staticContent(TABLE_PREFIX + "total");
    }
    @Override
    default String headerAnneeMoins2Format(){
        return context().staticContent(HEADERS + "1");
    }

    @Override
    default String headerAnneeMoins1Format(){
        return context().staticContent(HEADERS + "2");
    }
    @Override
    default String headerAnneeFormat(){
        return context().staticContent(HEADERS + "3");
    }

    @Override
    default String headerAnneePlus1Format(){
        return context().staticContent(HEADERS + "4");
    }

    @Override
    default String headerAnneePlus2Format(){
        return context().staticContent(HEADERS + "5");
    }
    class EvolutionPostesView extends EvolutionView{
        private final GenerationContext context;
        private EvolutionPostesView(GenerationContext context, Evolution delegate){
            super(delegate);
            this.context = context;
        }

        @Override
        String name() {
            return context.staticContent(super.name());
        }
    }
}

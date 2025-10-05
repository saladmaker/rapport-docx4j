package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

//todo make this generic for both (programmes, sous-programmes)
interface ViewRepartitionTitre {


    List<RepartitionTitre> delegates();

    GenerationContext context();

    ViewType viewType();

    static ViewRepartitionTitre of(GenerationContext context, ViewType viewType, List<RepartitionTitre> delegates){
        Objects.requireNonNull(context);
        Objects.requireNonNull(viewType);
        Objects.requireNonNull(delegates);

        return switch (context.direction()){
            case LTR -> new ViewRepartitionTitreFR(context, viewType, delegates);
            case RTL -> new ViewRepartitionTitreAR(context, viewType, delegates);
        };
    }

    default List<RepartitionTitreView> repartitions(){
        return delegates().stream()
                .map(e-> new RepartitionTitreView(context(), e))
                .toList();
    }

    default String header(){
        return context().staticContent(viewType().name());
    }
    @JStache(path = "templates/repartitionTitre.fr.mustache")
    record ViewRepartitionTitreFR(GenerationContext context, ViewType viewType, List<RepartitionTitre> delegates) implements ViewRepartitionTitre {
    }

    @JStache(path = "templates/repartitionTitre.ar.mustache")
    record ViewRepartitionTitreAR(GenerationContext context, ViewType viewType, List<RepartitionTitre> delegates) implements ViewRepartitionTitre {
    }



    record RepartitionTitreView(GenerationContext context, RepartitionTitre delegate){

        public String name() {
            return delegate.name();
        }

        public String titre1() {
            return NumberFormatter.format(delegate.titre1());
        }

        public String titre2() {
            return NumberFormatter.format(delegate.titre2());
        }

        public String titre3() {
            return NumberFormatter.format(delegate.titre3());
        }

        public String titre4() {
            return NumberFormatter.format(delegate.titre4());
        }

        public String titre5() {
            return NumberFormatter.format(delegate.titre5());
        }

        public String titre6() {
            return NumberFormatter.format(delegate.titre6());
        }

        public String titre7() {
            return NumberFormatter.format(delegate.titre7());
        }

        String total(){
            return NumberFormatter.format(delegate.total());
        }

    }

    default String globalTotal(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::total)
                .sum();
        return NumberFormatter.format(total);
    }
    default boolean hasAutreTitre(){
        return delegates().stream()
                .anyMatch(RepartitionTitre::isMF);
    }
    default String totalTitre1(){
        var total= delegates().stream()
                .mapToLong(RepartitionTitre::titre1)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre2(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre2)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre3(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre3)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre4(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre4)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre5(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre5)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre6(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre6)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre7(){
        var total = delegates().stream()
                .mapToLong(RepartitionTitre::titre7)
                .sum();
        return NumberFormatter.format(total);
    }



}

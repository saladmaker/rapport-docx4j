package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

interface ViewCentreResponsabiliteTitre {

    List<RepartitionCentreResponsabiliteTitre> delegates();

    GenerationContext context();

    @JStache(path = "templates/repartitionCentreResponsabiliteTitre.ar.mustache")
    record ViewCentreResponsabiliteTitreAR(GenerationContext context,
                                           List<RepartitionCentreResponsabiliteTitre> delegates)
            implements ViewCentreResponsabiliteTitre {
    }

    @JStache(path = "templates/repartitionCentreResponsabiliteTitre.fr.mustache")
    record ViewCentreResponsabiliteTitreFR(GenerationContext context,
                                           List<RepartitionCentreResponsabiliteTitre> delegates)
            implements ViewCentreResponsabiliteTitre {
    }

    static ViewCentreResponsabiliteTitre of(GenerationContext context,
                                            List<RepartitionCentreResponsabiliteTitre> delegates){
        Objects.requireNonNull(delegates);
        Objects.requireNonNull(context);

        return switch (context.direction()){
            case LTR -> new ViewCentreResponsabiliteTitreFR(context, delegates);
            case RTL -> new ViewCentreResponsabiliteTitreAR(context, delegates);
        };
    }

    default boolean hasAutreTitre() {
        return delegates().stream()
                .anyMatch(RepartitionCentreResponsabiliteTitre::hasAutreTitre);
    }

    default List<CentreResponsabiliteTitre> repartitions() {
        return delegates().stream()
                .filter(e -> !(0 == e.total()))
                .map(e -> new CentreResponsabiliteTitre(e, context()))
                .toList();
    }

    class CentreResponsabiliteTitre {
        private final GenerationContext context;
        private final RepartitionCentreResponsabiliteTitre delegate;

        CentreResponsabiliteTitre(RepartitionCentreResponsabiliteTitre delegate, GenerationContext context) {
            this.context = context;
            this.delegate = delegate;
        }

        String serviceType() {
            return context.staticContent(delegate.serviceType().name());
        }

        String titre1() {
            return NumberFormatter.format(delegate.titre1());
        }

        String titre2() {
            return NumberFormatter.format(delegate.titre2());
        }

        String titre3() {
            return NumberFormatter.format(delegate.titre3());
        }

        String titre4() {
            return NumberFormatter.format(delegate.titre4());
        }

        String titre5() {
            return NumberFormatter.format(delegate.titre5());
        }

        String titre6() {
            return NumberFormatter.format(delegate.titre6());
        }

        String titre7() {
            return NumberFormatter.format(delegate.titre7());
        }

        String total() {
            return NumberFormatter.format(delegate.total());
        }
    }

    default String globalTotal() {
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::total)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalTitre1() {
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::titre1)
                .sum();
        return NumberFormatter.format(total);

    }
    default String totalTitre2(){
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::titre2)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre3(){
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::titre3)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre4(){
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::titre4)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre5(){
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::titre5)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre6(){
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::titre6)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalTitre7(){
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabiliteTitre::titre7)
                .sum();
        return NumberFormatter.format(total);
    }
}

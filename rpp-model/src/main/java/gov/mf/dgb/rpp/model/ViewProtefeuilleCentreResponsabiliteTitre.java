package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;

import java.util.List;

interface ViewProtefeuilleCentreResponsabiliteTitre {

    List<RepartitionCentreResponsabiliteTitre> delegates();

    GenerationContext context();

    default boolean hasAutreTitre(){
        return delegates().stream()
                .anyMatch(RepartitionCentreResponsabiliteTitre::hasAutreTitre);
    }


    default List<CentreResponsabiliteTitre> repartitions(){
        return delegates().stream()
                .filter(e -> !(0 == e.total()))
                .map(e -> new CentreResponsabiliteTitre(e, context()))
                .toList();
    }
    record ViewProtefeuilleCentreResponsabiliteTitreAR(List<RepartitionCentreResponsabiliteTitre> delegates, GenerationContext context)
            implements ViewProtefeuilleCentreResponsabiliteTitre{

    }
    record ViewProtefeuilleCentreResponsabiliteTitreFR(List<RepartitionCentreResponsabiliteTitre> delegates, GenerationContext context)
            implements ViewProtefeuilleCentreResponsabiliteTitre{
    }

    class CentreResponsabiliteTitre{
        private final GenerationContext context;
        private final RepartitionCentreResponsabiliteTitre delegate;
        CentreResponsabiliteTitre(RepartitionCentreResponsabiliteTitre delegate, GenerationContext context){
            this.context = context;
            this.delegate = delegate;
        }
        String serviceType(){
            return context.staticContent(delegate.serviceType().name());
        }

        String titre1(){
            return NumberFormatter.format(delegate.titre1());
        }

        String titre2(){
            return NumberFormatter.format(delegate.titre2());
        }

        String titre3(){
            return NumberFormatter.format(delegate.titre3());
        }

        String titre4(){
            return NumberFormatter.format(delegate.titre4());
        }

        String titre5(){
            return NumberFormatter.format(delegate.titre5());
        }

        String titre6(){
            return NumberFormatter.format(delegate.titre6());
        }

        String titre7(){
            return NumberFormatter.format(delegate.titre7());
        }

        String total(){
            return NumberFormatter.format(delegate.total());
        }
    }
}

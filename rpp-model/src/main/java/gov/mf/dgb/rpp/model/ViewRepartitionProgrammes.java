package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

interface ViewRepartitionProgrammes {

    @JStache(path = "templates/repartitionProgrammes.fr.mustache")
    interface ViewRepartitionProgrammesFR extends ViewRepartitionProgrammes {
        static ViewRepartitionProgrammesFR of(List<RepartitionProgramme> repartitions){
            return () -> repartitions;
        }
    }

    @JStache(path = "templates/repartitionProgrammes.ar.mustache")
    interface ViewRepartitionProgrammesAR extends ViewRepartitionProgrammes {
        static ViewRepartitionProgrammesAR of(List<RepartitionProgramme> repartitions){
            return () -> repartitions;
        }
    }

    List<RepartitionProgramme> repartition();

    static ViewRepartitionProgrammes of(
            List<RepartitionProgramme> repartitionProgrammes,
            LanguageDirection direction){
        //invariants check
        Objects.requireNonNull(repartitionProgrammes);
        Objects.requireNonNull(direction);
        return switch (direction){
            case LTR -> ViewRepartitionProgrammesFR.of(repartitionProgrammes);
            case RTL -> ViewRepartitionProgrammesAR.of(repartitionProgrammes);
        };
    }

    default String totalAE(){
        var totalAE = repartition().stream()
                .mapToLong(RepartitionProgramme::ae)
                .sum();
        return NumberFormatter.format(totalAE);
    }

    default String totalCP(){
        var totalCP = repartition().stream()
                .mapToLong(RepartitionProgramme::cp)
                .sum();
        return NumberFormatter.format(totalCP);
    }
}

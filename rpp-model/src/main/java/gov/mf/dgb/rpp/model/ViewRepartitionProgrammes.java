package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

sealed interface ViewRepartitionProgrammes{
    List<RepartitionProgramme> repartition();

    @JStache(path = "templates/section1/repartitionProgrammes.fr.mustache")
    record ViewRepartitionProgrammesFR(List<RepartitionProgramme> repartition)
            implements ViewRepartitionProgrammes {

    }

    @JStache(path = "templates/section1/repartitionProgrammes.ar.mustache")
    record ViewRepartitionProgrammesAR(List<RepartitionProgramme> repartition)
            implements ViewRepartitionProgrammes {
    }



    static ViewRepartitionProgrammes of(
            List<RepartitionProgramme> repartitionProgrammes,
            LanguageDirection direction){
        //invariants check
        Objects.requireNonNull(repartitionProgrammes);
        Objects.requireNonNull(direction);
        return switch (direction){
            case LTR -> new ViewRepartitionProgrammesFR(repartitionProgrammes);
            case RTL -> new ViewRepartitionProgrammesAR(repartitionProgrammes);
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

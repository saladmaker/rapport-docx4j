package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

public interface RepartitionProgrammesView {

    @JStache(path = "templates/repartitionProgrammes.fr.mustache")
    interface RepartitionProgrammesViewFR extends RepartitionProgrammesView{
        static RepartitionProgrammesViewFR of(List<RepartitionProgramme> repartitions){
            return () -> repartitions;
        }
    }
//todo
    @JStache(path = "templates/repartitionProgrammes.ar.mustache")
    interface RepartitionProgrammesViewAR extends RepartitionProgrammesView{
        static RepartitionProgrammesViewAR of(List<RepartitionProgramme> repartitions){
            return () -> repartitions;
        }
    }

    List<RepartitionProgramme> repartition();

    static RepartitionProgrammesView of(
            List<RepartitionProgramme> repartitionProgrammes,
            LanguageDirection direction){
        //invariants check
        Objects.requireNonNull(repartitionProgrammes);
        Objects.requireNonNull(direction);
        return switch (direction){
            case LTR -> RepartitionProgrammesViewFR.of(repartitionProgrammes);
            case RTL -> RepartitionProgrammesViewAR.of(repartitionProgrammes);
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

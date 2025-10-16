package gov.mf.dgb.rpp.model;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import java.util.List;

sealed interface RepartitionProgrammeQute {
    List<RepartitionProgramme> repartitions();
    static TemplateInstance of(GenerationContext context, List<RepartitionProgramme> repartitions){
        return switch (context.direction()){
            case LTR -> Templates.repartition_programme_fr(new RepatitionProgrammesQuteFR(repartitions));
            case RTL -> Templates.repartition_programme_ar(new RepartitionProgrammesQuteAR(repartitions));
        };
    }
    @CheckedTemplate
    static class Templates{
        public static native TemplateInstance repartition_programme_fr(RepatitionProgrammesQuteFR view);
        public static native TemplateInstance repartition_programme_ar(RepartitionProgrammesQuteAR view);
    }
    record RepatitionProgrammesQuteFR(List<RepartitionProgramme> repartitions)
            implements RepartitionProgrammeQute{
    }
    record RepartitionProgrammesQuteAR(List<RepartitionProgramme> repartitions)
            implements RepartitionProgrammeQute{}
    default String totalAE(){
        var totalAE = repartitions().stream()
                .mapToLong(RepartitionProgramme::ae)
                .sum();
        return NumberFormatter.format(totalAE);
    }

    default String totalCP(){
        var totalCP = repartitions().stream()
                .mapToLong(RepartitionProgramme::cp)
                .sum();
        return NumberFormatter.format(totalCP);
    }
}

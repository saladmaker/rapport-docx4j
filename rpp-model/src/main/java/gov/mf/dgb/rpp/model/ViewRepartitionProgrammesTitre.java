package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewRepartitionProgrammesTitre extends ViewRepartitionTitreBase {

    String HEADER_TITLE = "section1.ficheportefeuille.table.4.header.title";
    String TOTAL_TITLE = "section1.ficheportefeuille.table.4.total.title";

    static ViewRepartitionProgrammesTitre of(GenerationContext context, List<RepartitionTitre> delegates){
        return switch (context.direction()){
            case LTR -> new ViewRepartitionProgrammesTitreFR(context, delegates);
            case RTL -> new ViewRepartitionProgrammesTitreAR(context, delegates);
        };
    }
    @JStache(path = "templates/repartitionTitre.fr.mustache")
    record ViewRepartitionProgrammesTitreFR(GenerationContext context, List<RepartitionTitre> delegates)
            implements ViewRepartitionProgrammesTitre {
    }
    @JStache(path = "templates/repartitionTitre.ar.mustache")
    record ViewRepartitionProgrammesTitreAR(GenerationContext context, List<RepartitionTitre> delegates)
            implements ViewRepartitionProgrammesTitre {
    }

    @Override
    default String headerTitle() {
        return context().staticContent(HEADER_TITLE);
    }

    @Override
    default String totalTitle() {
        return context().staticContent(TOTAL_TITLE);
    }
}

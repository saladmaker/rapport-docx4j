package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewRepartitionSousprogrammesTitre extends ViewRepartitionTitreBase {
    String HEADER_TITLE = "section2.ficheprogramme.table.4.header.title";

    static ViewRepartitionSousprogrammesTitre of(GenerationContext context, List<RepartitionTitre> delegates) {
        return switch (context.direction()) {
            case LTR -> new ViewRepartitionSousprogrammesTitreFR(context, delegates);
            case RTL -> new ViewRepartitionSousprogrammesTitreAR(context, delegates);
        };
    }

    @JStache(path = "templates/repartitionTitre.fr.mustache")
    record ViewRepartitionSousprogrammesTitreFR(GenerationContext context, List<RepartitionTitre> delegates)
            implements ViewRepartitionSousprogrammesTitre {
    }

    @JStache(path = "templates/repartitionTitre.ar.mustache")
    record ViewRepartitionSousprogrammesTitreAR(GenerationContext context, List<RepartitionTitre> delegates)
            implements ViewRepartitionSousprogrammesTitre {
    }

    @Override
    default String headerTitle() {
        return context().staticContent(HEADER_TITLE);
    }

}

package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;

interface ViewEvolutionDepensesProgrammes extends ViewEvolutionBase {
    String TABLE_PREFIX = "section1.ficheportefeuille.table.6.";
    String HEADERS = TABLE_PREFIX + "headers.";


    static ViewEvolutionDepensesProgrammes of(GenerationContext context, List<Evolution> delegates) {
        return switch (context.direction()) {
            case LTR -> new ViewEvolutionDepensesProgrammesFR(context, delegates);
            case RTL -> new ViewEvolutionDepensesProgrammesAR(context, delegates);
        };
    }

    @JStache(path = "templates/evolution.fr.mustache")
    record ViewEvolutionDepensesProgrammesFR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesProgrammes {
    }

    @JStache(path = "templates/evolution.ar.mustache")
    record ViewEvolutionDepensesProgrammesAR(GenerationContext context, List<Evolution> delegates)
            implements ViewEvolutionDepensesProgrammes {
    }

    @Override
    default String headerTitle() {
        return context().staticContent(HEADERS + "0");
    }

    @Override
    default String totalTitle() {
        return context().staticContent(TABLE_PREFIX + "total");
    }

    @Override
    default String headerAnneeMoins2Format() {
        return context().staticContent(HEADERS + "1");
    }

    @Override
    default String headerAnneeMoins1Format() {
        return context().staticContent(HEADERS + "2");

    }

    @Override
    default String headerAnneeFormat() {
        return context().staticContent(HEADERS + "3");

    }

    @Override
    default String headerAnneePlus1Format() {
        return context().staticContent(HEADERS + "4");
    }

    @Override
    default String headerAnneePlus2Format() {
        return context().staticContent(HEADERS + "5");
    }
}

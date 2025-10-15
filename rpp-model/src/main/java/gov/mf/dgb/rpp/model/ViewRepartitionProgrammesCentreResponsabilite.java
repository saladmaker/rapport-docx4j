package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

interface ViewRepartitionProgrammesCentreResponsabilite {

    List<RepartitionCentreResponsabilite> delegates();

    static ViewRepartitionProgrammesCentreResponsabilite of(
            List<RepartitionCentreResponsabilite> repartitions,
            LanguageDirection direction) {

        Objects.requireNonNull(repartitions);
        Objects.requireNonNull(direction);

        return switch (direction) {
            case LTR -> new ViewRepartitionProgrammesCentreResponsabiliteFR(repartitions);
            case RTL -> new ViewRepartitionProgrammesCentreResponsabiliteAR(repartitions);
        };
    }
    @JStache(path = "templates/section1/repartitionProgrammesCentreResponsabilite.fr.mustache")
    record ViewRepartitionProgrammesCentreResponsabiliteFR(List<RepartitionCentreResponsabilite> delegates)
            implements ViewRepartitionProgrammesCentreResponsabilite {
    }

    @JStache(path = "templates/section1/repartitionProgrammesCentreResponsabilite.ar.mustache")
    record ViewRepartitionProgrammesCentreResponsabiliteAR(List<RepartitionCentreResponsabilite> delegates)
            implements ViewRepartitionProgrammesCentreResponsabilite {
    }
    default List<RepartitionCentreResponsabiliteView> repartitions(){
        return delegates().stream()
                .map(RepartitionCentreResponsabiliteView::new)
                .toList();
    }

    default boolean require10Csz(){
        return globalTotal().length() > 11;
    }
    default boolean require8Csz(){
        return globalTotal().length() > 12;
    }
    default String totalServicesCentraux() {
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabilite::servicesCentraux)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalServicesDeconcentres() {
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabilite::servicesDeconcentres)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalOragnismesSousTutelles() {
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabilite::organismesSousTutelles)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalOrganesTerritoriaux() {
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabilite::organesTerritoriaux)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalAutreOrganismesSousTutelles(){
        return NumberFormatter.format(totalAutreOrganismesSousTutelle());
    }
    default String globalTotal(){
        var total = delegates().stream()
                .mapToLong(RepartitionCentreResponsabilite::total)
                .sum();
        return NumberFormatter.format(total);
    }
    default boolean hasAutreOrganismesSousTutelle() {
        return totalAutreOrganismesSousTutelle() != 0L;
    }
    private Long totalAutreOrganismesSousTutelle() {
        return delegates().stream()
                .mapToLong(RepartitionCentreResponsabilite::autreOrganismesSousTutelles)
                .sum();
    }
    record RepartitionCentreResponsabiliteView(RepartitionCentreResponsabilite delegate) {

        public String name() {
            return delegate().name();
        }

        public String servicesCentraux() {
            return NumberFormatter.format(delegate.servicesCentraux());
        }

        public String servicesDeconcentres() {
            return NumberFormatter.format(delegate.servicesDeconcentres());
        }

        public String organismesSousTutelles() {
            return NumberFormatter.format(delegate.organismesSousTutelles());
        }

        public String organesTerritoriaux() {
            return NumberFormatter.format(delegate.organesTerritoriaux());
        }

        public String autreOrganismesSousTutelles() {
            return NumberFormatter.format(delegate.autreOrganismesSousTutelles());
        }

        String total() {
            return NumberFormatter.format(delegate.total());
        }


    }
}

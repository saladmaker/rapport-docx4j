package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

interface RepartitionProgrammesCentreResponsabiliteView {
    List<RepartitionCentreResponsabiliteView> repartitions();

    @JStache(path = "templates/repartitionProgrammesCentreResponsabilite.fr.mustache")
    interface RepartitionProgrammesCentreResponsabiliteViewFR extends RepartitionProgrammesCentreResponsabiliteView {
        static RepartitionProgrammesCentreResponsabiliteViewFR of(List<RepartitionCentreResponsabiliteView> repartitions) {
            return () -> repartitions;
        }
    }

    interface RepartitionProgrammesCentreResponsabiliteViewAR extends RepartitionProgrammesCentreResponsabiliteView {
        static RepartitionProgrammesCentreResponsabiliteViewAR of(List<RepartitionCentreResponsabiliteView> repartitions) {
            return () -> repartitions;
        }
    }


    static RepartitionProgrammesCentreResponsabiliteView of(
            List<RepartitionCentreResponsabilite> repartitions,
            LanguageDirection direction) {

        Objects.requireNonNull(repartitions);
        Objects.requireNonNull(direction);

        List<RepartitionCentreResponsabiliteView> views = repartitions.stream()
                .map(RepartitionCentreResponsabiliteView::new)
                .toList();

        return switch (direction) {
            case LTR -> RepartitionProgrammesCentreResponsabiliteView
                    .RepartitionProgrammesCentreResponsabiliteViewFR.of(views);
            case RTL -> RepartitionProgrammesCentreResponsabiliteView.
                    RepartitionProgrammesCentreResponsabiliteViewAR.of(views);
        };
    }

    default String totalServicesCentraux() {
        var total = repartitions().stream()
                .mapToLong(RepartitionCentreResponsabiliteView::servicesCentraux)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalServicesDeconcentres() {
        var total = repartitions().stream()
                .mapToLong(RepartitionCentreResponsabiliteView::servicesDeconcentres)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalOragnismesSousTutelles() {
        var total = repartitions().stream()
                .mapToLong(RepartitionCentreResponsabiliteView::organismesSousTutelles)
                .sum();
        return NumberFormatter.format(total);
    }

    default String totalOrganesTerritoriaux() {
        var total = repartitions().stream()
                .mapToLong(RepartitionCentreResponsabiliteView::organesTerritoriaux)
                .sum();
        return NumberFormatter.format(total);
    }
    default String totalAutreOrganismesSousTutelles(){
        return NumberFormatter.format(totalAutreOrganismesSousTutelle());
    }
    default String globalTotal(){
        var total = repartitions().stream()
                .mapToLong(RepartitionCentreResponsabiliteView::totalNumber)
                .sum();
        return NumberFormatter.format(total);
    }
    default boolean hasAutreOrganismesSousTutelle() {
        return totalAutreOrganismesSousTutelle() != 0L;
    }
    private Long totalAutreOrganismesSousTutelle() {
        return repartitions().stream()
                .mapToLong(RepartitionCentreResponsabiliteView::autreOrganismesSousTutelles)
                .sum();
    }

    record RepartitionCentreResponsabiliteView(RepartitionCentreResponsabilite delegate)
            implements RepartitionCentreResponsabilite {

        @Override
        public String name() {
            return delegate().name();
        }

        @Override
        public Long servicesCentraux() {
            return delegate.servicesCentraux();
        }

        @Override
        public Long servicesDeconcentres() {
            return delegate.servicesDeconcentres();
        }

        @Override
        public Long organismesSousTutelles() {
            return delegate.organismesSousTutelles();
        }

        @Override
        public Long organesTerritoriaux() {
            return delegate.organesTerritoriaux();
        }

        @Override
        public Long autreOrganismesSousTutelles() {
            return delegate.autreOrganismesSousTutelles();
        }

        String total() {
            var total = servicesCentraux() +
                    servicesDeconcentres() +
                    organismesSousTutelles() +
                    organesTerritoriaux() +
                    autreOrganismesSousTutelles();
            return NumberFormatter.format(total);
        }
        Long totalNumber(){
            return servicesCentraux() +
                    servicesDeconcentres() +
                    organismesSousTutelles() +
                    organesTerritoriaux() +
                    autreOrganismesSousTutelles();
        }
    }
}

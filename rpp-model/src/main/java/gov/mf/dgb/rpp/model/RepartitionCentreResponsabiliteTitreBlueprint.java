package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;
import java.util.Objects;

@Prototype.Blueprint
@Prototype.CustomMethods(RepartitionCentreResponsabiliteTitreBlueprint.CustomMethods.class)
interface RepartitionCentreResponsabiliteTitreBlueprint {
    CentreResponsabilite serviceType();

    @Option.DefaultLong(0L)
    Long titre1();

    @Option.DefaultLong(0L)
    Long titre2();

    @Option.DefaultLong(0L)
    Long titre3();

    @Option.DefaultLong(0L)
    Long titre4();

    @Option.DefaultLong(0L)
    Long titre5();

    @Option.DefaultLong(0L)
    Long titre6();

    @Option.DefaultLong(0L)
    Long titre7();

    default Long total(){
        return titre1() +
                titre2() +
                titre3() +
                titre4() +
                titre5() +
                titre6() +
                titre7();
    }

    default boolean hasAutreTitre(){
        return (titre5() + titre6() + titre7()) > 0;
    }
    final class CustomMethods{
        @Prototype.FactoryMethod
        static RepartitionCentreResponsabiliteTitre create(CentreResponsabilite centreResponsabilite, List<Long> repartitions){
            return RepartitionCentreResponsabiliteTitre.builder()
                    .serviceType(centreResponsabilite)
                    .repartitions(repartitions)
                    .build();
        }
        @Prototype.BuilderMethod
        static void repartitions(RepartitionCentreResponsabiliteTitre.BuilderBase<?, ?> builder, List<Long> repartitions){
            Objects.requireNonNull(repartitions);

            var repartitionsSize = repartitions.size();

            // repartitions should have t1, t2, t3, t4, [t5, t6, t7]
            if (repartitionsSize < 4 || repartitionsSize > 7) {
                throw new IllegalArgumentException("repartitions size should have a size of 4-7, found: "
                        + repartitions.size() + " repartitions:" + repartitions.toString());
            }
            if (builder.serviceType().isPresent()) {
                var serviceType = builder.serviceType().get();
                if (!(CentreResponsabilite.SERVICES_CENTRAUX == serviceType) && repartitionsSize > 4) {
                    var totalAutreTitres = repartitions.stream()
                            .skip(4)
                            .mapToLong(Long::longValue)
                            .sum();
                    if(totalAutreTitres > 0L) {
                        throw new IllegalArgumentException(
                                "repartitions size for non service centraux should be 4, found: service type " + serviceType
                                        + " size:"
                                        + repartitionsSize + " repartitions:" + repartitions.toString());
                    }
                }
            }
            for (int i = 0; i < repartitionsSize; i++) {

                switch (i) {

                    case 0 -> {
                        builder.titre1(repartitions.get(i));
                    }
                    case 1 -> {
                        builder.titre2(repartitions.get(i));
                    }
                    case 2 -> {
                        builder.titre3(repartitions.get(i));
                    }
                    case 3 -> {
                        builder.titre4(repartitions.get(i));
                    }
                    case 4 -> {
                        builder.titre5(repartitions.get(i));
                    }
                    case 5 -> {
                        builder.titre6(repartitions.get(i));
                    }
                    case 6 -> {
                        builder.titre7(repartitions.get(i));
                    }

                }
            }
        }
        @Prototype.FactoryMethod
        static RepartitionCentreResponsabiliteTitre ofCentraux(List<Long> repartitions){
            return RepartitionCentreResponsabiliteTitre.create(CentreResponsabilite.SERVICES_CENTRAUX, repartitions);
        }
        @Prototype.FactoryMethod
        static RepartitionCentreResponsabiliteTitre ofDeconcentres(List<Long> repartitions){
            return RepartitionCentreResponsabiliteTitre.create(CentreResponsabilite.SERVICES_DECONCENTRES, repartitions);
        }
        @Prototype.FactoryMethod
        static RepartitionCentreResponsabiliteTitre ofOrganismesSousTutelle(List<Long> repartitions){
            return RepartitionCentreResponsabiliteTitre.create(CentreResponsabilite.ORGANISMES_SOUS_TUTELLE, repartitions);
        }
        @Prototype.FactoryMethod
        static RepartitionCentreResponsabiliteTitre ofOrganesTerritoriaux(List<Long> repartitions){
            return RepartitionCentreResponsabiliteTitre.create(CentreResponsabilite.ORGANES_TERRITORIAUX, repartitions);
        }
        @Prototype.FactoryMethod
        static RepartitionCentreResponsabiliteTitre ofAutreServiceSousTutelle(List<Long> repartitions){
            return RepartitionCentreResponsabiliteTitre.create(CentreResponsabilite.AUTRE_SERVICES_SOUS_TUTELLE, repartitions);
        }
    }
}

package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;
import java.util.Objects;

@Prototype.Blueprint
@Prototype.CustomMethods(RepartitionCentreResponsabiliteBlueprint.Support.class)
interface RepartitionCentreResponsabiliteBlueprint {

    String name();

    @Option.DefaultLong(0)
    Long servicesCentraux();

    @Option.DefaultLong(0)
    Long servicesDeconcentres();

    @Option.DefaultLong(0)
    Long organismesSousTutelles();

    @Option.DefaultLong(0)
    Long organesTerritoriaux();

    @Option.DefaultLong(0)
    Long autreOrganismesSousTutelles();

    final class Support {

        @Prototype.FactoryMethod
        static RepartitionCentreResponsabilite create(String name, List<Long> repartition) {
            return RepartitionCentreResponsabilite.builder()
                    .name(name)
                    .repartition(repartition)
                    .build();
        }

        @Prototype.BuilderMethod
        static void repartition(RepartitionCentreResponsabilite.BuilderBase<?,?> builder,
                                List<Long> repartitions
                                ){
            Objects.requireNonNull(repartitions);
            int repartitionSize = repartitions.size();
            if(repartitionSize > 5){
                throw new IllegalArgumentException("repartition should be of size 0-5! found" +
                        repartitionSize + ", repartitions " + repartitions);
            }
            for (int i = 0; i < repartitionSize; i++) {
                switch (i) {
                    case 0 -> {
                        builder.servicesCentraux(repartitions.get(i));
                    }
                    case 1 -> {
                        builder.servicesDeconcentres(repartitions.get(i));
                    }
                    case 2 -> {
                        builder.organismesSousTutelles(repartitions.get(i));
                    }
                    case 3 -> {
                        builder.organesTerritoriaux(repartitions.get(i));
                    }
                    case 4 -> {
                        builder.autreOrganismesSousTutelles(repartitions.get(i));
                    }
                }
            }
        }
    }
}

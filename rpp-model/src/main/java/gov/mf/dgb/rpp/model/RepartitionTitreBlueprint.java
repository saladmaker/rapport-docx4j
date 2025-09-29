package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;
import java.util.Objects;

@Prototype.Blueprint
@Prototype.CustomMethods(RepartitionTitreBlueprint.CustomMethods.class)
interface RepartitionTitreBlueprint {

    String name();

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

    final class CustomMethods {
        @Prototype.FactoryMethod
        static RepartitionTitre create(String name, List<Long> repartitions){
            return RepartitionTitre.builder()
                    .name(name)
                    .repartition(repartitions)
                    .build();
        }

        @Prototype.BuilderMethod
        static void repartition(RepartitionTitre.BuilderBase<?, ?> builder, List<Long> repartitions) {
            Objects.requireNonNull(repartitions, "repartitions can not be null!");

            if (repartitions.size() > 7) {
                throw new IllegalArgumentException(
                        "repartition shoul be of size 7 repartition, found size: " + repartitions.size());
            }

            for (int i = 0; i < repartitions.size(); i++) {
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
    }

}

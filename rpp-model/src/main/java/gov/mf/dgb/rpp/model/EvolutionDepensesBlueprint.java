package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;
import java.util.Objects;

@Prototype.Blueprint
@Prototype.CustomMethods(EvolutionDepensesBlueprint.CustomMethods.class)
interface EvolutionDepensesBlueprint {

    String name();

    @Option.DefaultLong(0L)
    Long anneeMoins2();

    @Option.DefaultLong(0L)
    Long anneeMoins1();

    @Option.DefaultLong(0L)
    Long annee();

    @Option.DefaultLong(0L)
    Long anneePlus1();

    @Option.DefaultLong(0L)
    Long anneePlus2();

    default long total(){
        return anneeMoins2() +
                anneeMoins1() +
                annee() +
                anneePlus1() +
                anneePlus2();
    }
    final class CustomMethods{
        @Prototype.FactoryMethod
        static EvolutionDepenses create(String name, List<Long> depenses){
            return EvolutionDepenses.builder()
                    .name(name)
                    .evolution(depenses)
                    .build();
        }
        @Prototype.BuilderMethod
        static void evolution(EvolutionDepenses.BuilderBase<?,?> builder, List<Long> depenses){
            Objects.requireNonNull(depenses);

            var size = depenses.size();

            if ((size < 3) || (size > 5)) {
                throw new IllegalArgumentException("evolution des depenses should be of size 3 to 5, but got:" + depenses.size());
            }

            // set depense from y+2 to y-2
            switch (size) {
                case 3 -> {
                    builder.annee(depenses.get(0));
                    builder.anneePlus1(depenses.get(1));
                    builder.anneePlus2(depenses.get(2));
                }
                case 4 -> {
                    builder.anneeMoins1(depenses.get(0));
                    builder.annee(depenses.get(1));
                    builder.anneePlus1(depenses.get(2));
                    builder.anneePlus2(depenses.get(3));

                }
                case 5 -> {
                    builder.anneeMoins1(depenses.get(0));
                    builder.anneeMoins2(depenses.get(1));
                    builder.annee(depenses.get(2));
                    builder.anneePlus1(depenses.get(3));
                    builder.anneePlus2(depenses.get(4));
                }
            }
        }
    }
}

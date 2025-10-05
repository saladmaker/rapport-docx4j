package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Prototype;
import io.helidon.builder.api.Option;

import java.util.List;
import java.util.Objects;

@Prototype.Blueprint
@Prototype.CustomMethods(PostesOuvertMassSalarialBlueprint.CustomMethods.class)
interface PostesOuvertMassSalarialBlueprint {

    CentreResponsabilite serviceType();

    @Option.DefaultLong(0L)
    Long postesAnneeMoins2();

    @Option.DefaultLong(0L)
    Long postesAnneeMoins1();

    Long postesAnnee();

    @Option.DefaultLong(0L)
    Long massSalarialAnneeMoins2();

    @Option.DefaultLong(0L)
    Long massSalarialAnneeMoins1();

    Long massSalarialAnnee();

    default Long nombre() {
        return postesAnnee() - postesAnneeMoins1();
    }

    default Double variationPostes() {
        return nombre().doubleValue() * 100 / postesAnnee();
    }

    default Long variationSalarial(){
        return massSalarialAnnee() - massSalarialAnneeMoins1();
    }
    final class CustomMethods {
        @Prototype.BuilderMethod
        static void evolutionPostes(PostesOuvertMassSalarial.BuilderBase<?, ?> builderBase, List<Long> postes) {
            //invariants check
            Objects.requireNonNull(postes);
            int size = postes.size();
            if ((0 == size) || size > 3) {
                throw new IllegalArgumentException("evolution des postes size must be of 1-3, found" + size);
            }

            switch (size) {
                case 3 -> {
                    builderBase.postesAnneeMoins2(postes.get(0));
                    builderBase.postesAnneeMoins1(postes.get(1));
                    builderBase.postesAnnee(postes.get(2));

                }
                case 2 -> {
                    builderBase.postesAnneeMoins1(postes.get(0));
                    builderBase.postesAnnee(postes.get(1));
                }
                case 1 -> {
                    builderBase.postesAnnee(postes.getFirst());
                }
            }
        }

        @Prototype.BuilderMethod
        static void evolutionSalarial(PostesOuvertMassSalarial.BuilderBase<?, ?> builderBase, List<Long> salaires) {
            //invariants check
            Objects.requireNonNull(salaires);
            int size = salaires.size();
            if ((0 == size) || size > 3) {
                throw new IllegalArgumentException("evolution des postes size must be of 1-3, found" + size);
            }

            switch (size) {
                case 3 -> {
                    builderBase.massSalarialAnneeMoins2(salaires.get(0));
                    builderBase.massSalarialAnneeMoins1(salaires.get(1));
                    builderBase.massSalarialAnnee(salaires.get(2));

                }
                case 2 -> {
                    builderBase.massSalarialAnneeMoins1(salaires.get(0));
                    builderBase.massSalarialAnnee(salaires.get(1));
                }
                case 1 -> {
                    builderBase.massSalarialAnnee(salaires.getFirst());
                }
            }
        }

        @Prototype.FactoryMethod
        static PostesOuvertMassSalarial create(CentreResponsabilite centreResponsabilite, List<Long> postes, List<Long> salaires) {

            return PostesOuvertMassSalarial.builder()
                    .serviceType(centreResponsabilite)
                    .evolutionPostes(postes)
                    .evolutionSalarial(salaires)
                    .build();
        }
    }
}

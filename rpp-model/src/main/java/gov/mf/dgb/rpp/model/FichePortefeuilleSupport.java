package gov.mf.dgb.rpp.model;


import io.helidon.builder.api.Prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

final class FichePortefeuilleSupport {

    static final class BuilderDecorator implements Prototype.BuilderDecorator<FichePortefeuille.BuilderBase<?,?>>{

        @Override
        public void decorate(FichePortefeuille.BuilderBase<?, ?> target) {
            if(target.repartitionProgrammes().isEmpty()){
                throw new IllegalStateException("at least one programme should be present in #repartitionProgrammes!");
            }
            uniqueNessCheck(target.repartitionProgrammes(), RepartitionProgramme::name,
                    "duplicate programme names in #repartitionProgrammes!");

            if(target.versionBRepartitionProgrammes().isEmpty()){
                throw new IllegalStateException("at least one programme should be present in #versionBRepartitionProgrammes!");
            }
            uniqueNessCheck(target.versionBRepartitionProgrammes(), RepartitionProgramme::name,
                    "duplicate programme names in #versionBRepartitionProgrammes!");
            if(target.repartitionProgrammes().size() != target.versionBRepartitionProgrammes().size()){
                throw new IllegalStateException("size of #repartitionProgrammes != #versionBRepartitionProgrammes!");
            }

            uniqueNessCheck(target.repartitionProgrammeCentreResps(), RepartitionCentreResponsabilite::name,
                    "duplicate programme names in #repartitionProgrammeCentreResps!");
            uniqueNessCheck(target.repartitionProgrammesTitres(), RepartitionTitre::name,
                    "duplicate programme names in #repartitionProgrammesTitres!");
            uniqueNessCheck(target.repartitionPortefeuilleCentreResponTitres(), RepartitionCentreResponsabiliteTitre::serviceType,
                    "duplicate service type in #repartitionPortefeuilleCentreResponTitres!");
            uniqueNessCheck(target.evolutionDepensesProgrammes(), Evolution::name,
                    "duplicate programme names in #evolutionDepensesProgrammes!");
        }

        private static <T> void uniqueNessCheck(List<T> element, Function<T, ?> mapper, String message){
            int size = element.size();
            int uniqueSize = element.stream()
                    .map(mapper)
                    .collect(Collectors.toSet())
                    .size();
            if(uniqueSize != size){
                throw new IllegalStateException("invalid state: " + message);
            }
        }
    }

    @Prototype.BuilderMethod
    static void addEvolutionPostesServicesCentraux(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionPostesService(CentreResponsabilite.SERVICES_CENTRAUX, builder, evolution);
    }

    @Prototype.BuilderMethod
    static void addEvolutionPostesServicesDeconcentres(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionPostesService(CentreResponsabilite.SERVICES_DECONCENTRES, builder, evolution);
    }

    @Prototype.BuilderMethod
    static void addEvolutionPostesOrganismesSousTutelle(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionPostesService(CentreResponsabilite.ORGANISMES_SOUS_TUTELLE, builder, evolution);
    }
    @Prototype.BuilderMethod
    static void addEvolutionPostesOrganesTerritoriaux(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionPostesService(CentreResponsabilite.ORGANES_TERRITORIAUX, builder, evolution);
    }
    @Prototype.BuilderMethod
    static void addEvolutionAutreServicesSousTutelle(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionPostesService(CentreResponsabilite.AUTRE_SERVICES_SOUS_TUTELLE, builder, evolution);
    }
    private static void addEvolutionPostesService(CentreResponsabilite centreResponsabilite,
                                                  FichePortefeuille.BuilderBase<?,?> builder,
                                                  List<Long> evolution){
        Objects.requireNonNull(evolution);

        String serviceName = centreResponsabilite.name();
        var evolutions = builder.evolutionPostesServices();
        boolean exists = evolutions.stream()
                .map(Evolution::name)
                .anyMatch(serviceName::equals);
        if(exists){
            throw new IllegalArgumentException(serviceName + " evolution postes already exist!");
        }

        List<Evolution> appendedEvolutions = new ArrayList<>(evolutions);
        appendedEvolutions.add(Evolution.create(serviceName, evolution));
        builder.evolutionPostesServices(appendedEvolutions);
    }

}

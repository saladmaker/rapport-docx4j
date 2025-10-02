package gov.mf.dgb.rpp.model;


import io.helidon.builder.api.Prototype;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class FichePortefeuilleSupport {

    final class BuilderDecorator implements Prototype.BuilderDecorator<FichePortefeuille.BuilderBase<?,?>>{

        @Override
        public void decorate(FichePortefeuille.BuilderBase<?, ?> target) {
            //validate programme name uniqueness in table: version B, version A
            //validate
        }
    }

    @Prototype.BuilderMethod
    static void addEvolutionPostesServicesCentraux(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionService(CentreResponsabilite.SERVICES_CENTRAUX, builder, evolution);
    }

    @Prototype.BuilderMethod
    static void addEvolutionPostesServicesDeconcentres(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionService(CentreResponsabilite.SERVICES_DECONCENTRES, builder, evolution);
    }

    @Prototype.BuilderMethod
    static void addEvolutionPostesOrganismesSousTutelle(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionService(CentreResponsabilite.ORGANISMES_SOUS_TUTELLE, builder, evolution);
    }
    @Prototype.BuilderMethod
    static void addEvolutionPostesOrganesTerritoriaux(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionService(CentreResponsabilite.ORGANES_TERRITORIAUX, builder, evolution);
    }
    @Prototype.BuilderMethod
    static void addEvolutionAutreServicesSousTutelle(FichePortefeuille.BuilderBase<?,?> builder, List<Long> evolution){
        addEvolutionService(CentreResponsabilite.AUTRE_SERVICES_SOUS_TUTELLE, builder, evolution);
    }
    private static void addEvolutionService(CentreResponsabilite centreResponsabilite,
                                            FichePortefeuille.BuilderBase<?,?> builder,
                                            List<Long> evolution){

        Objects.requireNonNull(evolution);

        String serviceName = centreResponsabilite.name();
        var evolutions = builder.evolutionPostesServices();
        boolean exists = evolutions.stream()
                .map(Evolution::name)
                .anyMatch(serviceName::equals);
        if(exists){
            throw new IllegalArgumentException(serviceName + " evolution post already exist");
        }
        List<Evolution> appendedEvolutions = new ArrayList<>(evolutions);
        appendedEvolutions.add(Evolution.create(serviceName, evolution));
        builder.evolutionPostesServices(appendedEvolutions);
    }

}

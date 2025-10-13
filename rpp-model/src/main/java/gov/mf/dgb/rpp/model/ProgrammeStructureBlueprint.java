package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.Set;

@Prototype.Blueprint(createEmptyPublic = false)
interface ProgrammeStructureBlueprint {

    @Option.Required
    String name();

    @Option.Singular
    Set<String> servicesCentraux();

    @Option.Singular
    Set<String> servicesDeconcentres();

    @Option.Singular
    Set<String> organismesSousTutelles();

    @Option.Singular
    Set<String> organesTerritoriaux();

    @Option.Singular
    Set<String> autreServiceSousTutelles();
}

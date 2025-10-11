package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

import java.util.List;

@Prototype.Blueprint(createEmptyPublic = false)
@Prototype.CustomMethods(MissionBlueprint.CustomMethods.class)
interface MissionBlueprint {

    @Option.Required
    String mission();

    @Option.Singular
    List<String> subMissions();


    final class CustomMethods{
        @Prototype.FactoryMethod
        static Mission create(String mission){
            return Mission.builder()
                    .mission(mission)
                    .build();
        }
        @Prototype.FactoryMethod
        static Mission create(String mission, List<String> subMission){
            return Mission.builder()
                    .mission(mission)
                    .subMissions(subMission)
                    .build();
        }
    }
}

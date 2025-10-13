package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Set;

interface ViewCartographie extends Viewable{
    List<ProgrammeStructure> delegates();

    default List<ProgrammeStructureView> programmes(){
        return delegates().stream()
                .map(ProgrammeStructureView::new)
                .toList();
    }
    static ViewCartographie of(GenerationContext context, CartographieProgrammesPortefeuilleBlueprint delegate){
        return switch (context.direction()){
            case LTR -> new ViewCartographieFR(context, delegate.programmeStructures());
            case RTL -> new ViewCartographieAR(context, delegate.programmeStructures());
        };
    }
    @JStache(path = "templates/section1/cartographie.fr.mustache")
    record ViewCartographieFR(GenerationContext context, List<ProgrammeStructure> delegates) implements ViewCartographie{
    }
    @JStache(path = "templates/section1/cartographie.ar.mustache")
    record ViewCartographieAR(GenerationContext context, List<ProgrammeStructure> delegates) implements ViewCartographie{
    }
    record ProgrammeStructureView(ProgrammeStructure delegate){
        String name(){
            return delegate().name();
        }

        Set<String> servicesCentraux(){
            return delegate.servicesCentraux();
        }

        Set<String> servicesDeconcentres(){
            return delegate.servicesDeconcentres();
        }

        Set<String> organismeSousTutelles(){
            return delegate.organismesSousTutelles();
        }

        Set<String> organesTerritoriaux(){
            return delegate.organesTerritoriaux();
        }
    }
}

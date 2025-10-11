package gov.mf.dgb.rpp.model;

import io.jstach.jstache.JStache;

import java.util.List;
import java.util.Objects;

sealed interface ViewFicheProgramme {

    FicheProgrammeBlueprint delegate();

    default FicheProgrammeView programme(){
        return new FicheProgrammeView(delegate());
    }

    static ViewFicheProgramme of(GenerationContext context, FicheProgrammeBlueprint delegate){
        return new ViewFicheProgrammeFR(delegate);
    }

    @JStache(path = "templates/section2/fiche.programme.fr.mustache")
    record ViewFicheProgrammeFR(FicheProgrammeBlueprint delegate) implements ViewFicheProgramme {
    }

    record FicheProgrammeView(FicheProgrammeBlueprint delegate) {
        public FicheProgrammeView {
            Objects.requireNonNull(delegate);
        }

        String responsable() {
            return delegate.gestionnaire();
        }

        List<String> axesStrategiques() {
            return delegate.axesStrategiques();
        }

        List<String> objectivesStrategiques() {
            return delegate.objectivesStrategiques();
        }

        List<String> initiativesImportantes() {
            return delegate.initiativesImportantes();
        }

    }
}

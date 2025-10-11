package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Option;
import io.jstach.jstache.JStache;

import java.util.List;

interface ViewJustificationDepense {
    FicheProgrammeBlueprint delegate();
    default JustificationDepenseView programme(){
        return new JustificationDepenseView(delegate());
    }
    static ViewJustificationDepense of(GenerationContext context, FicheProgrammeBlueprint delegate){
        return new ViewJustificationDepenseFR(delegate);
    }
    @JStache(path = "templates/section2/justification.depense.fr.mustache")
    record ViewJustificationDepenseFR(FicheProgrammeBlueprint delegate)
            implements ViewJustificationDepense{}

    record JustificationDepenseView(FicheProgrammeBlueprint delegate){
        List<String> justificationDepensePersonnel(){
            return delegate.justificationDepensePersonnel();
        }

        List<String> justificationDepenseFonctionnementServices(){
            return delegate.justificationDepenseFonctionnementServices();
        }

        List<String> justificationDepenseInvestissements(){
            return delegate.justificationDepenseInvestissements();
        }

        List<String> justificationDepenseTransferts(){
            return delegate.justificationDepenseTransferts();
        }

    }
}

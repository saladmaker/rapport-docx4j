package rpp.test.generators;

import gov.mf.dgb.rpp.model.FicheProgramme;
import gov.mf.dgb.rpp.model.GenerationContext;
import gov.mf.dgb.rpp.model.LanguageDirection;
import gov.mf.dgb.rpp.model.RepartitionCentreResponsabiliteTitre;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.util.List;
import java.util.Map;

public class FrenchModelTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        FicheProgramme ficheProgramme = FicheProgramme.builder()
                .counter(1)
                .name("Impots")
                .gestionnaire("DG")
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofCentraux(List.of(600L, 550L, 330L, 222L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofDeconcentres(List.of(321L, 333L, 555L, 655L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofOrganismesSousTutelle(List.of(0L, 0L, 333L, 3332L)))
                .build();
        ficheProgramme.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of()));

    }
}

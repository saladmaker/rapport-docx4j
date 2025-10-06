package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.time.Year;
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
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.SERVICES_CENTRAUX,
                                List.of(500L, 600L, 700L), List.of(100000L, 120000L, 140000L)))
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.SERVICES_DECONCENTRES,
                                List.of(430L, 650L, 888L), List.of(130000L, 129333L, 126000L)))
                .build();
        ficheProgramme.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of(), Year.of(2025)));

    }
}

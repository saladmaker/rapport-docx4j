package rpp.test.generators;

import gov.mf.dgb.rpp.model.FicheProgramme;
import gov.mf.dgb.rpp.model.GenerationContext;
import gov.mf.dgb.rpp.model.LanguageDirection;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.util.Map;

public class FrenchModelTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        FicheProgramme ficheProgramme = FicheProgramme.builder()
                .counter(1)
                .name("Impots")
                .build();
        ficheProgramme.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of()));
    }
}

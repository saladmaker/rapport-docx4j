package rpp.test.generators;

import gov.mf.dgb.rpp.model.CartographieProgrammesPortefeuille;
import gov.mf.dgb.rpp.model.GenerationContext;
import gov.mf.dgb.rpp.model.LanguageDirection;
import gov.mf.dgb.rpp.model.ProgrammeStructure;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.time.Year;
import java.util.Map;
import java.util.Set;

public class CartographieArabicTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        CartographieProgrammesPortefeuille carto = CartographieProgrammesPortefeuille.builder()
                .addProgrammeStructure(ProgrammeStructure.builder()
                        .name("البرنامج 001 - تحديث الإدارة")
                        .addServicesCentraux(Set.of("الأمانة العامة", "المفتشية العامة"))
                        .addServicesDeconcentres(Set.of("المديرية الجهوية الجزائر", "المديرية الجهوية وهران"))
                        .addOrganismesSousTutelles(Set.of("الوكالة الوطنية للرقمنة", "المعهد العالي للإدارة العمومية"))
                        .addOrganesTerritoriaux(Set.of("مديرية ولاية الجزائر", "مديرية ولاية وهران"))
                        .build())
                .addProgrammeStructure(ProgrammeStructure.builder()
                        .name("البرنامج 002 - التنمية المستدامة")
                        .addServicesCentraux("المديرية العامة للبيئة")
                        .addServicesDeconcentres(Set.of("المديرية الجهوية عنابة", "المديرية الجهوية تلمسان"))
                        .addOrganismesSousTutelle("المكتب الوطني للغابات")
                        .addOrganesTerritoriaux("المحافظة على المناطق الرطبة")
                        .build())
                .build();
        carto.write(document, GenerationContext.of(LanguageDirection.RTL,document, Map.of(), Year.of(2025)));

    }
}

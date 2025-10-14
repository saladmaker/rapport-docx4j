package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArabicModel implements DocumentGenerator {
    static String intro = """
            يتمثل قطاع التكوين والتعليم المهنيين في تلبية الطلب الاجتماعي على التكوين المعبر عنه من طرف الشباب المتخرجين من التعليم الوطني،
            وكذلك في تلبية حاجيات المؤسسات وسوق العمل من التأهيل المهني.
            
            يشمل التكوين المقدم 23 شعبة مهنية، وكل شعبة تنقسم بدورها إلى تخصصات (495).
            
            يستند تنظيم وعمل القطاع إلى القانون الإطار رقم 08-07 المؤرخ في 23 فبراير 2008،
            والمتعلق بالقانون التوجيهي للتكوين والتعليم المهنيين.
            """;

    @Override
    public void generate(WordprocessingMLPackage document) {
        byte[] image;
        try {
            image = ArabicModel.class.getClassLoader().getResourceAsStream("ORGANIGRAME.jpg").readAllBytes();
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }

        var au = AuSujet.builder()
                .laMission(LaMission.builder()
                        .intro(intro)
                        .addMission(Mission.builder()
                                .mission("المالية العمومية")
                                .addSubMission("الجباية")
                                .addSubMission("الجمارك")
                                .addSubMission("الأملاك الوطنية والشؤون العقارية")
                                .addSubMission("النفقات العمومية، الميزانية والمحاسبة العمومية")
                                .build()
                        )
                        .addMission(Mission.create("النقد"))
                        .addMission(Mission.create("الادخار، القرض والتأمينات الاقتصادية"))
                        .addMission(Mission.create("التدخلات المالية للدولة"))
                        .addMission(Mission.create("السياسة الوطنية في مجال الاستدانة الخارجية"))
                        .addMission(Mission.create("مراقبة الصرف"))
                        .addMission(Mission.create("الرقابة المالية المتعلقة باستعمال اعتمادات ميزانية الدولة وموارد الخزينة العمومية"))
                        .addMission(Mission.create("العلاقات الاقتصادية والمالية الخارجية"))
                        .build()
                )
                .leMinistere(LeMinistere.builder()
                        .image(image)
                        .build())
                .cartographie(CartographieProgrammesPortefeuille.builder()
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
                        .build())
                .fichePortefeuille(FichePortefeuille.builder()
                .addRepartitionProgramme(new RepartitionProgramme("التكوين المهني", 19506191000L, 20143691000L))
                .addRepartitionProgramme(new RepartitionProgramme("التعليم المهني", 540000000L, 622000000L))
                .addRepartitionProgramme(new RepartitionProgramme("الادارة العامة", 98536426000L, 97250926000L))
                .addVersionBRepartitionProgramme(
                        new RepartitionProgramme("التكوين المهني", 109366562L, 110004062L))
                .addVersionBRepartitionProgramme(
                        new RepartitionProgramme("التعليم المهني", 2436520L, 2518520L))
                .addVersionBRepartitionProgramme(
                        new RepartitionProgramme("الادارة العامة", 6779535L, 5494035L))
                .addRepartitionProgrammeCentreResp(
                        RepartitionCentreResponsabilite.create("التكوين المهني",
                                List.of(360311L, 10655000L, 89737251L, 8614000L)))
                .addRepartitionProgrammeCentreResp(
                        RepartitionCentreResponsabilite.create("التعليم المهني",
                                List.of(64000L, 228000L, 1856520L, 288000L)))
                .addRepartitionProgrammeCentreResp(
                        RepartitionCentreResponsabilite.create("الادارة العامة",
                                List.of(1723123L, 4863195L, 138717L, 54500L)))

                .addRepartitionProgrammesTitre(RepartitionTitre.create("التكوين المهني",
                        List.of(11076119L, 2683352L, 3217628L, 2962406L)))
                .addRepartitionProgrammesTitre(RepartitionTitre.create("التعليم المهني",
                        List.of(4573433L, 427752L, 2816372L, 1024000L)))
                .addRepartitionProgrammesTitre(RepartitionTitre.create("الادارة العامة",
                        List.of(4497238L, 558043L, 466000L, 79326L)))
                .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofCentraux(List.of(553000L, 415_934L, 1_178_500L, 0L)))
                .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofDeconcentres(List.of(3_881_195L, 380_000L, 11_485_000L, 0L)))
                .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganismesSousTutelle(List.of(76_606_938L, 5_875_098L, 0L, 9_250_452L)))
                .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganesTerritoriaux(List.of(0L, 0L, 8_956_500L, 0L)))
                .addEvolutionDepensesProgramme(Evolution.create("التكوين المهني", List.of(76_427_663L, 90_012_506L, 109_366_562L, 111_091_201L, 113_327_046L)))
                .addEvolutionDepensesProgramme(Evolution.create("التعليم المهني", List.of(1_545_505L, 2023_610L, 2_436_520L, 2_460_022L, 2_504_707L)))
                .addEvolutionDepensesProgramme(Evolution.create("الادارة العامة", List.of(5_118_443L, 7_674_181L, 6_779_535L, 7_542_716L, 7_154_077L)))
                .addEvolutionPostesServicesCentraux(List.of(394L, 394L, 394L, 394L, 394L))
                .addEvolutionPostesServicesDeconcentres(List.of(2_829L, 2_979L, 2_979L, 2_979L, 2_979L))
                .addEvolutionPostesOrganismesSousTutelle(List.of(71_626L, 72_716L, 72_716L, 73_526L, 74_036L))
                .build())
                .build();
        au.write(document, GenerationContext.of(LanguageDirection.RTL, document, Map.of(), Year.of(2025)));

    }
}

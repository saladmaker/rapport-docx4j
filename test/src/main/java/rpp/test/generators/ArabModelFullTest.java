package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Map;

public class ArabModelFullTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        String intro = """
                يتمثل قطاع التكوين والتعليم المهنيين في تلبية الطلب الاجتماعي على التكوين المعبر عنه من طرف الشباب المتخرجين من التعليم الوطني،
                وكذلك في تلبية حاجيات المؤسسات وسوق العمل من التأهيل المهني.
                
                يشمل التكوين المقدم 23 شعبة مهنية، وكل شعبة تنقسم بدورها إلى تخصصات (495).
                
                يستند تنظيم وعمل القطاع إلى القانون الإطار رقم 08-07 المؤرخ في 23 فبراير 2008،
                والمتعلق بالقانون التوجيهي للتكوين والتعليم المهنيين.
                """;

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
                .fichePortefeuille(FichePortefeuille.builder()
                        .addRepartitionProgramme(new RepartitionProgramme("التكوين المهني", 1950_6191_000L, 20143691000L))
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
                                        List.of(360_311L, 10_655_000L, 89_737_251L, 8_614_000L, 243_323L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("التعليم المهني",
                                        List.of(64_000L, 228_000L, 1_856_520L, 288_000L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("الادارة العامة",
                                        List.of(1_723_123L, 4_863_195L, 138_717L, 54_500L)))

                        .addRepartitionProgrammesTitre(RepartitionTitre.create("التكوين المهني",
                                List.of(11_076_119L, 2_683_352L, 3_217_628L, 2_962_406L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("التعليم المهني",
                                List.of(4_573_433L, 427_752L, 2_816_372L, 1_024_000L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("الادارة العامة",
                                List.of(4_497_238L, 558_043L, 466_000L, 79_326L)))
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
        au.write(document, GenerationContext.of(LanguageDirection.RTL, document, Map.of(), Year.of(2026)));
    }
}

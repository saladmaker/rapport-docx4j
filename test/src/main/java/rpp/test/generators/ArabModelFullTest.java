package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArabModelFullTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        String intro = """
                يتمثل قطاع التكوين والتعليم المهنيين في تلبية الطلب الاجتماعي على التكوين المعبر عنه من طرف الشباب المتخرجين من التعليم الوطني،
                وكذلك في تلبية حاجيات المؤسسات وسوق العمل من التأهيل المهني.
                
                يشمل التكوين المقدم 23 شعبة مهنية، وكل شعبة تنقسم بدورها إلى تخصصات (495).
                
                يستند تنظيم وعمل القطاع إلى القانون الإطار رقم 08-07 المؤرخ في 23 فبراير 2008،
                والمتعلق بالقانون التوجيهي للتكوين والتعليم المهنيين.
                """
                .replaceAll("\\r?\\n", " ");

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
                        .addRepartitionProgramme(new RepartitionProgramme("التدخلات الاقتصادية للدولة", 323_931_555L, 194_970_000L))
                        .addRepartitionProgramme(new RepartitionProgramme("الضرائب", 27_918_492L, 25_217_992L))
                        .addRepartitionProgramme(new RepartitionProgramme("الميزانية", 661_494_700L, 665_764_155L))
                        .addRepartitionProgramme(new RepartitionProgramme("املاك الدولة", 12_282_568L, 12_122_568L))
                        .addRepartitionProgramme(new RepartitionProgramme("المحاسبة", 17_649_681L, 16_315_181L))
                        .addRepartitionProgramme(new RepartitionProgramme("الجمارك", 22_851_220L, 19_948_220L))
                        .addRepartitionProgramme(new RepartitionProgramme("التفتيش المالي", 967_574L, 902_574L))
                        .addRepartitionProgramme(new RepartitionProgramme("الادارة العامة", 3_794_600L, 3_614_600L))
                        //
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("التدخلات الاقتصادية للدولة", 323_931_555L, 194_970_000L))
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("الضرائب", 27_918_492L, 25_217_992L))
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("الميزانية", 661_494_700L, 665_764_155L))
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("املاك الدولة", 12_282_568L, 12_122_568L))
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("المحاسبة", 17_649_681L, 16_315_181L))
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("الجمارك", 22_851_220L, 19_948_220L))
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("التفتيش المالي", 967_574L, 902_574L))
                        .addVersionBRepartitionProgramme(new RepartitionProgramme("الادارة العامة", 3_794_600L, 3_614_600L))
                        //programme-centre de responsabilite
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("التدخلات الاقتصادية للدولة",
                                        List.of(323_931_555L, 0L, 0L, 0L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("الضرائب",
                                        List.of(3_731_524L, 22_177_968L, 0L, 2_009_000L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("الميزانية",
                                        List.of(651_448_619L, 9_508_081L, 0L, 538_000L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("املاك الدولة",
                                        List.of(277_035L, 11_475_033L, 0L, 530_500L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("المحاسبة",
                                        List.of(2_021_943L, 14_961_238L, 0L, 666_500L)))
                        //programme-titre
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("التدخلات الاقتصادية للدولة",
                                List.of(0L, 0L, 0L, 128_961_555L, 194_970_000L, 0L, 0L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("الضرائب",
                                List.of(22_589_586L, 1_200_906L, 4_127_000L, 1_000L, 0L, 0L, 0L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("الميزانية",
                                List.of(10_103_106L, 424_174L, 1_259_000L, 0L, 0L, 0L, 649_708_420L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("أملاك الدولة",
                                List.of(11_201_682L, 550_386L, 530_500L, 0L, 0L, 0L, 0L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("المسح العقاري",
                                List.of(3_315_334L, 34_666L, 362_500L, 0L, 0L, 0L, 0L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("المحاسبة",
                                List.of(14_751_153L, 964_028L, 1_881_500L, 53_000L, 0L, 0L, 0L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("الجمارك",
                                List.of(17_808_465L, 1_333_755L, 3_709_000L, 0L, 0L, 0L, 0L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("مفتشية المالية",
                                List.of(834_440L, 64_634L, 68_500L, 0L, 0L, 0L, 0L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("الإدارة العامة",
                                List.of(3_105_190L, 506_410L, 180_000L, 3_000L, 0L, 0L, 0L)))

                        // titre-centre de responsabilite
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofCentraux(
                                List.of(27_319_682L, 1_610_353L, 6_962_500L, 129_018_555L, 194_970_000L, 0L, 649_708_420L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofDeconcentres(
                                List.of(55_604_294L, 3_381_386L, 55_000L, 0L, 0L, 0L, 0L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganismesSousTutelle(
                                List.of(784_980L, 87_220L, 0L, 0L, 0L, 0L, 0L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganesTerritoriaux(
                                List.of(0L, 0L, 5_100_500L, 0L, 0L, 0L, 0L)))

                        .addEvolutionDepensesProgramme(Evolution.create("التدخلات الاقتصادية للدولة",
                                List.of(0L, 323_931_555L, 400_314_615L, 409_361_725L)))
                        .addEvolutionDepensesProgramme(Evolution.create("الضرائب",
                                List.of(0L, 27_918_492L, 34_501_672L, 35_281_410L)))
                        .addEvolutionDepensesProgramme(Evolution.create("الميزانية",
                                List.of(0L, 661_494_700L, 793_413_811L, 811_761_854L)))
                        .addEvolutionDepensesProgramme(Evolution.create("الأملاك الوطنية",
                                List.of(0L, 12_282_568L, 15_178_797L, 15_521_838L)))
                        .addEvolutionDepensesProgramme(Evolution.create("المسح العقاري",
                                List.of(0L, 3_712_500L, 4_587_907L, 4_691_594L)))
                        .addEvolutionDepensesProgramme(Evolution.create("المحاسبة",
                                List.of(0L, 17_649_681L, 21_811_475L, 22_304_415L)))
                        .addEvolutionDepensesProgramme(Evolution.create("الجمارك",
                                List.of(0L, 22_851_220L, 28_239_537L, 28_877_751L)))
                        .addEvolutionDepensesProgramme(Evolution.create("مفتشية المالية",
                                List.of(0L, 967_574L, 1_195_727L, 1_222_751L)))
                        .addEvolutionDepensesProgramme(Evolution.create("الإدارة العامة",
                                List.of(0L, 3_794_600L, 4_689_366L, 4_795_346L)))

                        .addEvolutionPostesServicesCentraux(List.of(394L, 394L, 394L, 394L, 394L))
                        .addEvolutionPostesServicesDeconcentres(List.of(2_829L, 2_979L, 2_979L, 2_979L, 2_979L))
                        .addEvolutionPostesOrganismesSousTutelle(List.of(71_626L, 72_716L, 72_716L, 73_526L, 74_036L))
                        .build())
                .build();
        au.write(document, GenerationContext.of(LanguageDirection.RTL, document, Map.of(), Year.of(2026)));
    }
}

package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.time.Year;
import java.util.List;
import java.util.Map;

public class ArabicModelTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        FicheProgramme ficheProgramme = FicheProgramme.builder()
                .counter(1)
                .name("التكوين المهني")
                .gestionnaire("مدير تنظيم التكوين المهني ومتابعته")
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofCentraux(List.of(600L, 550L, 330L, 222L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofDeconcentres(List.of(321L, 333L, 555L, 655L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofOrganismesSousTutelle(List.of(0L, 0L, 333L, 3332L)))
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("التكوين المهني الأولي",
                        List.of(74046542L, 5869433L, 19150000L, 9176540L)))
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("التكوين المتواصل والتكوين عن بعد",
                        List.of(0L, 8000L, 0L, 9880L)))
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("الهندسة البيداغوجية",
                        List.of(876_320L, 110_847L, 119_000L, 0L)))
                .addEvolutionDepenseSousProgramme(Evolution.create("التكوين المهني الأولي",
                        List.of(75_540_668L, 89_044_586L, 108_242_515L, 109_566_169L, 111_779_553L)))
                .addEvolutionDepenseSousProgramme(Evolution.create("التكوين المتواصل والتكوين عن بعد",
                        List.of(12995L, 37_889L, 17880L, 18140L, 18_342L)))
                .addEvolutionDepenseSousProgramme(Evolution.create("الهندسة البيداغوجية",
                        List.of(874_000L, 930_031L, 1_106_167L, 1_506_892L, 1_529_151L)))
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.SERVICES_CENTRAUX,
                                List.of(500L, 600L, 700L), List.of(100000L, 120000L, 140000L)))
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.SERVICES_DECONCENTRES,
                                List.of(430L, 650L, 888L), List.of(130000L, 129333L, 126000L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("مراكز التكوين المهني والتمهين",
                        List.of(48_386_156L, 52_909_464L, 66_773_595L, 67_500_000L, 68_100_092L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("المعاهد الوطنية المتخصصة في التكوين المهني",
                        List.of(15_734_830L, 18_107_942L, 21_974_609L, 22_400_000L, 22_800_072L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("المعهد الوطني في التكوين والتعليم المهنيين",
                        List.of(133_857L, 123_142L, 151_617L, 152_000L, 152_000L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("معاهد التكوين والتعليم المهنيين",
                        List.of(609_447L, 609_447L, 778_048L, 810_000L, 819_600L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("مركز الوطني ل لامتحانات والمسابقات لقطاع التكوين والتعليم المهنيين",
                        List.of(0L, 0L, 49_500L, 49_500L, 49_500L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("الديوان الوطني لتطوير التكوين المتواصل و ترقيته",
                        List.of(3_040L, 3_040L, 3_040L, 3_040L, 3_040L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("المركز الوطني للتكوين المهني عن بعد",
                        List.of(6_840L, 6_840L, 6_840L, 6_840L, 6_840L)))
                .addEvolutionDepenseOSTPartType(Evolution.create("مؤسسة عمومية ذات طابع اداري", List.of(64_864_290L, 71_749_995L, 89_737_369L, 90_911_500L, 91_921_264L)))
                .addEvolutionDepenseOSTPartType(Evolution.create("مؤسسة عمومية ذات طابع صناعي وتجاري", List.of(9880L, 9880L, 9880L, 9880L, 9880L)))
                .build();
        ficheProgramme.write(document, GenerationContext.of(LanguageDirection.RTL, document, Map.of(), Year.of(2025)));
    }
}

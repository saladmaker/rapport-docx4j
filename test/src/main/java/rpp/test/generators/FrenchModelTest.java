package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;

public class FrenchModelTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        FicheProgramme ficheProgramme = FicheProgramme.builder()
                .counter(1)
                .name("Impots")
                .gestionnaire("Directeur de " +
                        "l’organisation et du suivi de la formation " +
                        "professionnelle")
                //repartition programme titre-centre responsabilite
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofCentraux(List.of(600L, 550L, 330L, 222L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofDeconcentres(List.of(321L, 333L, 555L, 655L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofOrganismesSousTutelle(List.of(0L, 0L, 333L, 3332L)))
                //repartition sous-programme titre
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("Formation professionnelle initiale",
                        List.of(74046542L, 5869433L, 19150000L, 9176540L)))
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("Formation continue et à distance",
                        List.of(0L, 8000L, 0L, 9880L)))
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("Ingénierie pédagogique de la formation professionnelle",
                        List.of(876_320L, 110_847L, 119_000L, 0L)))
                //evolution depenses
                .addEvolutionDepenseSousProgramme(Evolution.create("Formation professionnelle initiale",
                        List.of(75_540_668L, 89_044_586L, 108_242_515L, 109_566_169L, 111_779_553L)))
                .addEvolutionDepenseSousProgramme(Evolution.create("Formation continue et à distance",
                        List.of(12995L, 37_889L, 17880L, 18140L, 18_342L)))
                .addEvolutionDepenseSousProgramme(Evolution.create("Ingénierie pédagogique de la formation professionnelle",
                        List.of(874_000L, 930_031L, 1_106_167L, 1_506_892L, 1_529_151L)))
                // evolution postes ouvert mass salariale
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.SERVICES_CENTRAUX,
                                List.of(500L, 600L, 700L), List.of(100000L, 120000L, 140000L)))
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.SERVICES_DECONCENTRES,
                                List.of(430L, 650L, 888L), List.of(130000L, 129333L, 126000L)))
                //evolution depenses OST
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("Centres de formation professionnelle et d'apprentissage",
                        List.of(48_386_156L, 52_909_464L, 66_773_595L, 67_500_000L, 68_100_092L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("Instituts nationaux spécialisés de formation professionnelle",
                        List.of(15_734_830L, 18_107_942L, 21_974_609L, 22_400_000L, 22_800_072L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("Institut National de la Formation et de l’Enseignement Professionnels",
                        List.of(133_857L, 123_142L, 151_617L, 152_000L, 152_000L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("Instituts de Formation et d’Enseignement Professionnels",
                        List.of(609_447L, 609_447L, 778_048L, 810_000L, 819_600L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("Centre National des Examens et Concours de la Formation et d’Enseignement Professionnels",
                        List.of(0L, 0L, 49_500L, 49_500L, 49_500L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("Office national de développement et de promotion de la Formation Professionnelle continue",
                        List.of(3_040L, 3_040L, 3_040L, 3_040L, 3_040L)))
                .addEvolutionDepensesOrganismesSousTutelle(Evolution.create("Centre National Enseignement Professionnel à Distance",
                        List.of(6_840L, 6_840L, 6_840L, 6_840L, 6_840L)))
                //par type OST
                .addEvolutionDepenseOSTPartType(Evolution.create("EPA", List.of(64_864_290L, 71_749_995L, 89_737_369L, 90_911_500L, 91_921_264L)))
                .addEvolutionDepenseOSTPartType(Evolution.create("EPIC", List.of(9880L, 9880L, 9880L, 9880L, 9880L)))
                //evolution depenses par territoire
                .addEvolutionDepenseTerritoire(Evolution.create("Tiaret", List.of(123L, 2334L, 2323L, 3434L, 3434L)))
                .addEvolutionDepenseTerritoire(Evolution.create("Algiers", List.of(123L, 2334L, 2323L, 3434L, 3434L)))
                .addEvolutionDepenseTerritoire(Evolution.create("Oran", List.of(123L, 3434L, 2323L, 3434L, 3434L)))
                .addEvolutionDepenseTerritoire(Evolution.create("Blida", List.of(664L, 2334L, 2323L, 3434L, 3434L)))
                .addEvolutionDepenseTerritoire(Evolution.create("Djelfa", List.of(664L, 2334L, 2323L, 3434L, 3434L)))
                .addEvolutionDepenseTerritoire(Evolution.create("Batna", List.of(505L, 1293L, 2323L, 666L, 3434L)))
                //source de financement
                .addEvolutionDepenseSourceFinancement(Evolution.create("budget de l'Etat", List.of(343L, 545L, 3432L, 5345L, 5453L)))
                //
                .addProjetEnCour(Projet.builder()
                        .name("etude et realisation de ..")
                        .dateDebut(LocalDate.of(2012,11,3))
                        .dateFin(LocalDate.of(2024,1,1))
                        .couteEstGlobal(134_333L)
                        .tauxAvancement(83d)
                        .respetEcheanciers(false)
                        .AEreevaluationDemandee(323L)
                        .CPAnnee(34334L)
                        .CPAnneePlus1(34333L)
                        .CPAnneePlus2(44333L)
                        .chargesRecurAnnuellMoyennesPrevus(23433L)
                        .build())
                .build();
        ficheProgramme.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of(), Year.of(2025)));

    }
}

package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;

public class FicheProgrammeFrenchTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        FicheProgramme ficheProgramme = FicheProgramme.builder()
                .counter(1)
                .name("Formation professionnelle")
                .gestionnaire("""
                        Directeur de l’Organisation et du Suivi de la Formation
                        Professionnelle."""
                        .replaceAll("\\r?\\n", " "))
                .addInitiativesImportante("""
                        La revalorisation de la ressource humaine à travers le recrutement pour les
                        nouveaux métiers et le perfectionnement des formateurs"""
                        .replaceAll("\\r?\\n", " "))
                .addInitiativesImportante("""
                        La réalisation de nouveaux établissements et la réhabilitation du patrimoine
                        existant"""
                        .replaceAll("\\r?\\n", " "))
                .addInitiativesImportante("""
                        La confection de nouveaux programmes de formation par le biais du réseau
                        d’ingénierie pédagogique"""
                        .replaceAll("\\r?\\n", " "))
                .addJustificationDepensePersonnel("""
                        Les traitements, salaires, indemnités et charges sociales du personnel intervenant dans le cadre du
                        programme « Patrimoine culturel »"""
                        .replaceAll("\\r?\\n", " "))
                .addJustificationDepensePersonnel("""
                        L’incidence financière induite par la révision de la grille indiciaire du personnel relevant du
                        programme « Patrimoine culturel »"""
                        .replaceAll("\\r?\\n", " "))
                .addJustificationDepensePersonnel("""
                        L’avancement et la promotion du personnel, en activité, chargé du programme en question""")
                .addJustificationDepensePersonnel("""
                        L’incidence financière induite par la révision de la grille indiciaire du personnel relevant des
                        établissements sous tutelle du Ministère de la Culture et des Arts (EPA et EPST relevant du
                        programme « Patrimoine culturel »)"""
                        .replaceAll("\\r?\\n", " "))
                .addJustificationDepenseFonctionnementService("""
                        De l'opération d'inventaire au profit des offices nationaux (Atlas Saharien, Ahaggar, Tassili, Touat
                        gourara et Tindouf), suite aux conclusions de la réunion du Gouvernement tenue le 15/02/2023 et en
                        application des instructions de Monsieur le Premier Ministre)"""
                        .replaceAll("\\r?\\n", " "))
                .addJustificationDepenseFonctionnementService("""
                        Des frais liés à l’organisation d’une manifestation culturelle internationale sur la valorisation du
                        patrimoine culturel"""
                        .replaceAll("\\r?\\n", " "))

                //repartition programme titre-centre responsabilite
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofCentraux(List.of(81_000L, 279_311L, 0L, 0L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofOrganismesSousTutelle(List.of(74_841_862L, 5_708_969L, 0L, 9_186_420L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofDeconcentres(List.of(0L, 0L, 10_655_000L, 0L)))
                .addRepartitionProgrammeCentreRespTitre(RepartitionCentreResponsabiliteTitre
                        .ofOrganesTerritoriaux(List.of(0L, 0L, 8_614_000L, 0L)))
                //repartition sous-programme titre
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("Formation professionnelle initiale",
                        List.of(74_046_542L, 5_869_433L, 19_150_000L, 9_176_540L)))
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("Formation continue et à distance",
                        List.of(0L, 8_000L, 0L, 9_880L)))
                .addRepartitionSousProgrammeTitre(RepartitionTitre.create("Ingénierie pédagogique de la formation professionnelle",
                        List.of(876_320L, 110_847L, 119_000L, 0L)))
                //evolution depenses
                .addEvolutionDepenseSousProgramme(Evolution.create("Formation professionnelle initiale",
                        List.of(75_540_668L, 89_044_586L, 108_242_515L, 109_566_169L, 111_779_553L)))
                .addEvolutionDepenseSousProgramme(Evolution.create("Formation continue et à distance",
                        List.of(12_995L, 37_889L, 17_880L, 18_140L, 18_342L)))
                .addEvolutionDepenseSousProgramme(Evolution.create("Ingénierie pédagogique de la formation professionnelle",
                        List.of(874_000L, 930_031L, 1_106_167L, 1_506_892L, 1_529_151L)))
                // evolution postes ouvert mass salariale
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.SERVICES_CENTRAUX,
                                List.of(67L, 71L, 71L), List.of(51_120L, 68_160L, 70_384L)))
                .addEvolutionPostOuvertMassSalarial(PostesOuvertMassSalarial
                        .create(CentreResponsabilite.ORGANISMES_SOUS_TUTELLE,
                                List.of(69_960L, 71_005L, 71_005L),
                                List.of(49_186_110L, 64_356_373L, 73_295_330L)))
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
                .addGPEEnCour(Projet.builder()
                        .name("GPE 1")
                        .dateDebut(LocalDate.of(2016, 1, 23))
                        .dateFin(LocalDate.of(2028, 2, 24))
                        .couteEstGlobal(134_223L)
                        .tauxAvancement(85.6)
                        .respetEcheanciers(true)
                        .AEreevaluationDemandee(345_243L)
                        .CPAnnee(1_433_343L)
                        .CPAnneePlus1(3_343_342L)
                        .CPAnneePlus2(4_434_343L)
                        .chargesRecurAnnuellMoyennesPrevus(2_343_434L)
                        .build())
                .build();
        ficheProgramme.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of(), Year.of(2025)));

    }
}

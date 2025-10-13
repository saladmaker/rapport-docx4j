package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Map;

public class FrenchModel implements DocumentGenerator {
    static String intro = """
            Le secteur de la formation et de l’enseignement professionnels a pour mission de répondre à la fois à la demande sociale en formation exprimée par les jeunes sortants de l’éducation nationale, et aux besoins en qualification professionnelles exprimés par les entreprises et le marché du travail.
            
            Les formations dispensées concernent 23 branches professionnelles et chaque branche est elle-même subdivisée en spécialités (495).
            
            L’organisation et le fonctionnement du secteur repose sur une loi-cadre la loi n°08-07 du 23 février 2008, portant loi d’orientation sur la formation et l’enseignement professionnels.
            """;

    @Override
    public void generate(WordprocessingMLPackage document) {
        byte[] image = null;
        try {
            image = FrenchModel.class.getClassLoader().getResourceAsStream("ORGANIGRAME.jpg").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var au = AuSujet.builder()
                .laMission(LaMission.builder()
                        .intro(intro)
                        .addMission(Mission.builder()
                                .mission("Les finances publiques")
                                .addSubMission("fiscalité")
                                .addSubMission("la douane")
                                .addSubMission("le domaine national et les affaires foncières")
                                .addSubMission("les dépenses publiques, le budget et la comptabilité publique")
                                .build()
                        )
                        .addMission(Mission.create("la monnaie"))
                        .addMission(Mission.create("l’épargne, le crédit et les assurances économiques"))
                        .addMission(Mission.create("les interventions financières de l’Etat"))
                        .addMission(Mission.create("la politique nationale en matière d’endettement extérieur"))
                        .addMission(Mission.create("le contrôle des changes"))
                        .addMission(Mission.create("le contrôle financier relatif aux utilisations des crédits du budget de l’Etat et des" +
                                "ressources du Trésor Public"))
                        .addMission(Mission.create("les relations économiques et financières extérieures"))
                        .build())
                .leMinistere(LeMinistere.builder()
                        .image(image)
                        .build())
                .cartographie(CartographieProgrammesPortefeuille.builder()
                        .addProgrammeStructure(
                                ProgrammeStructure.builder()
                                        .name("Programme 001 - Modernisation de l'administration")
                                        .addServicesCentraux("Secrétariat Général")
                                        .addServicesCentraux("Inspection Générale")
                                        .addServicesDeconcentre("Direction Régionale Alger")
                                        .addServicesDeconcentre("Direction Régionale Oran")
                                        .addOrganismesSousTutelle(
                                                "Agence Nationale du Numérique")
                                        .addOrganismesSousTutelle(
                                                "Institut Supérieur d’Administration Publique")
                                        .addOrganesTerritoriaux("Direction Wilaya Alger")
                                        .addOrganesTerritoriaux("Direction Wilaya Oran")
                                        .build())
                        .addProgrammeStructure(
                                ProgrammeStructure.builder()
                                        .name("Programme 002 - Développement durable")
                                        .addServicesCentraux(
                                                "Direction Générale de l’Environnement")
                                        .addServicesDeconcentre("Direction Régionale Annaba")
                                        .addServicesDeconcentre("Direction Régionale Tlemcen")
                                        .addOrganismesSousTutelle("Office National des Forêts")
                                        .addOrganesTerritoriaux("Conservatoire des Zones Humides")
                                        .build())
                        .build())
                .fichePortefeuille(FichePortefeuille.builder()
                        .addRepartitionProgramme(new RepartitionProgramme("Formation professionnelle", 19_506_191_000L, 20_143_691_000L))
                        .addRepartitionProgramme(new RepartitionProgramme("Enseignement professionnel", 540_000_000L, 622_000_000L))
                        .addRepartitionProgramme(new RepartitionProgramme("Administration générale", 98_536_426_000L, 97_250_926_000L))
                        .addVersionBRepartitionProgramme(
                                new RepartitionProgramme("Formation professionnelle", 109_366_562L, 110_004_062L))
                        .addVersionBRepartitionProgramme(
                                new RepartitionProgramme("Enseignement professionnel", 2_436_520L, 2_518_520L))
                        .addVersionBRepartitionProgramme(
                                new RepartitionProgramme("Administration générale", 6_779_535L, 5_494_035L))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("Formation professionnelle",
                                        List.of(360_311L, 10_655_000L, 89_737_251L, 8_614_000L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("Enseignement professionnel",
                                        List.of(64_000L, 228_000L, 1_856_520L, 288_000L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("Administration générale",
                                        List.of(1_723_123L, 4_863_195L, 138_717L, 54_500L)))

                        .addRepartitionProgrammesTitre(RepartitionTitre.create("Arts et lettres",
                                List.of(11_076_119L, 2_683_352L, 3_217_628L, 2_962_406L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("Patrimoine culturel",
                                List.of(45_734_33L, 427_752L, 2_816_372L, 1_024_000L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("Administration générale",
                                List.of(4_497_238L, 558_043L, 466_000L, 79_326L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofCentraux(List.of(553_000L, 415_934L, 1_178_500L, 0L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofDeconcentres(List.of(3_881_195L, 380_000L, 11_485_000L, 0L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganismesSousTutelle(List.of(76_606_938L, 5_875_098L, 0L, 9_250_452L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganesTerritoriaux(List.of(0L, 0L, 8_956_500L, 0L)))
                        .addEvolutionDepensesProgramme(Evolution.create("Formation professionnelle", List.of(76_427_663L, 90_012_506L, 109_366_562L, 11_1091_201L, 113_327_046L)))
                        .addEvolutionDepensesProgramme(Evolution.create("Enseignement professionnel", List.of(1_545_505L, 2_023_610L, 2_436_520L, 2_460_022L, 2_504_707L)))
                        .addEvolutionDepensesProgramme(Evolution.create("Administration générale", List.of(5_118_443L, 7_674_181L, 6_779_535L, 7_542_716L, 7_154_077L)))
                        .addEvolutionPostesServicesCentraux(List.of(394L,394L,394L, 394L, 394L))
                        .addEvolutionPostesServicesDeconcentres(List.of(2_829L, 2_979L, 2_979L, 2_979L, 2_979L))
                        .addEvolutionPostesOrganismesSousTutelle(List.of(71_626L, 72_716L, 72_716L, 73_526L, 74_036L))
                        .build())
                .build();
        au.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of(), Year.of(2025)));
    }
}

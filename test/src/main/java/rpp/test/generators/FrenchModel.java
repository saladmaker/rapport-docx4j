package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;
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
                .fichePortefeuille(FichePortefeuille.builder()
                        .addRepartitionProgramme(new RepartitionProgramme("Formation professionnelle", 19506191000L, 20143691000L))
                        .addRepartitionProgramme(new RepartitionProgramme("Enseignement professionnel", 540000000L, 622000000L))
                        .addRepartitionProgramme(new RepartitionProgramme("Administration générale", 98536426000L, 97250926000L))
                        .addVersionBRepartitionProgramme(
                                new RepartitionProgramme("Formation professionnelle", 109366562L, 110004062L))
                        .addVersionBRepartitionProgramme(
                                new RepartitionProgramme("Enseignement professionnel", 2436520L, 2518520L))
                        .addVersionBRepartitionProgramme(
                                new RepartitionProgramme("Administration générale", 6779535L, 5494035L))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("Formation professionnelle",
                                        List.of(360311L, 10655000L, 89737251L, 8614000L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("Enseignement professionnel",
                                        List.of(64000L, 228000L, 1856520L, 288000L)))
                        .addRepartitionProgrammeCentreResp(
                                RepartitionCentreResponsabilite.create("Administration générale",
                                        List.of(1723123L, 4863195L, 138717L, 54500L)))

                        .addRepartitionProgrammesTitre(RepartitionTitre.create("Arts et lettres",
                                List.of(11076119L, 2683352L, 3217628L, 2962406L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("Patrimoine culturel",
                                List.of(4573433L, 427752L, 2816372L, 1024000L)))
                        .addRepartitionProgrammesTitre(RepartitionTitre.create("Administration générale",
                                List.of(4497238L, 558043L, 466000L, 79326L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofCentraux(List.of(553000L, 415_934L, 1_178_500L, 0L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofDeconcentres(List.of(3_881_195L, 380_000L, 11_485_000L, 0L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganismesSousTutelle(List.of(76_606_938L, 5_875_098L, 0L, 9_250_452L)))
                        .addRepartitionPortefeuilleCentreResponTitre(RepartitionCentreResponsabiliteTitre.ofOrganesTerritoriaux(List.of(0L, 0L, 8_956_500L, 0L)))
                        .addEvolutionDepensesProgramme(Evolution.create("Formation professionnelle", List.of(76427663L, 90012506L, 109366562L, 111091201L, 113327046L)))
                        .addEvolutionDepensesProgramme(Evolution.create("Enseignement professionnel", List.of(1545505L, 2023610L, 2436520L, 2460022L, 2504707L)))
                        .addEvolutionDepensesProgramme(Evolution.create("Administration générale", List.of(5118443L, 7674181L, 6779535L, 7542716L, 7154077L)))
                        .addEvolutionPostesServicesCentraux(List.of(394L,394L,394L, 394L, 394L))
                        .addEvolutionPostesServicesDeconcentres(List.of(2829L, 2979L, 2979L, 2979L, 2979L))
                        .addEvolutionPostesOrganismesSousTutelle(List.of(71626L, 72716L, 72716L, 73526L, 74036L))
                        .build())
                .build();
        au.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of()));
    }
}

package rpp.test.generators;

import gov.mf.dgb.rpp.model.*;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;
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
                        .addRepartitionProgramme(new RepartitionProgramme("Programme A", 213123L, 322232L))
                        .addRepartitionProgramme(new RepartitionProgramme("Programme B", 234233L, 324234L))
                                .build())
                        .build();

        au.write(document, GenerationContext.of(LanguageDirection.LTR, document, Map.of()));
    }
}

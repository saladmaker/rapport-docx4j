package rpp.test.generators;

import gov.mf.dgb.rpp.model.AuSujet;
import gov.mf.dgb.rpp.model.LaMission;
import gov.mf.dgb.rpp.model.LeMinistere;
import gov.mf.dgb.rpp.model.Mission;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.io.IOException;

public class FrenchModelFullTest implements DocumentGenerator {

    @Override
    public void generate(WordprocessingMLPackage document) {
        byte[] image = null;
        try {
            image = FrenchModel.class.getClassLoader().getResourceAsStream("ORGANIGRAME.jpg").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final String missionIntro = """
                Le décret exécutif N°95-54 du 15 février 1995 fixant attributions du Ministre des Finances,
                stipule que : « Le ministre des Finances exerce ses attributions dans les domaines ci-après :"""
                .replaceAll("\\r?\\n", " ");
        AuSujet auSujet = AuSujet.builder()
                .laMission(LaMission.builder()
                        .intro(missionIntro)
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

                .build();
    }
}

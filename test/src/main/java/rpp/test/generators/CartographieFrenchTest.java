package rpp.test.generators;

import gov.mf.dgb.rpp.model.CartographieProgrammesPortefeuille;
import gov.mf.dgb.rpp.model.GenerationContext;
import gov.mf.dgb.rpp.model.LanguageDirection;
import gov.mf.dgb.rpp.model.ProgrammeStructure;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import rpp.test.DocumentGenerator;

import java.time.Year;
import java.util.Map;

public class CartographieFrenchTest implements DocumentGenerator {
    @Override
    public void generate(WordprocessingMLPackage document) {
        CartographieProgrammesPortefeuille carto = CartographieProgrammesPortefeuille.builder()
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
                .build();
        carto.write(document, GenerationContext.of(LanguageDirection.LTR,document, Map.of(), Year.of(2025)));
    }
}

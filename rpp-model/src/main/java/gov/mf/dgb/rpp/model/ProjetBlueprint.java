package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Prototype;

import java.time.LocalDate;

@Prototype.Blueprint
interface ProjetBlueprint {
    String name();
    LocalDate dateDebut();

    LocalDate dateFin();

    Long couteEstGlobal();

    Double tauxAvancement();

    Boolean respetEcheanciers();

    Long AEreevaluationDemandee();

    Long CPAnnee();

    Long CPAnneePlus1();

    Long CPAnneePlus2();

    Long chargesRecurAnnuellMoyennesPrevus();

}

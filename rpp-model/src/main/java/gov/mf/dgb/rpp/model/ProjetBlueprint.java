package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Prototype;

import java.time.LocalDate;

@Prototype.Blueprint
interface ProjetBlueprint {

    LocalDate dateDebut();

    LocalDate dateFin();

    Long CouteEstGlobal();

    Double tauxAvancement();

    Boolean respetÉchéanciers();

    Long AEreevaluationDemandée();

    Long CPAnnee();

    Long CPAnneePlus1();

    Long CPAnee();

    Long chargesRecurAnnuellMoyennesPrevus();

}

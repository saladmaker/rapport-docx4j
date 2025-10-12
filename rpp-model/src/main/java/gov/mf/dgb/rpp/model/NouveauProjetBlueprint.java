package gov.mf.dgb.rpp.model;

import io.helidon.builder.api.Prototype;

import java.time.LocalDate;

@Prototype.Blueprint(createEmptyPublic = false)
interface NouveauProjetBlueprint {
    String name();
    LocalDate dateDebut();

    LocalDate dateFin();

    Long couteEstGlobal();

    Long AEDemande();

    Long CPAnnee();

    Long CPAnneePlus1();

    Long CPAnneePlus2Suivante();

    Long chargesRecurAnnuellMoyennesPrevus();
}

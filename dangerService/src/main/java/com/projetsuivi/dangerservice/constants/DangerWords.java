package com.projetsuivi.dangerservice.constants;

public enum DangerWords {

    HEMOGLOBINE_A1C("Hémoglobine A1C"),
    MICROALBUMINE("Microalbumine"),
    TAILLE ("Taille"),
    POIDS ("Poids"),
    FUMEUR ("Fumeur"),
    FUMEUSE ("Fumeuse"),
    ANORMAL ("Anormal"),
    CHOLESTEROL ("Cholestérol"),
    VERTIGES ("Vertiges"),
    RECHUTE ("Rechute"),
    REACTION ("Réaction"),
    ANTICORPS ("Anticorps");


    private String value;
    private DangerWords(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

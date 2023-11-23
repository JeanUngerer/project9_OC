package com.projetsuivi.dangerservice.constants;

public enum DangerStatus {

    NONE ("None"),
    BORDERLINE ("Borderline"),
    INDANGER ("InDanger"),
    EARLYONSET ("EarlyOnset");


    private String value;
    private DangerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

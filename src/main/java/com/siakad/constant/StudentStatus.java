package com.siakad.constant;

public enum StudentStatus {

    ACTIVE("Aktif"),
    GRADUATED("Lulus"),
    DROPPED("Keluar");

    public final String label;

    StudentStatus(String label) {
        this.label = label;
    }
}

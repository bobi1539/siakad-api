package com.siakad.constant;

public enum TeacherStatus {

    ACTIVE("Aktif"),
    INACTIVE("Tidak Aktif");

    public final String label;

    TeacherStatus(String label) {
        this.label = label;
    }
}

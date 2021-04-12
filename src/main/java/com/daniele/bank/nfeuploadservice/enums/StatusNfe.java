package com.daniele.bank.nfeuploadservice.enums;

public enum StatusNfe {
    AGUARDANDO_PROCESSAMENTO(1),
    EM_PROCESSAMENTO(2),
    PROCESSADA(3),
    PROCESSADA_COM_ERRO(4);

    private final int statusId;

    StatusNfe(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}

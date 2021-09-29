package com.test.concord.dto;

public class ResponseUserDto {
    private String fio;

    public ResponseUserDto(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}

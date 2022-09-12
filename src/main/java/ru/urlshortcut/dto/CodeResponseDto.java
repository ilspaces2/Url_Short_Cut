package ru.urlshortcut.dto;

public class CodeResponseDto {

    private String code;

    public CodeResponseDto() {
    }

    public CodeResponseDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

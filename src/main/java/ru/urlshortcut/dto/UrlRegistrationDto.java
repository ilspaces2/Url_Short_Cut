package ru.urlshortcut.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

public class UrlRegistrationDto {

    @URL(message = "Invalid url format (for example http://www.ex.ex or https://ex.ex).")
    @NotBlank(message = "Url is blank.")
    private String url;

    public UrlRegistrationDto() {
    }

    public UrlRegistrationDto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

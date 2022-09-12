package ru.urlshortcut.dto;

import javax.validation.constraints.NotBlank;

public class SiteRegistrationDto {

    @NotBlank(message = "Site name is blank")
    private String site;

    public SiteRegistrationDto() {
    }

    public SiteRegistrationDto(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}

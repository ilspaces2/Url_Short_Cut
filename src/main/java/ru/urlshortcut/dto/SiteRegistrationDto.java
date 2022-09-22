package ru.urlshortcut.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SiteRegistrationDto {

    @NotBlank(message = "Site name is blank")
    @Pattern(regexp = "^(?!https://|http://).+",
            message = "Site must be without a protocol. For example: site.com.")
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

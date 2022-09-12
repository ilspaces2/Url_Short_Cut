package ru.urlshortcut.controller;

import org.springframework.web.bind.annotation.*;
import ru.urlshortcut.dto.SiteRegistrationDto;
import ru.urlshortcut.dto.SiteResponseDto;
import ru.urlshortcut.service.SiteService;

import javax.validation.Valid;

@RestController
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/registration")
    public SiteResponseDto siteRegistration(@RequestBody @Valid SiteRegistrationDto site) {
        return siteService.siteRegistration(site);
    }
}

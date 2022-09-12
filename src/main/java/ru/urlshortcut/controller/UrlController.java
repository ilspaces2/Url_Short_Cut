package ru.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.urlshortcut.dto.CodeResponseDto;
import ru.urlshortcut.dto.UrlRegistrationDto;
import ru.urlshortcut.dto.UrlStatisticResponseDto;
import ru.urlshortcut.service.SiteService;
import ru.urlshortcut.service.UrlService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UrlController {

    private final SiteService siteService;

    private final UrlService urlService;

    public UrlController(SiteService siteService, UrlService urlService) {
        this.siteService = siteService;
        this.urlService = urlService;
    }

    @PostMapping("/convert")
    public CodeResponseDto urlRegistration(Authentication auth, @RequestBody @Valid UrlRegistrationDto urlRegistrationDto) {
        return urlService.urlRegistration(
                urlRegistrationDto,
                siteService.findSiteByLogin(auth.getName()));
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirectToUrl(@PathVariable String code) {
        return new ResponseEntity<>(urlService.redirectToUrl(code), HttpStatus.FOUND);
    }

    @GetMapping("/statistic")
    public List<UrlStatisticResponseDto> urlStatistic(Authentication auth) {
        return urlService.urlStatistic(auth.getName());
    }
}

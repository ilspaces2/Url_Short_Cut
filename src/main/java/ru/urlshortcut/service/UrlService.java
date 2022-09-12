package ru.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.urlshortcut.dto.CodeResponseDto;
import ru.urlshortcut.dto.UrlRegistrationDto;
import ru.urlshortcut.dto.UrlStatisticResponseDto;
import ru.urlshortcut.model.Site;
import ru.urlshortcut.model.Url;
import ru.urlshortcut.repository.UrlRepository;
import ru.urlshortcut.util.WordGeneratorByAsciiTable;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UrlService {

    private final int wordLength = 8;

    private final UrlRepository urlRepository;

    private final WordGeneratorByAsciiTable wordGenerator;

    public UrlService(UrlRepository urlRepository, WordGeneratorByAsciiTable wordGenerator) {
        this.urlRepository = urlRepository;
        this.wordGenerator = wordGenerator;
    }

    public CodeResponseDto urlRegistration(UrlRegistrationDto urlRegistration, Site site) {
        if (urlRepository.findUrlByUrl(urlRegistration.getUrl()).isPresent()) {
            throw new IllegalArgumentException("Url already registered.");
        }
        if (!urlRegistration.getUrl().startsWith("https://" + site.getSite())
                && !urlRegistration.getUrl().startsWith("http://" + site.getSite())) {
            throw new IllegalArgumentException("Url does not match with site.");
        }
        var url = new Url();
        url.setUrl(urlRegistration.getUrl());
        url.setCode(wordGenerator.generateWord(wordLength));
        url.setSite(site);
        urlRepository.save(url);
        return new CodeResponseDto(url.getCode());
    }

    public String redirectToUrl(String code) {
        var url = urlRepository.findUrlByCode(code);
        if (url.isEmpty()) {
            throw new NoSuchElementException("Url not found.");
        }
        urlRepository.incrementTotal(url.get().getId());
        return url.get().getUrl();
    }

    public List<UrlStatisticResponseDto> urlStatistic(String login) {
        return urlRepository.findUrlBySiteLogin(login).stream()
                .map(url ->
                        new UrlStatisticResponseDto(
                                url.getUrl(),
                                url.getTotal()))
                .toList();
    }
}

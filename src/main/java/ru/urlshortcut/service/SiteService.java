package ru.urlshortcut.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.urlshortcut.dto.SiteRegistrationDto;
import ru.urlshortcut.dto.SiteResponseDto;
import ru.urlshortcut.model.Site;
import ru.urlshortcut.repository.SiteRepository;
import ru.urlshortcut.util.WordGeneratorByAsciiTable;

import java.util.NoSuchElementException;

@Service
public class SiteService {

    private final int wordLength = 8;

    private final WordGeneratorByAsciiTable wordGenerator;

    private final SiteRepository siteRepository;

    private final PasswordEncoder passwordEncoder;

    public SiteService(WordGeneratorByAsciiTable wordGenerator, SiteRepository siteRepository, PasswordEncoder passwordEncoder) {
        this.wordGenerator = wordGenerator;
        this.siteRepository = siteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SiteResponseDto siteRegistration(SiteRegistrationDto siteRegistrationDto) {
        if (siteRepository.findSiteBySite(siteRegistrationDto.getSite()).isPresent()) {
            return new SiteResponseDto(false, "", "");
        }
        String login = wordGenerator.generateWord(wordLength);
        String password = wordGenerator.generateWord(wordLength);
        siteRepository.save(
                new Site(
                        siteRegistrationDto.getSite(),
                        login,
                        passwordEncoder.encode(password))
        );
        return new SiteResponseDto(true, login, password);
    }

    public Site findSiteByLogin(String login) {
        var site = siteRepository.findSiteByLogin(login);
        if (site.isEmpty()) {
            throw new NoSuchElementException("Login not found");
        }
        return site.get();
    }
}

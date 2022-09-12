package ru.urlshortcut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.urlshortcut.model.Site;
import ru.urlshortcut.repository.SiteRepository;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SiteRepository siteRepository;

    public UserDetailsServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Site site = siteRepository.findSiteByLogin(login).orElse(null);
        if (site == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(
                site.getLogin(),
                site.getPassword(),
                Collections.emptyList());
    }
}

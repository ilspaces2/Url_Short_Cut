package ru.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.urlshortcut.model.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {

    Optional<Site> findSiteBySite(String site);

    Optional<Site> findSiteByLogin(String login);
}

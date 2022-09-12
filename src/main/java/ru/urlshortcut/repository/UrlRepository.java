package ru.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.urlshortcut.model.Url;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    Optional<Url> findUrlByCode(String code);

    Optional<Url> findUrlByUrl(String url);

    List<Url> findUrlBySiteLogin(String login);

    @Modifying
    @Transactional()
    @Query("update Url set total=total+1 where id=?1")
    void incrementTotal(int id);
}

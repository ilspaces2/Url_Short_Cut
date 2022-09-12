[![Java CI with Maven](https://github.com/ilspaces2/job4j_chat/actions/workflows/maven.yml/badge.svg)](https://github.com/ilspaces2/job4j_url_shortcut/actions/workflows/maven.yml)

# Сервис - UrlShortCut

Сервис преобразует все ссылки сайта в кодовые слова.
Сервис работает через REST API.

#### Используемые технологии

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Liquibase

#### Запуск приложения

- Установить на пк : Java 17, PostgreSQL 14, Maven 3.8.6.

- Через терминал создать базу данных в postgresql c названием url_shortcut при помощи команд:
    - psql -U yourLogin
    - enter yourPassword
    - create database url_shortcut;

- Через терминал в папке проекта выполнить:
    - mvn clean install
    - cd target
    - java -jar url_shortcut-1.0.jar

#### Как пользоваться

- Регистрация сайта
    - POST /registration c телом запроса {site : "name.ru"}.
    - В ответ получаем {registration : true/false, login: УНИКАЛЬНЫЙ_КОД, password : УНИКАЛЬНЫЙ_КОД}.
    - Флаг registration указывает, что регистрация выполнена или нет, то есть сайт уже есть в системе.

- Аутентификация пользователя
    - POST /login c телом запроса {login: УНИКАЛЬНЫЙ_КОД, password : УНИКАЛЬНЫЙ_КОД}.
    - В ответ в заголовке получим Authorization: Bearer ТОКЕН_АВТОРИЗАЦИИ.

- Авторизация пользователя
    - Для авторизации необходимо предоставлять токен в заголовке запроса.
      Authorization: Bearer ТОКЕН_АВТОРИЗАЦИИ.

- Регистрация URL
    - Поле того, как пользователь зарегистрировал имя сайта, он может отправлять ссылки
      со своего сайта и получать в ответ УНИКАЛЬНЫЙ_КОД который
      будет ассоциироваться с отправленной ссылкой.
    - Например был зарегистрирован сайт "name.ru".
      Отправляем ссылку - POST /convert c телом запроса {url: "https://name.ru/profile/1"}.
    - В ответ получаем {code: УНИКАЛЬНЫЙ_КОД}.

- Переадресация
    - Выполняется без авторизации. GET /redirect/УНИКАЛЬНЫЙ_КОД
    - В ответ получаем ссылку и статус 302.

- Статистика
    - У каждого пользователя есть статистика вызовов его ссылок.
    - GET /statistic. В ответ получим писок
      {
      {url : "https://name.ru/profile/1", total : 10},
      {url : "https://name.ru/menu", total : 32}
      }.
  
package ru.urlshortcut.dto;

public class SiteResponseDto {
    private boolean registration;

    private String login;

    private String password;

    public SiteResponseDto() {
    }

    public SiteResponseDto(boolean registration, String login, String password) {
        this.registration = registration;
        this.login = login;
        this.password = password;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

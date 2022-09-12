package ru.urlshortcut.jwtfilter;

public final class JWTConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 86_400_000; /* 1 day, ms */
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String REGISTRATION_SITE = "/registration";

    public static final String REDIRECT_TO_URL = "/redirect/*";

    private JWTConstants() {
    }
}

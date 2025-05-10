package ukim.finki.mk.lab1.security;

public class JWTConstants {
    public static final String SECRET_KEY = "bd37e60e92bf08819806785eafe3c0e3b20d0b17d500734d7a815bfaf6abf905";
    public static final Long EXPIRATION_TIME = 864000000L;
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}

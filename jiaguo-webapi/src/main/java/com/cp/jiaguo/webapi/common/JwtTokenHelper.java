package com.cp.jiaguo.webapi.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtTokenHelper {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenHelper.class);
    private static final String SECRET = "This is my secret key key key!!!";

    public static String create(String username, String[] roles) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withIssuer("ben")
                    .withClaim("user", username)
                    .withArrayClaim("roles", roles)
                    .withExpiresAt(Date.from(LocalDate.now().plus(1, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                    .sign(algorithm);
        } catch (Exception ex) {
            log.error("Error when create JwtToken.", ex);
        }
        return token;
    }

    public static String validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("ben")
                    .acceptExpiresAt(60 * 60 * 24 * 7)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Claim claim = jwt.getClaim("user");
            return claim.asString();
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
        }
        return null;
    }
}

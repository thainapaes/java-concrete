package service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long expiration = 1800000;

    public String createJWT(String id) {
       long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder().setIssuedAt(now)
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SECRET_KEY);

        return builder.compact();
    }


    public Claims decodeJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}

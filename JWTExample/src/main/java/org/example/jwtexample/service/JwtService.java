package org.example.jwtexample.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "34030067976dd4db510794c83862962f0d246ac208e7e30e74e2b099d5f73dab191c3cec42adc84ded3b23862fc41b863e9d4d8a0f482e069377acf70f10433c5585a23410bce40b7046ba529ef9510f9032c01fce52fce9e653a4179aed59412b0bc30dc38ca37dfb6042673ea8e7b92ac6e618ae95981f75af3e2a4eb2a8bcac299a370d91b191a7e65c67cfd9f04b3106e5a5a2528a4220a2144c9338721e52828108a02099b45a37dd7d6cc580d1d65f9ffa7162f4601c566f4e1dc31025d33c15040542647eb5b1f61114af378d28fe368d9bd652a65b17c738a1e9cfede946a566e5d5d845dcebb2bc00e5edec3d65f6ab8580c0a0424c40d50398ab024b17e0b6e7de679ba57624b7d9584efb4e7b20b92ec2ac414d53b0a6ab45a980d8d086040ce8323c85011c01b7c007688d9edd2faa0cf5de4def95c51fe91bf6f58c4aed0cf945a67279dfdcbe254609860022c8f821f7c551a31cc7429d7eae"; // 32+ znaki!

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 godziny
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}


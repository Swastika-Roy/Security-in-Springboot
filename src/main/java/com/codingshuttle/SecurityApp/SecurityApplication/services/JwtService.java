package com.codingshuttle.SecurityApp.SecurityApplication.services;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import com.codingshuttle.SecurityApp.SecurityApplication.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Set;

// Optional if there's only one constructor

@Service
@RequiredArgsConstructor
public class JwtService {

//    @Value("${jwt.secretKey}")
//    private String jwtSecretKey;

//    public JwtService(@Value("${jwt.secretKey}") String jwtSecretKey) {
//        this.jwtSecretKey = jwtSecretKey;
//    }

//    private SecretKey getSecretKey(){
//        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
//    }

private String secretKey="ads9f6askj3h4k1hf86asdfiahkjh34a789s6df89ayshkjh3jkh786adsf78ay";

//@Value("${jwt.secretKey:defaultFallbackKey}")
//private String jwtSecretKey;
public SecretKey getSecretKey(){
    return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
}

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", user.getRoles()) // Use actual user roles
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSecretKey())
                .compact();
    }








//    public Long getUserIdFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .verifyWith(getSecretKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//        return Long.valueOf(claims.getSubject());
//    }



//    public String generateToken(User user){
//       return Jwts.builder()
//                .subject(user.getId().toString())
//                .claim("email",user.getEmail())
//                .claim("roles", Set.of("ADMIN","USER"))
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 1000*60))
//                .signWith(getSecretKey())
//                .compact();
//    }



//    public Long getUserIdFromFromToken(String token){
//        Claims claims = Jwts.parser()
//                .setSigningKey(getSecretKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return Long.valueOf(claims.getSubject());
//    }





}

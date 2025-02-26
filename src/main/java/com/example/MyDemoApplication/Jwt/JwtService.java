package com.example.MyDemoApplication.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRETKEY = "4f3a1f9b8c5e1a2c4d1f8e5b3c9a7e6f3b6a2d4f1e8c9b5a2d3e7f1b8c6e5a4";

    public JwtService(){

    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    generate token
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(getKey())
                .compact();
    }

//    get username
    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

//    validate token
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
             return true;
        }catch(Exception e){
            return false;
        }
    }
}

package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.Constants;
import com.luucasor.goldenraspberryawards.domain.User;
import com.luucasor.goldenraspberryawards.dtos.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenService {

    public TokenDTO generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secret = Base64.getEncoder().encode(Constants.API_SECRET_KEY.getBytes());
        Key signingKey = new SecretKeySpec(secret, signatureAlgorithm.getJcaName());

        String token = Jwts.builder()
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .signWith(signingKey, signatureAlgorithm)
                .compact();

        return new TokenDTO(token);
    }
}

package ru.oksei.talisman.simpleMoney.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "280e0d59803b768cd08f8686d2b17f0ac4867e5c02e8d5b576909230a1ed04e892f986ec2a4f653d" +
            "9c5ec5a903dab16d8016a33df244678945d7b8a2f9dd53d3606768baf0670522e8fa9ac04cde62e0c73327daf2a7fcbb4afb9942a77" +
            "b87c6f39a70d084df03c38d59b078108162ee82131c7a240e559015960f827e2b9729d12ea1430e7b1f9f1b545b50de2585966cb1b68" +
            "5016d6ff8d90bb22c2b6bbdfc8d24ece8e715528d30807d77d47e5b5b0c1b07ef661c1d88a81cf070ca8e691aa9680e7e3222413d708" +
            "1a81c7f90e3851ce635e8b781251d89a65cf8148433587b224456a4b14198b4f40355d6ba7219c4614d5de7c46a70d0563520d74dee19";

    public String generateToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 день
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

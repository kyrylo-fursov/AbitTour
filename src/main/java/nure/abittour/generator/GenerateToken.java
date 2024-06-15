package nure.abittour.generator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

import java.security.Key;
import java.util.Date;

public class GenerateToken {

    private static final String SECRET = "9949e7ba6b26e4f3f137e741efa09be24f4cbbfbb26922cb329881f32ec269a0"; // Use the same secret as in your JwtService

    public static void main(String[] args) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));

        String jwt = Jwts.builder()
                .setSubject("testuser@gmail.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (100L * 365 * 24 * 60 * 60 * 1000))) // 100 years
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("Generated JWT: " + jwt);
    }
}

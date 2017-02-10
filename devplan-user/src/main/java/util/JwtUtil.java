package util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import model.User;
import model.UserDto;

/**
 * Created by meruzhan.gasparyan on 14-Dec-16.
 */


public class JwtUtil {

    public static String generateToken(User user, String signingKey) {
        if (user == null) {
            return null;
        }
        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());
        claims.put("date", System.currentTimeMillis());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();

    }

    public static String parsToken(String token, String signingKey) {
        if (token == null || signingKey == null) {
            return null;
        }
        Claims body = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();

        return body.getSubject();
    }
}

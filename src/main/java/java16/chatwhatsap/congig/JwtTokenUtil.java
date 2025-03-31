package java16.chatwhatsap.congig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java16.chatwhatsap.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
@Component
public class JwtTokenUtil {

    private String secretKey = "yourSecretKey";  // Сиздин токендин коддоо ачкычыңыз

    // Токенди түзүү
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).signWith(SignatureAlgorithm.HS512, secretKey)  // HS512 алгоритми менен кол коюу
                .compact();  // Токенди курап чыгуу
    }

    // Токенден колдонуучунун атын алуу
    public String getUsernameFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        Jws<Claims> claimsJws = Jwts.parseBulder
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);  // ✅ Жаңы версия үчүн туура

        return claimsJws.getBody().getSubject();
    }

    // Токендин тууралыгын текшерүү
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  // Токендин тууралыгынан тышкары, анын мөөнөтү өткөн эмес экенин текшерүү
    }

    // Токендин мөөнөтү өткөнүн текшерүү
    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());  // Токендин мөөнөтү өткөнбү текшерүү
    }

    // Токендин мөөнөтүн алуу
    public Date getExpirationDateFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();  // Токендин мөөнөтүн кайтаруу
    }
}

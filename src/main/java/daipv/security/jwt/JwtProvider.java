package daipv.security.jwt;

import io.jsonwebtoken.*;
import daipv.security.userprincipal.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    @Value("${daipv.jwt.secret}")
    private String JWT_SECRET;

    @Value("${daipv.jwt.expiration}")
    private long JWT_EXPIRATION;

    /** tạo token từ username, secret, expiration */
    public String createToken(CustomUserDetails customUserDetails){
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder().setSubject(customUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUsernameFromJWT(String token){
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
//        catch (MalformedJwtException ex){
//            log.error("Invalid JWT Token");
//        }catch (ExpiredJwtException ex){
//            log.error("Expired JWT Token");
//        }catch (UnsupportedJwtException ex){
//            log.error("Unsupported JWT Token");
//        }catch (IllegalArgumentException ex){
//            log.error("JWT claims String is empty");
//        }
    }
}

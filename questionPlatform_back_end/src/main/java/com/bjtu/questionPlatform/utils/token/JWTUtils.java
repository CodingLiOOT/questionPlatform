package com.bjtu.questionPlatform.utils.token;

import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: framework
 * @description: token utils
 * @author: CodingLiOOT
 * @create: 2021-03-18 16:53
 * @version: 1.0
 **/
@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JWTUtils implements Serializable {
    private String secret;
    private Long expiration;
    private String header;

    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String generateToken(UserDetails user){
        Map<String,Object> claims = new HashMap<>(2);
        claims.put("sub",user.getUsername());
        claims.put("created",new Date());
        return generateToken(claims);
    }

    public String getUsernameFromToken(String token){
        String username;
        try{
            Claims claims=getClaimsFromToken(token);
            username=claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    public Boolean isTokenExpired(String token){
        try{
            Claims claims=getClaimsFromToken(token);
            Date expiration=claims.getExpiration();
            return expiration.before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    public String refreshToken(String token){
        String refreshedToken;
        try{
            Claims claims =getClaimsFromToken(token);
            claims.put("created",new Date());
            refreshedToken=generateToken(claims);
        }catch (Exception e){
            refreshedToken=null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token,UserDetails userDetails){
        JwtUser jwtUser =(JwtUser) userDetails;
        String username=getUsernameFromToken(token);
        Boolean is=isTokenExpired(token);
        if (!(username.equals(jwtUser.getUsername()))||isTokenExpired(token)){
            throw new DefinitionException(ErrorEnum.SIGNATURE_NOT_MATCH);
        }
        return true;
    }
}
package com.haijun.util;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT：用户登录认证
 * @author CW5320
 *
 */
public class JwtHelper {
	public static Claims parseJWT(String jsonWebToken, String base64Security){  
        try {  
            Claims claims = Jwts.parser()  
                       .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))  
                       .parseClaimsJws(jsonWebToken).getBody();  
            return claims;  
        } catch(Exception ex) {  
            return null;  
        }  
    }  
      
	/**
	 * user为用户信息，含有用户名，用户id
	 * @param user
	 * @param base64Security
	 * @return
	 */
    public static String createJWT(Object user, String base64Security) {  
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;  
        String timestamp = System.currentTimeMillis()+"";
        //生成签名密钥  
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);  
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());  
        
          //添加构成JWT的参数  
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")  
        								.claim("user", user)
        								.setSubject(timestamp)
                                        .signWith(signatureAlgorithm, signingKey);  
         //生成JWT  
        return builder.compact();  
    } 
}

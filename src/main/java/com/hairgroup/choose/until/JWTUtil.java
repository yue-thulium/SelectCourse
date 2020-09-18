package com.hairgroup.choose.until;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Created on 2020/9/17
 *
 * @author Yue Wu
 */
public class JWTUtil {
    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * 生成 token, 24h后过期
     *
     * @param username 用户名
     * @param id 用户id
     * @return 加密的token
     */
    public static String createToken(String username,String id) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username&id信息
        return JWT.create()
                .withClaim("username", username)
                .withClaim("id", id)
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 生成 token, 24h后过期
     *
     * @param u_id 用户id
     * @param role 用户身份
     * @param identity_id 用户身份对应权限
     * @return 加密的token
     */
    public static String createToken(Integer u_id,Integer role,Integer identity_id) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username&id信息
        return JWT.create()
                .withClaim("u_id", u_id)
                .withClaim("role",role)
                .withClaim("identity_id",identity_id)
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户ID
     */
    public static Integer getUserID(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("u_id").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户role
     */
    public static Integer getUserRole(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("role").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户permission
     */
    public static Integer getUserIdentity(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("identity_id").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}

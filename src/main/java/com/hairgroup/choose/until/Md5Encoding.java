package com.hairgroup.choose.until;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created on 2020/9/18
 *
 * @author Yue Wu
 */
public class Md5Encoding {
    private final static String salt="1x2j3x4y5w6t";

    private Md5Encoding() {}

    /**
     * md5加盐加密算法
     * 固定盐值
     * @param password
     * @return
     */
    public static String md5SaltEncode(String password){
        String str = "" + salt.charAt(3) + salt + salt.charAt(7) + password + salt.charAt(9);
        return DigestUtils.md5Hex(str.getBytes());
    }
}

package com.ldasset.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.security.MessageDigest;

public class SecurityHelper {

    public static final String PAD_STR = "9";
    private MessageDigest messageDigest = DigestUtils.getSha256Digest();


    public static String encodeKeyWordCode(Long id) {
        if(id==null){
            throw new RuntimeException("id is null");
        }

        return  StringUtils.rightPad(Base64.encodeBase64URLSafeString(id.toString().getBytes()), 6, '=');
    }

    public static boolean checkKeyWordCode(String id) {
        if(StringUtils.isBlank(id)){
            return false;
        }

        if (!Base64.isBase64(id)) {
            return false;
        }

        return  true;
    }

    /**
     * 保护id
     */
    public static String encodeId(Long id) {
        if(id==null){
            throw new RuntimeException("id is null");
        }

        return  StringUtils.center(numToLetter(id.toString()), 5, PAD_STR);
    }

    /**
     * 解密id
     */
    public static String decodeId(String id) {
        if(StringUtils.isBlank(id)){
            throw new RuntimeException("id is null");
        }

        String letters=StringUtils.remove(id,"9").toLowerCase();


        return  letterToNum(letters);
    }

    // 将字母转换成数字
    public static String letterToNum(String input) {
        StringBuffer sb = new StringBuffer();
        for (byte b : input.getBytes()) {
            sb.append(b - 97);
        }
        return sb.toString();
    }

    // 将数字转换成字母
    public static String numToLetter(String input) {
        StringBuffer sb = new StringBuffer();
        byte[] bytes= input.getBytes();
        for (int i = 0; i <bytes.length; i++) {
            if(i%2==0){
                sb.append((char) (bytes[i] + 49));
            }else{
                sb.append((char) (bytes[i] + 17));
            }

        }

        return sb.toString();
    }

    /**
     * 保护密码
     */
    public static String digestPassword(String password) {
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("password is null");
        }

        return new Sha256Hash(password).toHex();
    }
}

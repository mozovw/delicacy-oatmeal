package com.delicacy.oatmeal.common.util.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class PwdUtil {

    static private final String SECURE_KEY ="@!@#$%^123456@~!";

    static private final String SECURE_IV = "~$#^@@!@#$%^&@@#";


    public static String encryptPassword(String password) throws Exception {
        Key keySpec = new SecretKeySpec(SECURE_KEY.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(SECURE_IV.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] b = cipher.doFinal(password.getBytes());
        String ret = Base64.getEncoder().encodeToString(b);
        return ret;
    }

    public static String decryptPassword(String password) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(password);
        IvParameterSpec ivSpec = new IvParameterSpec(SECURE_IV.getBytes());
        Key key = new SecretKeySpec(SECURE_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] ret = cipher.doFinal(bytes);
        return new String(ret, "utf-8");
    }

    public static void main(String[] args) {
        try {
            System.out.println(encryptPassword("123456"));
            System.out.println(decryptPassword("pJRY3Wnrhr5ZDWRxFR6z5w=="));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

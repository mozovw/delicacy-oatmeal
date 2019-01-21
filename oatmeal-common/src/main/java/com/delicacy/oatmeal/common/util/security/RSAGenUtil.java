package com.delicacy.oatmeal.common.util.security;

/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author luoxiaohui
 * @date 2016年8月15日 下午3:48:03
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RSAGenUtil {
    /**
     * 指定加密算法为DESede
     */
    private static String ALGORITHM = "RSA";
    /**
     * 指定key的大小
     */
    private static int KEYSIZE = 1024;
    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "public.keystore";
    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "private.keystore";

    /**
     * 生成密钥对
     */
    private static void generateKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        /** 用对象流将生成的密钥写入文件 */
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
        oos1.writeObject(publicKey);
        oos2.writeObject(privateKey);
        /** 清空缓存，关闭文件输出流 */
        oos1.close();
        oos2.close();
    }

    /**
     * 生成密钥对字符串
     */
    private static void generateKeyPairString() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom(new SimpleDateFormat("yyyyMMdd").format(new Date()).getBytes());
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        /** 用字符串将生成的密钥写入文件 */

        String algorithm = publicKey.getAlgorithm(); // 获取算法
        KeyFactory keyFact = KeyFactory.getInstance(algorithm);

        RSAPublicKeySpec keySpec = (RSAPublicKeySpec) keyFact.getKeySpec(publicKey, RSAPublicKeySpec.class);
        BigInteger prime = keySpec.getModulus();
        BigInteger exponent = keySpec.getPublicExponent();
        System.out.println("公钥模量：" + HexUtil.bytes2Hex(prime.toByteArray()));
        System.out.println("公钥指数:" + HexUtil.bytes2Hex(exponent.toByteArray()));


        System.out.println(privateKey.getAlgorithm());
        RSAPrivateCrtKeySpec privateKeySpec = (RSAPrivateCrtKeySpec) keyFact.getKeySpec(privateKey, RSAPrivateCrtKeySpec.class);
        BigInteger privateModulus = privateKeySpec.getModulus();
        BigInteger privateExponent = privateKeySpec.getPrivateExponent();
        System.out.println("私钥模量：" + HexUtil.bytes2Hex(privateModulus.toByteArray()));
        System.out.println("私钥指数:" + HexUtil.bytes2Hex(privateExponent.toByteArray()));

    }

    /**
     * 加密方法
     * source： 源数据
     */
    public static String encrypt(String source) throws Exception {
        generateKeyPair();
        /** 将文件中的公钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        Key key = (Key) ois.readObject();
        ois.close();

        String algorithm = key.getAlgorithm(); // 获取算法
        KeyFactory keyFact = KeyFactory.getInstance(algorithm);
        BigInteger prime = null;
        BigInteger exponent = null;
        if ("RSA".equals(algorithm)) { // 如果是RSA加密
            RSAPublicKeySpec keySpec = (RSAPublicKeySpec) keyFact.getKeySpec(key, RSAPublicKeySpec.class);
            prime = keySpec.getModulus();
            exponent = keySpec.getPublicExponent();

            //System.out.println("公钥模量：" + HexUtil.bytes2Hex(prime.toByteArray()));
            //System.out.println("公钥指数:" + HexUtil.bytes2Hex(exponent.toByteArray()));

        }


        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);

        return Base64.encodeBase64String(b1);
    }

    /**
     * 解密算法
     * cryptograph:密文
     */
    public static String decrypt(String cryptograph) throws Exception {
        /** 将文件中的私钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        Key key = (Key) ois.readObject();

        String algorithm = key.getAlgorithm(); // 获取算法
        KeyFactory keyFact = KeyFactory.getInstance(algorithm);
        RSAPrivateCrtKeySpec privateKeySpec = (RSAPrivateCrtKeySpec) keyFact.getKeySpec(key, RSAPrivateCrtKeySpec.class);
        BigInteger privateModulus = privateKeySpec.getModulus();
        BigInteger privateExponent = privateKeySpec.getPrivateExponent();

        //System.out.println("私钥模量：" + HexUtil.bytes2Hex(privateModulus.toByteArray()));
        //System.out.println("私钥指数:" + HexUtil.bytes2Hex(privateExponent.toByteArray()));

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);


        byte[] b1 = Base64.decodeBase64(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {

        generateKeyPairString();

        //		String source = "luoxiaohui";//要加密的字符串
        //
        //		String cryptograph = encrypt(source);//生成的密文
        //		String hexCrypt = HexUtil.bytes2Hex(cryptograph.getBytes(),false);
        //		//System.out.println("生成的密文--->"+hexCrypt);
        //
        //		String target = decrypt(HexUtil.hex2String(hexCrypt));//解密密文
        //		//System.out.println("解密密文--->"+target);
        //


    }
}










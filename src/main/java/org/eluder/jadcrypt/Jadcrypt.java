package org.eluder.jadcrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Jadcrypt {

    public static String encrypt(String plain, String password, String salt) throws Exception {
        return encrypt(plain, password, salt, Presets.DEFAULTS);
    }

    public static String encrypt(String plain, String password, String salt, Presets presets) throws Exception {
        byte[] key = CryptUtils.pbkdf2(password, salt, presets);
        byte[] iv = CryptUtils.md5(salt);

        return CryptUtils.toHex(encryptRaw(plain.getBytes(Presets.CHARSET), key, iv));
    }

    public static byte[] encryptRaw(byte[] plain, byte[] key, byte[] iv) throws Exception {
        SecretKey secret = new SecretKeySpec(key, "AES");
        IvParameterSpec ips = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret, ips);
        return cipher.doFinal(plain);
    }

    public static String decrypt(String encrypted, String password, String salt) throws Exception {
        return decrypt(encrypted, password, salt, Presets.DEFAULTS);
    }

    public static String decrypt(String encrypted, String password, String salt, Presets presets) throws Exception {
        byte[] key = CryptUtils.pbkdf2(password, salt, presets);
        byte[] iv = CryptUtils.md5(salt);

        return new String(decryptRaw(CryptUtils.fromHex(encrypted), key, iv), Presets.CHARSET);
    }

    public static byte[] decryptRaw(byte[] encrypted, byte[] key, byte[] iv) throws Exception {
        SecretKey secret = new SecretKeySpec(key, "AES");
        IvParameterSpec ips = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, ips);
        return cipher.doFinal(encrypted);
    }

}

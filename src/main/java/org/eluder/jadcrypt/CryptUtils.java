package org.eluder.jadcrypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class CryptUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static byte[] random(int bits) {
        if (bits < 8 || bits % 8 != 0) {
            throw new IllegalArgumentException(bits + " does not match even byte count");
        }
        byte[] bytes = new byte[bits / 8];
        RANDOM.nextBytes(bytes);
        return bytes;
    }

    public static byte[] pbkdf2(String password, String salt, Presets presets) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmac" + presets.getPbkdf2Digest());
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(Presets.CHARSET),
                presets.getIterations(), presets.getKeyBits());
        return factory.generateSecret(spec).getEncoded();
    }

    public static byte[] md5(String value) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(value.getBytes(Presets.CHARSET));
    }

    public static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

    public static byte[] fromHex(String hex) {
        return DatatypeConverter.parseHexBinary(hex);
    }
}

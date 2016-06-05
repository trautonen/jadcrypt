package org.eluder.jadcrypt;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class JadcryptTest {

    final String message = "Hello wörld!";
    final String password = "l5681mm0Yfi486y";
    final String salt = "cjttN5DS";

    final byte[] rawMessage = message.getBytes(Presets.CHARSET);
    final byte[] key = CryptUtils.random(256);
    final byte[] iv = CryptUtils.random(128);

    @Test
    public void testEncryptAndDecryptWithDefaultPresets() throws Exception {
        String encrypted = Jadcrypt.encrypt(message, password, salt);
        String decrypted = Jadcrypt.decrypt(encrypted, password, salt);

        System.out.println(CryptUtils.toHex(key));
        System.out.println(CryptUtils.toHex(iv));

        assertEquals(message, decrypted);
    }

    @Test
    public void testEncryptAndDecryptWithMobilePresets() throws Exception {
        String encrypted = Jadcrypt.encrypt(message, password, salt, Presets.MOBILE);
        String decrypted = Jadcrypt.decrypt(encrypted, password, salt, Presets.MOBILE);

        assertEquals(message, decrypted);
    }

    @Test
    public void testPresetsAreDifferent() throws Exception {
        String encrypted1 = Jadcrypt.encrypt(message, password, salt, Presets.DEFAULTS);
        String encrypted2 = Jadcrypt.encrypt(message, password, salt, Presets.MOBILE);

        assertNotEquals(encrypted1, encrypted2);
    }

    @Test
    public void testEncryptRawAndDecryptRaw() throws Exception {
        byte[] encrypted = Jadcrypt.encryptRaw(rawMessage, key, iv);
        byte[] decrypted = Jadcrypt.decryptRaw(encrypted, key, iv);

        assertArrayEquals(rawMessage, decrypted);
    }
}

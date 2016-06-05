package org.eluder.jadcrypt;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InteropTest {

    final byte[] key = CryptUtils.fromHex(readResource("/key.txt"));
    final String password = readResource("/password.txt");

    @Test
    public void testDecryptRawFile() throws Exception {
        String[] encrypted = readResource("/encrypted-raw.txt").split(":");
        byte[] iv = CryptUtils.fromHex(encrypted[0]);
        byte[] content = CryptUtils.fromHex(encrypted[1]);

        byte[] decrypted = Jadcrypt.decryptRaw(content, key, iv);
        assertEquals("Hello world! This is sparta!", new String(decrypted, Presets.CHARSET));
    }

    @Test
    public void testDecryptPasswordFile() throws Exception {
        String[] encrypted = readResource("/encrypted-salt.txt").split(":");
        String salt = encrypted[0];
        String content = encrypted[1];

        String decrypted = Jadcrypt.decrypt(content, password, salt);
        assertEquals("Hello world! This is sparta!", decrypted);
    }

    private static String readResource(String name) {
        try {
            return IOUtils.toString(InteropTest.class.getResourceAsStream(name), "UTF-8");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

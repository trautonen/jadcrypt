package org.eluder.jadcrypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public enum Presets {

    DEFAULTS(256, 65536, "SHA256"),
    MOBILE(128, 4096, "SHA1");

    public static final Charset CHARSET = StandardCharsets.UTF_8;

    private final int keyBits;
    private final int iterations;
    private final String pbkdf2Digest;

    Presets(int keyBits, int iterations, String pbkdf2Digest) {
        this.keyBits = keyBits;
        this.iterations = iterations;
        this.pbkdf2Digest = pbkdf2Digest;
    }

    public int getKeyBits() {
        return keyBits;
    }

    public int getIterations() {
        return iterations;
    }

    public String getPbkdf2Digest() {
        return pbkdf2Digest;
    }
}

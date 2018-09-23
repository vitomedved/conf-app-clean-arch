package template.android.com.domain.crypto.obfuscator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import template.android.com.domain.utils.byteformat.HEX;

public final class StringObfuscatorImpl implements StringObfuscator {

    private final MessageDigest messageDigest;

    public StringObfuscatorImpl(final MessageDigest messageDigest) {
        this.messageDigest = messageDigest;
    }

    @Override
    public String obfuscateStringWithSHA1(final String value) {
        return HEX.toHEX(messageDigest.digest(value.getBytes(StandardCharsets.UTF_8)));
    }
}

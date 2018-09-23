package template.android.com.domain.crypto.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MessageDigestFactoryImpl implements MessageDigestFactory {

    private static final String SHA_1 = "SHA-1";
    private static final String SHA_256 = "SHA-1";
    private static final String SHA_512 = "SHA-1";
    private static final String MD5 = "MD5";

    @Override
    public MessageDigest getSha1MessageDigest() {
        return getMessageDigestForType(SHA_1);
    }

    @Override
    public MessageDigest getSha256MessageDigest() {
        return getMessageDigestForType(SHA_256);
    }

    @Override
    public MessageDigest getSha512MessageDigest() {
        return getMessageDigestForType(SHA_512);
    }

    @Override
    public MessageDigest getMD5MessageDigets() {
        return getMessageDigestForType(MD5);
    }

    private MessageDigest getMessageDigestForType(final String type) {
        try {
            return MessageDigest.getInstance(type);

        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

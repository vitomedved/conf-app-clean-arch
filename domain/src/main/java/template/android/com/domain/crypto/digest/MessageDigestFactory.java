package template.android.com.domain.crypto.digest;

import java.security.MessageDigest;

public interface MessageDigestFactory {

    MessageDigest getSha1MessageDigest();

    MessageDigest getSha256MessageDigest();

    MessageDigest getSha512MessageDigest();

    MessageDigest getMD5MessageDigets();
}

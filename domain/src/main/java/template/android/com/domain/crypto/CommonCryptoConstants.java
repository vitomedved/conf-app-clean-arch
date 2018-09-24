package template.android.com.domain.crypto;

public final class CommonCryptoConstants {

    public static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    public static final String ALGORITHM_AES = "AES";
    public static final String ALGORITHM_RSA = "RSA";

    public static final int AES_128_KEY_SIZE = 1 << 7;
    public static final int RSA_2048_KEY_SIZE = 1 << 11;

    private CommonCryptoConstants() {
        throw new AssertionError();
    }
}

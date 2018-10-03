package template.android.com.domain.crypto.engine;

public interface CryptoEngine {

    byte[] encrypt(byte[] bytes);

    byte[] decrypt(byte[] bytes);
}

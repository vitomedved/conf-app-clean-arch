package template.android.com.domain.crypto.engine;

public interface CryptoEngine {

    void initializeIv(byte[] bytes);

    byte encrypt(byte[] bytes);

    byte decrypt(byte[] bytes);
}

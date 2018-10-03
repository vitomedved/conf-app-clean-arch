package template.android.com.domain.crypto.engine;

public interface CryptoEngineFactory {

    CryptoEngine createCryptoEngineForKeyAlias(String alias);
}

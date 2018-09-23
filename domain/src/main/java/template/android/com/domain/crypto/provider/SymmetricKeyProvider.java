package template.android.com.domain.crypto.provider;

import javax.crypto.SecretKey;

public interface SymmetricKeyProvider {

    SecretKey getSymmetricAesCbcPkcs7PaddingKeyForAlias(String alias);

    void deleteSymmetricKey(String alias);

    void deleteAllKeys();
}

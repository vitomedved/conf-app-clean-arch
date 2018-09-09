package template.android.com.domain.crypto;

import java.security.Key;
import java.util.List;

public interface SecureKeystore {

    List<String> listAliases();

    boolean containsAlias(String alias);

    void removeAlias(String alias);

    Key getSecretKeyWithAlias(String alias);

    void createKey();

    final class SecureKeystoreException extends RuntimeException {

        public SecureKeystoreException(final Throwable throwable) {
            super(throwable);
        }
    }
}

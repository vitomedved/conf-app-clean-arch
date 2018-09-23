package template.android.com.domain.crypto.keystore;

import java.util.List;

import javax.crypto.SecretKey;

public interface SecureKeystore {

    List<String> listAliases();

    boolean containsAlias(String alias);

    void removeAlias(String alias);

    final class SecureKeystoreException extends RuntimeException {

        public SecureKeystoreException(final Throwable throwable) {
            super(throwable);
        }
    }
}

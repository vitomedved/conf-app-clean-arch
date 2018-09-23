package template.android.com.device.crypto.provider;

public interface SymmetricKeyProviderStorage {

    void saveKeyToPersistentStorage(String alias, byte[] bytes);

    byte[] getKeyFromStorage(String alias);

    boolean isKeySavedInStorage(String alias);

    void deleteKey(String alias);

    void deleteAllKeys();

    final class KeyDoesNotExistException extends RuntimeException {

        public KeyDoesNotExistException(final String message) {
            super(message);
        }
    }
}

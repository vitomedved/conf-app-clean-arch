package template.android.com.device.crypto.provider;

import android.content.Context;
import android.content.SharedPreferences;

import template.android.com.domain.crypto.obfuscator.StringObfuscator;
import template.android.com.domain.utils.byteformat.HEX;

public final class SymmetricKeyProviderStorageImpl implements SymmetricKeyProviderStorage {

    private static final String PREFS_FILE_NAME = "symmetric_key_provider_storage";

    private static final String EMPTY = "";

    private final StringObfuscator stringObfuscator;
    private final SharedPreferences sharedPreferences;

    public static SymmetricKeyProviderStorageImpl create(final Context context, final StringObfuscator stringObfuscator) {
        return new SymmetricKeyProviderStorageImpl(stringObfuscator,
                                                   context.getSharedPreferences(stringObfuscator.obfuscateStringWithSHA1(PREFS_FILE_NAME),
                                                                                Context.MODE_PRIVATE));
    }

    private SymmetricKeyProviderStorageImpl(final StringObfuscator stringObfuscator, final SharedPreferences sharedPreferences) {
        this.stringObfuscator = stringObfuscator;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveKeyToPersistentStorage(final String alias, final byte[] bytes) {
        sharedPreferences.edit()
                         .putString(stringObfuscator.obfuscateStringWithSHA1(alias), HEX.toHEX(bytes))
                         .apply();
    }

    @Override
    public byte[] getKeyFromStorage(final String alias) {
        if (!isKeySavedInStorage(alias)) {
            throw new KeyDoesNotExistException("Key with alias" + alias + " does not exist.");
        }

        return HEX.fromHEX(sharedPreferences.getString(stringObfuscator.obfuscateStringWithSHA1(alias), EMPTY));
    }

    @Override
    public void deleteKey(final String alias) {
        sharedPreferences.edit()
                         .remove(stringObfuscator.obfuscateStringWithSHA1(alias))
                         .apply();
    }

    @Override
    public void deleteAllKeys() {
        sharedPreferences.edit()
                         .clear()
                         .apply();
    }

    @Override
    public boolean isKeySavedInStorage(final String alias) {
        return sharedPreferences.contains(stringObfuscator.obfuscateStringWithSHA1(alias));
    }
}

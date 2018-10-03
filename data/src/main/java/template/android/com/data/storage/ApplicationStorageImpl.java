package template.android.com.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

import template.android.com.domain.crypto.engine.CryptoEngine;
import template.android.com.domain.storage.ApplicationStorage;
import template.android.com.domain.utils.byteformat.HEX;

public final class ApplicationStorageImpl implements ApplicationStorage {

    private static final String APPLICATION_STORAGE = "application_storage";

    private static final String EMPTY = "";

    private final CryptoEngine cryptoEngine;
    private final SharedPreferences sharedPreferences;

    public static ApplicationStorageImpl create(final Context context, final CryptoEngine cryptoEngine) {
        return new ApplicationStorageImpl(cryptoEngine, context.getSharedPreferences(APPLICATION_STORAGE, Context.MODE_PRIVATE));
    }

    ApplicationStorageImpl(final CryptoEngine cryptoEngine, final SharedPreferences sharedPreferences) {
        this.cryptoEngine = cryptoEngine;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveString(final String key, final String value) {
        sharedPreferences.edit()
                         .putString(key, HEX.toHEX(cryptoEngine.encrypt(value.getBytes())))
                         .apply();
    }

    @Override
    public String getString(final String key) {

        if (!sharedPreferences.contains(key)) {
            return EMPTY;
        }

        return new String(cryptoEngine.decrypt(HEX.fromHEX(sharedPreferences.getString(key, EMPTY))));
    }
}

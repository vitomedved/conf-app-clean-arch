package template.android.com.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public final class ExamplePrefsImpl implements ExamplePrefs {

    private static final String FILE_NAME = "example_prefs";

    private final SharedPreferences preferences;

    public static ExamplePrefsImpl create(final Context context) {
        return new ExamplePrefsImpl(context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE));
    }

    public ExamplePrefsImpl(final SharedPreferences preferences) {
        this.preferences = preferences;
    }
}

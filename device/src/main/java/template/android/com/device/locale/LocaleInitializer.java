package template.android.com.device.locale;

import android.content.Context;

import java.util.Locale;

public interface LocaleInitializer {

    Context initializeActivityForLocale(Context context, Locale locale);
}

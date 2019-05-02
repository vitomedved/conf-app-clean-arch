package template.android.com.device.locale;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public final class LocaleInitializerImpl implements LocaleInitializer {

    @Override
    public Context initializeActivityForLocale(final Context context, final Locale targetLocale) {

        Locale.setDefault(targetLocale);

        final Configuration configuration = context.getResources().getConfiguration();
        configuration.setLayoutDirection(targetLocale);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            configuration.setLocale(targetLocale);

            return context.createConfigurationContext(configuration);

        } else {
            configuration.locale = targetLocale;
            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

            return context;
        }
    }
}

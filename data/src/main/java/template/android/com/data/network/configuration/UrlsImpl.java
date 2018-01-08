package template.android.com.data.network.configuration;

import android.content.res.Resources;

import template.android.com.data.R;

public final class UrlsImpl implements Urls {

    private final Resources resources;

    public UrlsImpl(final Resources resources) {
        this.resources = resources;
    }

    @Override
    public String getServerUrl() {
        return resources.getString(R.string.server_url);
    }
}

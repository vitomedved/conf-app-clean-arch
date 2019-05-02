package com.android.template.utils.animation;

import android.content.res.Resources;

import com.android.template.R;

public final class AnimationInfoProviderImpl implements AnimationInfoProvider {

    private final Resources resources;

    public AnimationInfoProviderImpl(final Resources resources) {
        this.resources = resources;
    }

    @Override
    public int getDefaultButtonDelay() {
        return resources.getInteger(R.integer.default_button_animation_duration);
    }
}

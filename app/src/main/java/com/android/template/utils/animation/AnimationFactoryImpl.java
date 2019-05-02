package com.android.template.utils.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public final class AnimationFactoryImpl implements AnimationFactory {

    private final Context context;

    public AnimationFactoryImpl(final Context context) {
        this.context = context;
    }

    @Override
    public Animation getDefaultFadeInAnimation() {
        return AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
    }

    @Override
    public Animation getDefaultFadeOutAnimation() {
        return AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
    }
}

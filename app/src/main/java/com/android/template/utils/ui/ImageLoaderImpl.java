package com.android.template.utils.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public final class ImageLoaderImpl implements ImageLoader {

    private final Context context;

    public ImageLoaderImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(final String url, final ImageView target) {
        Glide.with(context)
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .fitCenter()
             .crossFade()
             .into(target);
    }

    @Override
    public void loadImageCenterCrop(final String url, final ImageView target) {
        Glide.with(context)
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .centerCrop()
             .crossFade()
             .into(target);
    }

    @Override
    public void loadImage(final String url, final ImageView target, @DrawableRes final int placeholderDrawable, @DrawableRes final int errorDrawable) {
        Glide.with(context)
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .placeholder(placeholderDrawable)
             .error(errorDrawable)
             .crossFade()
             .into(target);
    }

    @Override
    public void loadImage(final String url, final ImageView target, final Drawable placeholderDrawable, @DrawableRes final int errorDrawable) {
        Glide.with(context)
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .placeholder(placeholderDrawable)
             .error(errorDrawable)
             .crossFade()
             .into(target);
    }

    @Override
    public void loadImageAsBitmap(final String url, final ImageView target, final Drawable placeholderDrawable, @DrawableRes final int errorDrawable) {
        Glide.with(context)
             .load(url)
             .asBitmap()
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .placeholder(placeholderDrawable)
             .error(errorDrawable)
             .into(target);
    }

    @Override
    public void loadImageForCircularImageView(final String url, final ImageView target, final Drawable placeholderDrawable, @DrawableRes final int errorDrawable) {
        Glide.with(context)
             .load(url)
             .asBitmap()
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .centerCrop()
             .placeholder(placeholderDrawable)
             .error(errorDrawable)
             .into(target);
    }
}

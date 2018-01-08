package com.android.template.injection.fragment.module;

import com.android.template.injection.fragment.DaggerFragment;

import dagger.Module;

@Module
public final class FragmentPresenterModule {

    private final DaggerFragment fragment;

    public FragmentPresenterModule(final DaggerFragment fragment) {
        this.fragment = fragment;
    }

    public interface Exposes {

    }
}

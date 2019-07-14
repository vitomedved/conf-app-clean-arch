package com.android.template.injection.fragment.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.android.template.injection.fragment.DaggerFragment;
import com.android.template.injection.qualifier.ForApplication;
import com.android.template.injection.scope.FragmentScope;
import com.android.template.ui.schedule.adapter.ScheduleRecyclerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public final class FragmentModule {

    private final DaggerFragment fragment;

    public FragmentModule(final DaggerFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    FragmentManager provideFragmentManager() {
        return fragment.getChildFragmentManager();
    }

    @Provides
    @FragmentScope
    LinearLayoutManager provideLinearLayoutManager(@ForApplication final Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    @FragmentScope
    ScheduleRecyclerAdapter provideScheduleRecyclerAdapter(final LayoutInflater layoutInflater) {
        return new ScheduleRecyclerAdapter(layoutInflater);
    }
}

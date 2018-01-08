package com.android.template.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.template.injection.activity.DaggerActivity;
import com.android.template.utils.ActivityUtils;

import java.util.List;

import javax.inject.Inject;

public abstract class BaseActivity extends DaggerActivity {

    @Inject
    protected FragmentManager fragmentManager;

    @Inject
    ActivityUtils activityUtils;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPresenter();
    }

    private void startPresenter() {
        getPresenter().start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().activate();
    }

    @Override
    protected void onPause() {
        getPresenter().deactivate();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (!activityUtils.propagateBackToTopFragment(fragmentManager)) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            getPresenter().destroy();
            clearFragments();
        }
        super.onDestroy();
    }

    @SuppressWarnings("Convert2streamapi")
    private void clearFragments() {
        final List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (final Fragment fragment : fragments) {
                if (fragment instanceof BaseFragment) {
                    ((BaseFragment) fragment).onPreDestroy();
                }
            }
        }
    }

    protected abstract ScopedPresenter getPresenter();
}

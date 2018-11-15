package com.android.template.injection.factory;

import com.android.template.application.ExampleApplication;
import com.android.template.injection.activity.ActivityComponent;
import com.android.template.injection.activity.DaggerActivity;
import com.android.template.injection.activity.DaggerActivityComponent;
import com.android.template.injection.activity.module.ActivityModule;
import com.android.template.injection.activity.module.ActivityPresenterModule;
import com.android.template.injection.application.ApplicationComponent;
import com.android.template.injection.application.DaggerApplicationComponent;
import com.android.template.injection.application.module.ApplicationModule;
import com.android.template.injection.fragment.DaggerFragment;
import com.android.template.injection.fragment.DaggerFragmentComponent;
import com.android.template.injection.fragment.FragmentComponent;
import com.android.template.injection.fragment.module.FragmentModule;
import com.android.template.injection.fragment.module.FragmentPresenterModule;
import com.android.template.injection.user.DaggerUserComponent;
import com.android.template.injection.user.UserComponent;

public final class ComponentFactory {

    public static ApplicationComponent createApplicationComponent(final ExampleApplication application) {
        return DaggerApplicationComponent.builder()
                                         .applicationModule(new ApplicationModule(application))
                                         .build();
    }

    public static UserComponent createUserComponent(final ApplicationComponent applicationComponent) {
        return DaggerUserComponent.builder().applicationComponent(applicationComponent)
                                  .build();
    }

    public static ActivityComponent createActivityComponent(final DaggerActivity activity, final ExampleApplication application) {
        return DaggerActivityComponent.builder()
                                      .userComponent(application.getUserComponent())
                                      .activityModule(new ActivityModule(activity))
                                      .activityPresenterModule(new ActivityPresenterModule(activity))
                                      .build();
    }

    public static FragmentComponent createFragmentComponent(final DaggerFragment fragment, final ActivityComponent component) {
        return DaggerFragmentComponent.builder()
                                      .activityComponent(component)
                                      .fragmentModule(new FragmentModule(fragment))
                                      .fragmentPresenterModule(new FragmentPresenterModule(fragment))
                                      .build();
    }
}

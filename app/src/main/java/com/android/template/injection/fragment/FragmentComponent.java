package com.android.template.injection.fragment;

import com.android.template.injection.activity.ActivityComponent;
import com.android.template.injection.fragment.module.FragmentModule;
import com.android.template.injection.fragment.module.FragmentPresenterModule;
import com.android.template.injection.fragment.module.FragmentUiModule;
import com.android.template.injection.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = {
                ActivityComponent.class
        },
        modules = {
                FragmentModule.class,
                FragmentUiModule.class,
                FragmentPresenterModule.class
        }
)
public interface FragmentComponent extends FragmentComponentInjects,
                                           FragmentComponentExposes { }

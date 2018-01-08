package com.android.template.injection.fragment;

import com.android.template.injection.activity.ActivityComponentExposes;
import com.android.template.injection.fragment.module.FragmentPresenterModule;
import com.android.template.injection.fragment.module.FragmentUiModule;

public interface FragmentComponentExposes extends ActivityComponentExposes,
                                                  FragmentUiModule.Exposes,
                                                  FragmentPresenterModule.Exposes { }

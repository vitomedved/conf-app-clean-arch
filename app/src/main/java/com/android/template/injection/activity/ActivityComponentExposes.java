package com.android.template.injection.activity;

import com.android.template.injection.activity.module.ActivityModule;
import com.android.template.injection.activity.module.ActivityPresenterModule;
import com.android.template.injection.activity.module.UiAdapterModule;
import com.android.template.injection.user.UserComponentExposes;

public interface ActivityComponentExposes extends UserComponentExposes,
                                                  ActivityModule.Exposes,
                                                  ActivityPresenterModule.Exposes,
                                                  UiAdapterModule.Exposes { }

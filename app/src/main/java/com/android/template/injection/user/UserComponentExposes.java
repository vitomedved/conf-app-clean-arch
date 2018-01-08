package com.android.template.injection.user;

import com.android.template.injection.application.ApplicationComponentExposes;
import com.android.template.injection.user.module.ServiceModule;
import com.android.template.injection.user.module.UserApiClientModule;
import com.android.template.injection.user.module.UserRepositoryModule;
import com.android.template.injection.user.module.UserUseCaseModule;

public interface UserComponentExposes extends ApplicationComponentExposes,
                                              ServiceModule.Exposes,
                                              UserApiClientModule.Exposes,
                                              UserRepositoryModule.Exposes,
                                              UserUseCaseModule.Exposes { }

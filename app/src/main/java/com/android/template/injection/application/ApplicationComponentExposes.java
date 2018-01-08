package com.android.template.injection.application;

import com.android.template.injection.application.module.AndroidSystemModule;
import com.android.template.injection.application.module.ApiModule;
import com.android.template.injection.application.module.ApplicationModule;
import com.android.template.injection.application.module.ConnectivityModule;
import com.android.template.injection.application.module.DataModule;
import com.android.template.injection.application.module.DbModule;
import com.android.template.injection.application.module.RepositoryModule;
import com.android.template.injection.application.module.ThreadingModule;
import com.android.template.injection.application.module.UseCaseModule;
import com.android.template.injection.application.module.UtilsModule;

public interface ApplicationComponentExposes extends ApplicationModule.Exposes,
                                                     AndroidSystemModule.Exposes,
                                                     ApiModule.Exposes,
                                                     ConnectivityModule.Exposes,
                                                     DataModule.Exposes,
                                                     DbModule.Exposes,
                                                     RepositoryModule.Exposes,
                                                     ThreadingModule.Exposes,
                                                     UseCaseModule.Exposes,
                                                     UtilsModule.Exposes { }

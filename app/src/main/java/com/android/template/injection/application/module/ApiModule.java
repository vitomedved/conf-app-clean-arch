package com.android.template.injection.application.module;

import android.content.res.Resources;

import com.android.template.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import template.android.com.data.network.configuration.Urls;
import template.android.com.data.network.configuration.UrlsImpl;
import template.android.com.data.network.converter.ApiMapper;
import template.android.com.data.network.converter.ApiMapperImpl;
import template.android.com.data.network.service.ExampleApiService;
import template.android.com.domain.utils.string.StringUtils;

@Module
public final class ApiModule {

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor interceptor) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(interceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    Urls provideUrls(final Resources resources) {
        return new UrlsImpl(resources);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final Urls urls, final OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(urls.getServerUrl())
                                     .client(okHttpClient)
                                     .addConverterFactory(GsonConverterFactory.create())
                                     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                     .build();
    }

    @Provides
    @Singleton
    ExampleApiService provideExampleApiService(final Retrofit retrofit) {
        return retrofit.create(ExampleApiService.class);
    }

    @Provides
    @Singleton
    ApiMapper provideApiConverter(final StringUtils stringUtils) {
        return new ApiMapperImpl(stringUtils);
    }

    public interface Exposes {

        ApiMapper apiConverter();

        ExampleApiService exampleApiService();
    }
}

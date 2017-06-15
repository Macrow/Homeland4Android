package org.mstudio.homeland4android.di.module;

import org.mstudio.homeland4android.data.TokenManager;
import org.mstudio.homeland4android.data.network.ApiService;
import org.mstudio.homeland4android.data.network.OAuthService;
import org.mstudio.homeland4android.util.AppConstant;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/6/13
 * desc   :
 */
@Module
public class BasicModule {

    private TokenManager mTokenManager;

    public BasicModule(TokenManager tokenManager) {
        mTokenManager = tokenManager;
    }

    @Singleton
    @Provides
    TokenManager provideTokenManager() {
        return mTokenManager;
    }

    @Singleton
    @Provides
    OkHttpClient provideOKHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Singleton
    @Named("Api")
    @Provides
    Retrofit provideApiRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(AppConstant.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Named("OAuth")
    @Provides
    Retrofit provideOAuthRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    ApiService provideApiService(@Named("Api") Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


    @Singleton
    @Provides
    OAuthService provideOAuthService(@Named("OAuth") Retrofit retrofit) {
        return retrofit.create(OAuthService.class);
    }
}

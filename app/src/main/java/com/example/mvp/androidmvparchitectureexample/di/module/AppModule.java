/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.mvp.androidmvparchitectureexample.BuildConfig;
import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.local.dao.ArticleDao;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public static ArticleDao providesArticleDao(LocalDataSource localDataSource) {
        return localDataSource.getArticleDao();
    }

    @Provides
    @Singleton
    public static RemoteService providesRemoteService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(RemoteService.class);
    }

    private static OkHttpClient getOkHttpClient() {

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl mUrl = originalHttpUrl.newBuilder()
//                                .addQueryParameter(Constants.NAME_KEY_API_NEWS, Constants.VALUE_KEY_API_NEWS)
//                                .addQueryParameter(Constants.NAME_COUNTRY_API_NEWS, Constants.VALUE_COUNTRY_API_NEWS)
                            .build();


                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .url(mUrl)
                            .build();


                    okhttp3.Response response = chain.proceed(request);
                    response.cacheResponse();
                    return response;

                })
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public static RemoteDataSource providesRemoteDataSource(RemoteService remoteService) {
        return new RemoteDataSource(remoteService);
    }

    @Singleton
    @Provides
    public LocalDataSource providesRoomDataSource() {
        return Room.databaseBuilder(application, LocalDataSource.class, "news_database")
                .build();
    }

    @Provides
    public Context providesAppContext() {
        return application;
    }

}

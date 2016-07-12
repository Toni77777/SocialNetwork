package by.grodno.toni7777.socialnetwork.di;

import android.content.Context;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.network.LoginService;
import by.grodno.toni7777.socialnetwork.util.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    public ApplicationModule() {
    }

    @Provides
    @Singleton
    public LoginService providesLogin() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(LoginService.class);
    }

}

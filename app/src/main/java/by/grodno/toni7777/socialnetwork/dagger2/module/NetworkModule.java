package by.grodno.toni7777.socialnetwork.dagger2.module;

import android.content.Context;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    final String mBaseUrl;
    final Context mContext;

    public NetworkModule(String baseUrl, Context context) {
        mBaseUrl = baseUrl;
        mContext = context;
    }

    //    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient() {
//        return new OkHttpClient().newBuilder().build();
//    }
    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }

    @Singleton
    @Provides
    SocialNetworkAPI provideNetService(Retrofit retrofit) {
        return retrofit.create(SocialNetworkAPI.class);
    }

    @Provides
    @Singleton
    LoginPreferences providesSharedPreferences() {
        return new LoginPreferences(mContext);
    }

}

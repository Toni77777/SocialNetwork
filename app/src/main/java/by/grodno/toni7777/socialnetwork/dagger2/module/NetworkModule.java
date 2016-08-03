package by.grodno.toni7777.socialnetwork.dagger2.module;

import javax.inject.Singleton;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.wall.WallPresenter;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    final String mBaseUrl;

    public NetworkModule(String baseUrl) {
        mBaseUrl = baseUrl;
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

    @Singleton
    @Provides
    public WallPresenter provideWallPresenter(SocialNetworkAPI socialNetworkAPI) {
        return new WallPresenter(socialNetworkAPI);
    }

}

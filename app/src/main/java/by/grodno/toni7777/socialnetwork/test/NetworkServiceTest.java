package by.grodno.toni7777.socialnetwork.test;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * I need this class for tess,
 * because Ipiary api get different base URL
 */
public class NetworkServiceTest {

    public static SocialNetworkAPI netWall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-5b721-wallposts.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(SocialNetworkAPI.class);
    }

    public static SocialNetworkAPI netFriends() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-5821d-friends9.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(SocialNetworkAPI.class);
    }
}

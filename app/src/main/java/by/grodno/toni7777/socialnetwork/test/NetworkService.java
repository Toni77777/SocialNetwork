package by.grodno.toni7777.socialnetwork.test;

import by.grodno.toni7777.socialnetwork.network.NetService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    public static NetService netWall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-5b721-wallposts.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(NetService.class);
    }

    public static NetService netFriends() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-5821d-friends9.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(NetService.class);
    }
}

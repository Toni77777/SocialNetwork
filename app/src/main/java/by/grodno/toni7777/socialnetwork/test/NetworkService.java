package by.grodno.toni7777.socialnetwork.test;

import by.grodno.toni7777.socialnetwork.network.WallService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    public static WallService netWall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sjc2016vs3.fwd.wf")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(WallService.class);
    }
}

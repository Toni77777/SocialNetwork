package by.grodno.toni7777.socialnetwork.test;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * I need this class for tess,
 * because Ipiary api get different base URL
 */
public class NetworkServiceTest {

    public static SocialNetworkAPI netWall() {

        String s = "Basic cGFzc3dvcmRDbGllbnQ6MG00NWJ4cDRyMg==";

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", s)
                            .method(original.method(), original.body());


                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://private-bc396-authorisation.apiary-mock.com")
                .baseUrl("https://sjc2016vs5.fwd.wf")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
//                .client(new OkHttpClient.Builder().build())
                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://private-5b721-wallposts.apiary-mock.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(new OkHttpClient.Builder().build())
//                .build();

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

    public static SocialNetworkAPI netLogin() {

        String s = "Basic cGFzc3dvcmRDbGllbnQ6MG00NWJ4cDRyMg==";

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", s)
                            .method(original.method(), original.body());


                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://private-bc396-authorisation.apiary-mock.com")
                .baseUrl("https://sjc2016vs5.fwd.wf")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
//                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(SocialNetworkAPI.class);
    }

    public static SocialNetworkAPI netProfile() {

        String s = "Basic cGFzc3dvcmRDbGllbnQ6MG00NWJ4cDRyMg==";

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", s)
                            .method(original.method(), original.body());


                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://private-bc396-authorisation.apiary-mock.com")
                .baseUrl("https://sjc2016vs5.fwd.wf")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
//                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(SocialNetworkAPI.class);
    }
}

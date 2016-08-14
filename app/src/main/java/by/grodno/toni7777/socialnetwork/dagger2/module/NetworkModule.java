package by.grodno.toni7777.socialnetwork.dagger2.module;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.*;

import by.grodno.toni7777.socialnetwork.database.DatabaseDAOImp;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header(HEADER_AUTORIZATION, HEADER_AUTORIZATION_VALUE)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
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

    @Provides
    @Singleton
    DatabaseDAOImp providesDatabaseDAO() {
        return new DatabaseDAOImp();
    }

}

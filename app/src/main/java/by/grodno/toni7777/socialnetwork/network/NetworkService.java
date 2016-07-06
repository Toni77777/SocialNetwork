package by.grodno.toni7777.socialnetwork.network;

import by.grodno.toni7777.socialnetwork.util.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class NetworkService {
    private LoginService loginService;

    public NetworkService() {
        initService();
    }

    private void initService() {
        OkHttpClient okHttpClient = newClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        loginService = retrofit.create(LoginService.class);
    }


    public OkHttpClient newClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            return response;
        });

        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().build();
            return chain.proceed(request);
        });

        return builder.build();
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable) {
        return unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

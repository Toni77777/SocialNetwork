package by.grodno.toni7777.socialnetwork.network;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.test.UserLogin;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.*;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LoginService {

    @GET(BuildConfig.LOGIN_URL)
    Observable<UserLogin> loginRequest(@Query(GRAND_TYPE) String grandType,
                                       @Query(CLIENT_ID) String clientId,
                                       @Query(USER_NAME) String username,
                                       @Query(USER_PASSWORD) String password);


}

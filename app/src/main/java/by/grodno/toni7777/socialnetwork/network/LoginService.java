package by.grodno.toni7777.socialnetwork.network;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.test.UserLogin;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

public interface LoginService {

//    @GET(BuildConfig.LOGIN_URL)
//    Observable<UserLogin> loginRequest(@Query(LOGIN) String login,
//                                       @Query(PASSWORD) String pass);

    @GET(BuildConfig.LOGIN_URL)
    Observable<UserLogin> loginRequest(@Query("grant_type") String grandType,
                                       @Query("client_id") String clientId,
                                       @Query("username") String username,
                                       @Query("password") String password);


}

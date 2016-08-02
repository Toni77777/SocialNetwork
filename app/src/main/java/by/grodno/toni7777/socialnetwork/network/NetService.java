package by.grodno.toni7777.socialnetwork.network;

import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.network.model.UserLoginDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.CLIENT_ID;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.GRAND_TYPE;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.LIMIT;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.OFFSET;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_ID;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_NAME;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_PASSWORD;

public interface NetService {

    @GET(BuildConfig.LOGIN_URL)
    Observable<UserLoginDTO> loginRequest(@Query(GRAND_TYPE) String grandType,
                                          @Query(CLIENT_ID) String clientId,
                                          @Query(USER_NAME) String username,
                                          @Query(USER_PASSWORD) String password);

    @GET(BuildConfig.POSTS_URL)
    Observable<WallDTO> getPost(@Query(USER_ID) int userId,
                                @Query(OFFSET) int offset,
                                @Query(LIMIT) int limit);


//    @GET(BuildConfig.FRIENDS_URL) // need for request get friends list
//    Observable<FriendsDTO> getFriends();


}

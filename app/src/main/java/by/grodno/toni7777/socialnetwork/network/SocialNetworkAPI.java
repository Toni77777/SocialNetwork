package by.grodno.toni7777.socialnetwork.network;

import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.network.model.UserDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.CLIENT_ID;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.FRIENDS_URL;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.GRAND_TYPE;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.LIMIT;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.OFFSET;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_ID;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_NAME;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_PASSWORD;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.LOGIN_URL;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.POSTS_URL;

public interface SocialNetworkAPI {

    @POST(LOGIN_URL)
    Observable<UserDTO> loginRequest(@Query(GRAND_TYPE) String grandType,
                                     @Query(CLIENT_ID) String clientId,
                                     @Query(USER_NAME) String username,
                                     @Query(USER_PASSWORD) String password);

    @GET(POSTS_URL)
    Observable<WallDTO> getPost(@Query(USER_ID) int userId,
                                @Query(OFFSET) int offset,
                                @Query(LIMIT) int limit);

    @GET(FRIENDS_URL)
    Observable<FriendsDTO> getFriends(@Query(USER_ID) int userId,
                                      @Query(OFFSET) int offset,
                                      @Query(LIMIT) int limit);
}

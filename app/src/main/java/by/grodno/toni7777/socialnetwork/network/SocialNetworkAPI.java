package by.grodno.toni7777.socialnetwork.network;

import by.grodno.toni7777.socialnetwork.network.model.PeopleDTO;
import by.grodno.toni7777.socialnetwork.network.model.UserLoginDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.*;

public interface SocialNetworkAPI {

    @POST(LOGIN_URL)
    Observable<UserLoginDTO> loginRequest(@Query(GRAND_TYPE) String grandType,
                                          @Query(CLIENT_ID) String clientId,
                                          @Query(USER_NAME) String username,
                                          @Query(USER_PASSWORD) String password);

    @GET(POSTS_URL)
    Observable<WallDTO> getPost(@Query(USER_ID) int userId,
                                @Query(OFFSET) int offset,
                                @Query(LIMIT) int limit);

    @GET(FRIENDS_URL)
    Observable<PeopleDTO> getFriends(@Query(USER_ID) int userId,
                                     @Query(OFFSET) int offset,
                                     @Query(LIMIT) int limit);

    @GET(PEOPLE_URL)
    Observable<PeopleDTO> getPeople(@Query(NAME) String name,
                                    @Query(OFFSET) int offset,
                                    @Query(LIMIT) int limit);
}

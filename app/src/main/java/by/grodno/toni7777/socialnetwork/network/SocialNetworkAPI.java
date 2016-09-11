package by.grodno.toni7777.socialnetwork.network;


import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.network.model.DialogsDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.network.model.GroupDataDTO;
import by.grodno.toni7777.socialnetwork.network.model.GroupsDTO;
import by.grodno.toni7777.socialnetwork.network.model.ImageResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.LikeDTO;
import by.grodno.toni7777.socialnetwork.network.model.LikeResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.NewPostDTO;
import by.grodno.toni7777.socialnetwork.network.model.PersonsDTO;
import by.grodno.toni7777.socialnetwork.network.model.ResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.*;

public interface SocialNetworkAPI {

    @POST(AUTHORIZATION_URL)
    Observable<AuthorizationDTO> loginRequest(@Query(GRAND_TYPE) String grandType,
                                              @Query(CLIENT_ID) String clientId,
                                              @Query(USER_NAME) String username,
                                              @Query(USER_PASSWORD) String password);

    @GET(POSTS_URL)
    Observable<WallDTO> getPost(@Query(USER_ID) long userId,
                                @Query(OFFSET) int offset,
                                @Query(LIMIT) int limit,
                                @Query(ACCESS_TOKEN) String accessToken);

    @GET(FRIENDS_URL)
    Observable<FriendsDTO> getFriends(@Query(USER_ID) long userId,
                                      @Query(OFFSET) int offset,
                                      @Query(LIMIT) int limit,
                                      @Query(ACCESS_TOKEN) String accessToken);

    @GET(PROFILE_URL)
    Observable<ProfileDTO> getProfileInfo(@Query(ACCESS_TOKEN) String accessToken);

    @DELETE(POSTS_URL)
    Observable<ResponseDTO> removePost(@Query(POST_ID) Long postId,
                                       @Query(ACCESS_TOKEN) String accessToken);

    @POST(POSTS_URL)
    Observable<ResponseDTO> sendNewPost(@Body NewPostDTO newPost,
                                        @Query(ACCESS_TOKEN) String accessToken);

    @Multipart
    @POST(FILE_URL)
    Observable<ImageResponseDTO> uploadImageToServer(@Part MultipartBody.Part image,
                                                     @Part(FILE_NAME) RequestBody name,
                                                     @Query(ACCESS_TOKEN) String accessToken);

    @POST(REGISTRATION_URL)
    Observable<ResponseDTO> registration(@Body RequestBody params);

    @GET(PERSONS_FIND_URL)
    Observable<PersonsDTO> findPersons(@Query(FULL_NAME) String fullName,
                                       @Query(OFFSET) int offset,
                                       @Query(LIMIT) int limit,
                                       @Query(ACCESS_TOKEN) String accessToken);

    @GET(GROUPS_URL)
    Observable<GroupsDTO> getGroups(@Query(USER_ID) long userId,
                                    @Query(OFFSET) int offset,
                                    @Query(LIMIT) int limit,
                                    @Query(ACCESS_TOKEN) String accessToken);

    @GET(GROUPS_FIND_URL)
    Observable<GroupsDTO> findGroups(@Query(GROUP_NAME) String nameGroup,
                                     @Query(OFFSET) int offset,
                                     @Query(LIMIT) int limit,
                                     @Query(ACCESS_TOKEN) String accessToken);

    @POST(FRIENDS_URL)
    Observable<ResponseDTO> addPersonToFriend(@Query(USER_ID) Long userId,
                                              @Query(ACCESS_TOKEN) String accessToken);

    @Headers(HEADER_CONTENT_TYPE_APP_JSON)
    @POST(FAVORITE_GROUP_URL)
    Observable<ResponseDTO> addGroupToFavorite(@Path(ID) Long groupId,
                                               @Query(ACCESS_TOKEN) String accessToken);

    @PUT(NOTIFICATION_URL)
    Observable<ResponseDTO> sentFCMToken(@Body RequestBody params,
                                         @Query(ACCESS_TOKEN) String accessToken);

    @GET(GROUP_POSTS_URL)
    Observable<WallDTO> getGroupPosts(@Query(GROUP_ID) long groupId,
                                      @Query(OFFSET) int offset,
                                      @Query(LIMIT) int limit,
                                      @Query(ACCESS_TOKEN) String accessToken);

    @GET(DIALOGS_URL)
    Observable<DialogsDTO> getDialogs(@Query(OFFSET) int offset,
                                      @Query(LIMIT) int limit,
                                      @Query(ACCESS_TOKEN) String accessToken);

    @Headers(HEADER_CONTENT_TYPE_APP_JSON)
    @POST(USERS_LIKE_POST_URL)
    Observable<LikeResponseDTO> likeUserPost(@Path(PATH_POST_ID) Long postId,
                                             @Body LikeDTO like,
                                             @Query(ACCESS_TOKEN) String accessToken);

    @GET(USERS_PROFILE_INFO_URL)
    Observable<ProfileDTO> getUserProfile(@Path(PATH_USER_ID) Long userId,
                                          @Query(ACCESS_TOKEN) String accessToken);

    @GET(FAVORITE_GROUP_URL)
    Observable<GroupDataDTO> getGroupInfo(@Path(ID) Long userId,
                                          @Query(ACCESS_TOKEN) String accessToken);

    @POST(AUTHORIZATION_URL)
    Observable<AuthorizationDTO> refreshAccessToken(@Query(GRAND_TYPE) String grandType,
                                                    @Query(CLIENT_ID) String clientId,
                                                    @Query(REFRESH_TOKEN) String refreshToken);

    @DELETE(FRIENDS_URL)
    Observable<ResponseDTO> removeUserFormFriends(@Query(USER_ID) long userId,
                                                  @Query(ACCESS_TOKEN) String accessToken);
}
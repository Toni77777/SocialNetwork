package by.grodno.toni7777.socialnetwork.network;

import android.graphics.Bitmap;

import java.io.File;
import java.util.Map;

import by.grodno.toni7777.socialnetwork.network.model.AuthorizationDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.network.model.ImageResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.NewPostDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostResponseDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.ACCESS_TOKEN;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.CLIENT_ID;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.FILE_URL;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.FRIENDS_URL;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.GRAND_TYPE;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.LIMIT;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.OFFSET;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.POST_ID;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_ID;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_NAME;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.USER_PASSWORD;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.LOGIN_URL;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.POSTS_URL;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.PROFILE_URL;
import static by.grodno.toni7777.socialnetwork.network.QueryProperties.FILE_NAME;

public interface SocialNetworkAPI {

    @POST(LOGIN_URL)
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
    Observable<PostResponseDTO> removePost(@Query(POST_ID) Long postId,
                                           @Query(ACCESS_TOKEN) String accessToken);

    @POST(POSTS_URL)
    Observable<PostResponseDTO> sendNewPost(@Body NewPostDTO newPost,
                                            @Query(ACCESS_TOKEN) String accessToken);

    @Multipart
    @POST(FILE_URL)
    Observable<ImageResponseDTO> uploadImageToServer(@Part MultipartBody.Part image,
                                                     @Part(FILE_NAME) RequestBody name,
                                                     @Query(ACCESS_TOKEN) String accessToken);

// @Multipart
//    @POST(FILE_URL)
//    Observable<ImageResponseDTO> uploadImageToServer(@Part("file") Bitmap bitmap,
//                                                     @Part(FILE_NAME) String name,
//                                                     @Query(ACCESS_TOKEN) String accessToken);


    //(@Part MultipartBody.Part image, @Part("name") RequestBody name);
//
//    @Multipart
//    @POST("/")
//    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);
//}
//    @Multipart
//    @POST(FILE_URL)
//    Observable<ImageResponseDTO> uploadImageToServer(@PartMap Map<String, RequestBody> params,
//                                                     @Query(ACCESS_TOKEN) String accessToken);


}
package by.grodno.toni7777.socialnetwork.network;

import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.socialnetwork.network.QueryProperties.*;

public interface WallService {

    @GET(POSTS_URL)
    Observable<WallDTO> getPost(@Query(USER_ID) String userId,
                                @Query(OFFSET) String offset,
                                @Query(LIMIT) String limit);

//    https://sjc2016vs3.fwd.wf/posts?idUser=1&offset=0&limit=10

}

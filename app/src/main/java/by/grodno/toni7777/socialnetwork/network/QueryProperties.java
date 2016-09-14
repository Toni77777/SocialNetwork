package by.grodno.toni7777.socialnetwork.network;


public final class QueryProperties {

    public static final String GRAND_TYPE = "grant_type";
    public static final String GRAND_TYPE_PASSWORD = "password";
    public static final String GRAND_TYPE_REFRESH_TOKEN = "refresh_token";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_ID_VALUE = "passwordClient";
    public static final String USER_NAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String AUTHORIZATION_URL = "/oauth/token";
    public static final String POSTS_URL = "/users/posts";
    public static final String FRIENDS_URL = "/friends";
    public static final String PROFILE_URL = "/users/profile";
    public static final String FILE_URL = "/files";
    public static final String REGISTRATION_URL = "/users";
    public static final String PERSONS_FIND_URL = "/users/find";
    public static final String GROUPS_URL = "/groups";
    public static final String FAVORITE_GROUP_URL = "/groups/{id}";
    public static final String GROUP_URL = "/groups/{groupId}";
    public static final String GROUPS_FIND_URL = "/groups/find";
    public static final String NOTIFICATION_URL = "/notifications";
    public static final String GROUP_POSTS_URL = "/groups/posts";
    public static final String DIALOGS_URL = "/dialogs";
    public static final String DIALOGS_FIND_URL = "/dialogs/find";
    public static final String USERS_LIKE_POST_URL = "/users/posts/{idPost}/likes";
    public static final String USERS_PROFILE_INFO_URL = "/users/{idUser}";
    public static final String PASSWORD_RESTORE_URL = "/password/forgotten";
    public static final String CHAT_MESSAGES_URL = "/dialogs/{dialogId}/messages";

    public static final String USER_ID = "userId";
    public static final String OFFSET = "offset";
    public static final String LIMIT = "limit";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String POST_ID = "postId";
    public static final String FILE_NAME = "name";
    public static final String FULL_NAME = "fullName";
    public static final String GROUP_NAME = "name";
    public static final String GROUP_ID = "groupId";
    public static final String EMAIL = "email";
    public static final String ID = "id";
    public static final String PATH_POST_ID = "idPost";
    public static final String PATH_USER_ID = "idUser";
    public static final String PATH_MESSAGE_ID = "dialogId";


    public static final String HEADER_AUTORIZATION = "Authorization";
    public static final String HEADER_AUTORIZATION_VALUE = "Basic cGFzc3dvcmRDbGllbnQ6MG00NWJ4cDRyMg==";

    public static final String HEADER_CONTENT_TYPE_APP_JSON = "Content-Type: application/json";

    private QueryProperties() {
    }
}

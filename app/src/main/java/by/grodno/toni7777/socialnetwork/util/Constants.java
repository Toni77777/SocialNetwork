package by.grodno.toni7777.socialnetwork.util;

public final class Constants {

    public static final String SHARE_PROFILE = "share_profile";
    public static final String SHARE_DATE_PICKER = "share_date";
    public static final String SHARE_GROUP_ID = "share_group_id";
    public static final String SHARE_FRIEND_ID = "share_friend_id";
    public static final String SHARE_CHAT_ID = "share_chat_id";

    public static final String PLACEHOLDER = "/";

    public static final String DATE_DEFAULT = "dd/MM/yyyy";

    public static final int START_LOAD = 0;

    public static final int SMALL_LIMIT = 4;
    public static final int MEDIUM_LIMIT = 10;

    public static final int ACTION_BUTTON_START = 0;
    public static final int ACTION_BUTTON_PROGRESS = 25;
    public static final int ACTION_BUTTON_FINISH = 100;

    public static final String LOGIN_PREFERENCES = "saveAccessToken";
    public static final String SETTINGS_PREFERENCES = "settingsPreferences";

    public static final String TOKEN_PREFERENCES = "accessToken";
    public static final String USER_ID_PREFERENCES = "userId";
    public static final String USER_AVATAR_PREFERENCES = "userAvatar";
    public static final String USER_FULL_NAME_PREFERENCES = "userFullName";

    public static final String NOTIFICATION_PREFERENCES = "notificationPreferences";
    public static final String NOTIFICATION_SOUND_PREFERENCES = "soundNotificationPreferences";
    public static final String NOTIFICATION_VIBRATE_PREFERENCES = "vibrateNotificationPreferences";

    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json; charset=utf-8";

    public static final int TIMEOUT = 40;

    public static final String EMPTY_STRING = " ";
    public static final long EMPTY_USER_ID = -1;

    public static final int NOTIFICATION_TIME_LIGHT_ON = 2000;
    public static final int NOTIFICATION_TIME_LIGHT_OFF = 1500;

    public static final long[] NOTIFICATION_VIBRATE = new long[]{1000, 1000, 1000, 1000, 1000};

    public static final int REQUEST_PERMISSION = 10;

    private Constants() {
    }
}

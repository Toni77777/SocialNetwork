package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;

/**
 * This return error message. Error message show to ui for users
 */

public final class ErrorHanding {

    /**
     * @param throwable need to get error message
     * @param context   need to get strings from resource
     * @return
     */
    public static String getErrorMessage(Throwable throwable, Context context) {
        String errorMessage = null;
        if (throwable instanceof java.net.UnknownHostException) {
            errorMessage = "No internet connection";
        } else {
            errorMessage = throwable.getMessage();
        }
        return errorMessage;
    }

    private ErrorHanding() {
    }
}

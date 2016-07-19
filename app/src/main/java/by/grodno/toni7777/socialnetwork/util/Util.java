package by.grodno.toni7777.socialnetwork.util;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;

import by.grodno.toni7777.socialnetwork.R;

import static by.grodno.toni7777.socialnetwork.util.Validation.EMPTY_FIELD;
import static by.grodno.toni7777.socialnetwork.util.Validation.MAX_LENGTH_FIELD;


public final class Util {

    public static boolean hasKeySparseIntArray(SparseIntArray sparseIntArray, int key) {
        return sparseIntArray.indexOfKey(key) >= 0;
    }

    public static void showErrorMessage(TextInputLayout layout, int errorType) {
        String errorMessage = getErrorText(layout.getContext(), errorType);
        layout.setError(errorMessage);
    }

    private static String getErrorText(Context context, int error) {
        switch (error) {
            case EMPTY_FIELD:
                return context.getString(R.string.error_field_empty);
            case MAX_LENGTH_FIELD:
                return context.getString(R.string.error_field_max);
            default:
                throw new IllegalArgumentException("Unknown error type " + error);
        }
    }

}

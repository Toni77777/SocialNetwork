package by.grodno.toni7777.socialnetwork.util;

import android.text.TextUtils;
import android.util.SparseIntArray;

import static by.grodno.toni7777.socialnetwork.util.Constants.DATE_DEFAULT;

public final class Validation {

    public static SparseIntArray validateInformation(String name, String surname, String dateBirth) {
        SparseIntArray errors = new SparseIntArray();
        validate(name, ERROR_NAME, errors);
        validate(surname, ERROR_SURNAME, errors);
        validateDate(dateBirth, ERROR_DATE_BIRTH, errors);
        return errors;
    }

    private static void validate(String field, int keyError, SparseIntArray errors) {
        if (TextUtils.isEmpty(field.trim())) {
            errors.append(keyError, EMPTY_FIELD);
        }
    }

    private static void validateDate(String dateBirth, int keyError, SparseIntArray errors) {
        if (dateBirth.equals(DATE_DEFAULT)) {
            errors.append(keyError, EMPTY_FIELD);
        }
    }

    public static final int ERROR_NAME = 101;
    public static final int ERROR_SURNAME = 102;
    public static final int ERROR_DATE_BIRTH = 103;

    public static final int EMPTY_FIELD = 1;
    public static final int MAX_LENGTH_FIELD = 2;

}
package by.grodno.toni7777.socialnetwork.util;

import java.util.Calendar;

import android.text.TextUtils;
import android.util.SparseIntArray;

import static by.grodno.toni7777.socialnetwork.util.Constants.DATE_DEFAULT;
import static by.grodno.toni7777.socialnetwork.util.Constants.PLACEHOLDER;

public final class Validation {

    public static SparseIntArray validateInformation(String name, String surname, String dateBirth) {
        SparseIntArray errors = new SparseIntArray();
        validate(name, ERROR_NAME, errors);
        validate(surname, ERROR_SURNAME, errors);
        validateDate(dateBirth, ERROR_DATE_BIRTH, errors);
        return errors;
    }

    public static SparseIntArray validateLogin(String login, String password, String confirnPass, String email) {
        SparseIntArray errors = new SparseIntArray();
        validate(login, ERROR_LOGIN, errors);
        validate(password, ERROR_PASSWORD, errors);
        validate(email, ERROR_EMAIL, errors);
        return errors;
    }

    public static SparseIntArray validateContact(String phone, String skype, String city) {
        SparseIntArray errors = new SparseIntArray();
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
        } else if (isFutureDate(dateBirth)) {
            errors.append(keyError, MAX_LENGTH_FIELD);
        }
    }

    private static boolean isFutureDate(String dateBirth) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String[] date = dateBirth.split(PLACEHOLDER);
        int inputMonth = Integer.parseInt(date[1]);
        int inputYear = Integer.parseInt(date[2]);
        return inputYear >= year && !((inputYear == year) & (inputMonth < month));
    }

    private static void validateMail(String email, int keyError, SparseIntArray errors) {
        if (TextUtils.isEmpty(email.trim())) {
            errors.append(keyError, EMPTY_FIELD);
        } else if (!email.contains("@")) {
            errors.append(keyError, WRONG_FORMAT_FIELD);
        }
    }

    public static final int ERROR_NAME = 101;
    public static final int ERROR_SURNAME = 102;
    public static final int ERROR_DATE_BIRTH = 103;

    public static final int ERROR_LOGIN = 201;
    public static final int ERROR_PASSWORD = 202;
    public static final int ERROR_CONFIRM_PASS = 203;
    public static final int ERROR_EMAIL = 204;

    public static final int ERROR_PHONE = 301;
    public static final int ERROR_SKYPE = 302;
    public static final int ERROR_CITY = 303;

    public static final int EMPTY_FIELD = 1;
    public static final int MAX_LENGTH_FIELD = 2;
    public static final int WRONG_FORMAT_FIELD = 3;

}
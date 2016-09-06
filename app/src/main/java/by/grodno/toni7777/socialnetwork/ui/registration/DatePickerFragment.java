package by.grodno.toni7777.socialnetwork.ui.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import static by.grodno.toni7777.socialnetwork.util.Constants.SHARE_DATE_PICKER;
import static by.grodno.toni7777.socialnetwork.util.Constants.PLACEHOLDER;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        return new DatePickerDialog(getActivity(),
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Month range 0 to 11
     * http://docs.oracle.com/javase/6/docs/api/java/util/Calendar.html#MONTH
     * Need month added +1
     */
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Intent intent = new Intent()
                .putExtra(SHARE_DATE_PICKER, String.valueOf(day) + PLACEHOLDER + month + PLACEHOLDER + year);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}

package by.grodno.toni7777.socialnetwork.ui.registration.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.BuildConfig;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.TabFragment;
import by.grodno.toni7777.socialnetwork.ui.registration.DatePickerFragment;

import static by.grodno.toni7777.socialnetwork.util.Constants.SHARE_DATE_PICKER;
import static by.grodno.toni7777.socialnetwork.util.Util.hasKeySparseIntArray;
import static by.grodno.toni7777.socialnetwork.util.Util.inNotEmptySparseIntArray;
import static by.grodno.toni7777.socialnetwork.util.Util.showErrorMessage;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_NAME;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_SURNAME;
import static by.grodno.toni7777.socialnetwork.util.Validation.ERROR_DATE_BIRTH;
import static by.grodno.toni7777.socialnetwork.util.Validation.validateInformation;

public class InfoFragment extends TabFragment {

    @BindView(R.id.name_layout)
    TextInputLayout mNameView;

    @BindView(R.id.surname_layout)
    TextInputLayout mSurnameView;

    @BindView(R.id.date_birth_layout)
    TextInputLayout mDateBirthView;

    @BindView(R.id.sex)
    Spinner mSexView;

    private OnInfoPass mInfoPasser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSexView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, new String[]{"male", "female"}));
        resetErrorAfterChange(mNameView, mSurnameView);
        if (BuildConfig.DEBUG) {
            mNameView.getEditText().setText("Anton");
            mSurnameView.getEditText().setText("Palaikou");
            mDateBirthView.getEditText().setText("15/4/1995");
        }
    }

    @Override
    @OnClick(R.id.next)
    public void nextTab() {
        String name = mNameView.getEditText().getText().toString();
        String surname = mSurnameView.getEditText().getText().toString();
        String sex = mSexView.getSelectedItem().toString();
        String dateBirth = mDateBirthView.getEditText().getText().toString();
        Log.e("Date", dateBirth.toString());
        SparseIntArray errors = validateInformation(name, surname, dateBirth);
        if (inNotEmptySparseIntArray(errors)) {
            showErrors(errors);
        } else {
            mInfoPasser.onInfoPass(name, surname, sex, dateBirth);
        }
    }

    @Override
    public void showErrors(SparseIntArray errors) {
        if (hasKeySparseIntArray(errors, ERROR_NAME)) {
            int errorType = errors.get(ERROR_NAME);
            showErrorMessage(mNameView, errorType);
        }
        if (hasKeySparseIntArray(errors, ERROR_SURNAME)) {
            int errorType = errors.get(ERROR_SURNAME);
            showErrorMessage(mSurnameView, errorType);
        }
        if (hasKeySparseIntArray(errors, ERROR_DATE_BIRTH)) {
            int errorType = errors.get(ERROR_DATE_BIRTH);
            showErrorMessage(mDateBirthView, errorType);
        }
    }

    @OnClick(R.id.date_birth)
    public void showDatePicker() {
        DialogFragment fragment = new DatePickerFragment();
        fragment.setTargetFragment(this, REQUEST_DATE_BIRTH);
        fragment.show(getFragmentManager(), PICKER);
        mDateBirthView.setError(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_DATE_BIRTH:
                    mDateBirthView.getEditText().setText(data.getStringExtra(SHARE_DATE_PICKER));
                    break;
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInfoPass) {
            mInfoPasser = (OnInfoPass) context;
        } else {
            throw new RuntimeException("Host activity must implements OnInfoPass interface.");
        }
    }

    public interface OnInfoPass {
        void onInfoPass(String name, String surname, String sex, String dateBirth);
    }

    private static final int REQUEST_DATE_BIRTH = 1504;
    private static final String PICKER = "date picker";
}

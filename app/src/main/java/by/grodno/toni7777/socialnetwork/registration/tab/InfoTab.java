package by.grodno.toni7777.socialnetwork.registration.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.registration.DatePickerFragment;
import by.grodno.toni7777.socialnetwork.registration.ErrorTextWatcher;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;
import static by.grodno.toni7777.socialnetwork.util.Util.*;
import static by.grodno.toni7777.socialnetwork.util.Validation.*;

public class InfoTab extends Tab {

    @BindView(R.id.name_layout)
    TextInputLayout mNameLayout;

    @BindView(R.id.surname_layout)
    TextInputLayout mSurnameLayout;

    @BindView(R.id.date_birth_layout)
    TextInputLayout mDateBirthLayout;

    @BindView(R.id.date_birth)
    TextView mDateBirth;

    @BindView(R.id.sex)
    Spinner mSex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSex.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, new String[]{"male", "female"}));

        mNameLayout.getEditText().addTextChangedListener(new ErrorTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mNameLayout.setError(null);
            }
        });

        mSurnameLayout.getEditText().addTextChangedListener(new ErrorTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                mSurnameLayout.setError(null);
            }
        });
    }

    @Override
    public boolean nextIf() {
        String name = mNameLayout.getEditText().getText().toString();
        String surname = mSurnameLayout.getEditText().getText().toString();
        String sex = mSex.getSelectedItem().toString();
        String dateBirth = mDateBirth.getText().toString();
        SparseIntArray errors = validateInformation(name, surname, dateBirth);
        if (errors.size() > 0) {
            showErrors(errors);
            return false;
        } else {
            getStepDataFor(TABS_SHARE_TO_LOGIN).putString(TABS_SHARE_NAME, name);
            getStepDataFor(TABS_SHARE_TO_LOGIN).putString(TABS_SHARE_SURNAME, surname);
            getStepDataFor(TABS_SHARE_TO_LOGIN).putString(TABS_SHARE_SEX, sex);
            getStepDataFor(TABS_SHARE_TO_LOGIN).putString(TABS_SHARE_DATE_BIRTH, dateBirth);
            return true;
        }
    }

    @Override
    public void showErrors(SparseIntArray errors) {
        mDateBirthLayout.setError("Error");
        if (hasKeySparseIntArray(errors, ERROR_NAME)) {
            int errorType = errors.get(ERROR_NAME);
            showErrorMessage(mNameLayout, errorType);
        }
        if (hasKeySparseIntArray(errors, ERROR_SURNAME)) {
            int errorType = errors.get(ERROR_SURNAME);
            showErrorMessage(mSurnameLayout, errorType);
        }

    }

    @OnClick(R.id.choose_date_birth)
    public void showDatePicker() {
        DialogFragment fragment = new DatePickerFragment();
        fragment.setTargetFragment(this, REQUEST_DATE_BIRTH);
        fragment.show(getFragmentManager(), PICKER);
        mDateBirthLayout.setError(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_DATE_BIRTH:
                    mDateBirth.setText(data.getStringExtra(SHARE_DATE_PICKER));
                    break;
            }
        }
    }

    private static final int REQUEST_DATE_BIRTH = 1504;
    private static final String PICKER = "date picker";
}

package com.passion.registerform.fragments;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.passion.registerform.utils.BundleKeys;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import com.passion.registerform.R;
import com.passion.registerform.dialogs.MyDatePickerDialog;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegistrationFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static int SIMPLE_FRAGMENT_REQUEST_CODE = 123;
    private long dateInMilliSeconds = 0;
    private EditText etDateOfBirth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        etDateOfBirth = (EditText) view.findViewById(R.id.et_date_of_birth);

        if (Build.VERSION.SDK_INT >= 11) {
            etDateOfBirth.setRawInputType(InputType.TYPE_CLASS_TEXT);
            etDateOfBirth.setTextIsSelectable(true);
        } else {
            etDateOfBirth.setRawInputType(InputType.TYPE_NULL);
            etDateOfBirth.setFocusable(true);
        }

        etDateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //showThirdPartyDatePicker();
                    showDatePicker();
                }
            }
        });

        etDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showThirdPartyDatePicker();
                showDatePicker();
            }
        });

        return view;
    }

    private void showDatePicker(){
        DialogFragment newFragment = new MyDatePickerDialog();

        Bundle extras = new Bundle();

        // Pass an initial or the last value for the date picker
        dateInMilliSeconds = (dateInMilliSeconds <= 0) ? Calendar.getInstance().getTimeInMillis() : dateInMilliSeconds;

        extras.putLong(BundleKeys.LAST_DEFINED_DATE, dateInMilliSeconds);
        newFragment.setArguments(extras);
        newFragment.setTargetFragment(this, SIMPLE_FRAGMENT_REQUEST_CODE);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void showThirdPartyDatePicker(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog thirdPartyDatePicker = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        thirdPartyDatePicker.show(getActivity().getFragmentManager(), "ThirdPartyDatePicker");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getContext(), "Year : " + year + " Month : " + monthOfYear + " Day : " + dayOfMonth, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SIMPLE_FRAGMENT_REQUEST_CODE && resultCode == MyDatePickerDialog.DATE_PICKED_RESULT_CODE) {
            String datePicked = data.getStringExtra(MyDatePickerDialog.DATE_PICKED_INTENT_KEY);
            this.etDateOfBirth.setText(datePicked);
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

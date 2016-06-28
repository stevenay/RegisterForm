package com.passion.registerform.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import com.passion.registerform.utils.BundleKeys;

import java.util.Calendar;

public class MyDatePickerDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String DATE_PICKED_INTENT_KEY = "DATE_PICKED_INTENT_KEY";
    public static final int DATE_PICKED_RESULT_CODE = 123;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Long dateInMilliSeconds = getArguments().getLong(BundleKeys.LAST_DEFINED_DATE);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateInMilliSeconds);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(view.getContext(), "Year : " + year + " Month : " + monthOfYear + " Day : " + dayOfMonth, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(DATE_PICKED_INTENT_KEY, dayOfMonth + "/" + monthOfYear + "/" + year);
        getTargetFragment().onActivityResult(getTargetRequestCode(), DATE_PICKED_RESULT_CODE, intent);
    }


}
package mf.andorid.com.mfinfo.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mf.andorid.com.mfinfo.R;

/**
 * Created by 8398 on 23/12/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    String code;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        code=getArguments().get("mCode").toString();
        System.out.println("date="+code);

// Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(c.getTime());
        EditText editText=(EditText) getActivity().findViewById(R.id.editText_Date);
        editText.setText(formattedDate);
        System.out.println("Date = " + formattedDate);
        EditText editText1=(EditText)getActivity().findViewById(R.id.editText_nav);

        String nav=addToPortfolioFragment.downloadUrl(Integer.parseInt(code), "02-Nov-2016");
        editText1.setText(nav);

    }
}

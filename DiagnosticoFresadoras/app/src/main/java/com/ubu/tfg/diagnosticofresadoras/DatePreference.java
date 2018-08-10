package com.ubu.tfg.diagnosticofresadoras;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePreference extends DialogPreference {

    DatePicker datePicker;
    DatePickerDialog.OnDateSetListener dateSetListener;

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        datePicker = new DatePicker(context, attrs);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {

        super.onCreateView(parent);
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.preference_date, parent, false);
    }

    @Override
    protected void onBindView(@NonNull View view) {

        super.onBindView(view);
        TextView clickDate = view.findViewById(R.id.tvDate);
        clickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario = Calendar.getInstance();
                int year = calendario.get(Calendar.YEAR);
                int month = calendario.get(Calendar.MONTH);
                int day = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                datePicker = dialog.getDatePicker();
            }

        });
        /*SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        int day=datePicker.getDayOfMonth();
        int month=datePicker.getMonth();
        int year=datePicker.getYear();
        Date d = new Date(year, month, day);
        String date = dateFormatter.format(d);
        Log.i("Fecha string",date);
        persistString(date);*/
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String dato = day + "/" + month + "/" + year;
                //Date d = new Date(year-1900, month, day);
                //Log.i("Fecha",String.valueOf(d.getTime()));
                //persistLong(d.getTime());
                SharedPreferences prefs = getSharedPreferences();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("Día", day);
                editor.putInt("Mes", month);
                editor.putInt("Año", year);
                editor.apply();
            }
        };
    }
}

package com.ubu.tfg.diagnosticofresadoras;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Clase que implementa el elemento interactivo que sirve para seleccionar la fecha en la sección de
 * preferencias.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class DatePreference extends DialogPreference {
    /**
     * Widget o elemento interactivo para seleccionar una fecha
     */
    private DatePicker datePicker;
    /**
     * Oyente que se utiliza cuando el usuario ha terminado de seleccionar una fecha
     */
    private DatePickerDialog.OnDateSetListener dateSetListener;

    /**
     * Constructor que inicializa el datePicker y le pasa el contexto y una colección de atributos.
     *
     * @param context Interfaz con información global sobre el entorno de la aplicación
     * @param attrs   Una colección de atributos
     */
    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        datePicker = new DatePicker(context, attrs);
    }

    /**
     * Diseña la interfaz de usuario del elemento interactivo por primera vez.
     *
     * @param parent Vista principal a la que se debe asociar la IU del elemento interactivo
     * @return Vista para la interfaz de usuario del elemento interactivo
     */
    @Override
    protected View onCreateView(ViewGroup parent) {

        super.onCreateView(parent);
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null)
            return inflater.inflate(R.layout.preference_date, parent, false);
        else
            return null;
    }

    /**
     * Vincula la vista creada a esta preferencia y añade los datos de la fecha a las preferencias
     * compartidas.
     *
     * @param view La vista que muestra esta preferencia
     */
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
                if (dialog.getWindow() != null)
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                datePicker = dialog.getDatePicker();
            }

        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
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

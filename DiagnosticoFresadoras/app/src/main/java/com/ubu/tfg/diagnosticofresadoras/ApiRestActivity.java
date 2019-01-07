package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * Activity de la pantalla que muestra las últimas alarmas que se han activado en un cierto
 * período de tiempo.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class ApiRestActivity extends AppCompatActivity {
    /**
     * Preferencias compartidas de la aplicación
     */
    SharedPreferences preferences;
    /**
     * Fecha límite hasta la cual se muestran las alarmas activadas
     */
    Date date;

    /**
     * Obtiene la fecha de las preferencias y la inicializa. Obtiene las alarmas y las muestra en la
     * pantalla.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_rest);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int day = preferences.getInt("Día", 0);
        int month = preferences.getInt("Mes", 0);
        int year = preferences.getInt("Año", 0);
        date = new Date(year, month, day);
        ArrayList<String> alarms = getAlarms();
        fillData(alarms);
    }

    /**
     * Obtiene, a partir de un fichero de simulación, las alarmas que se han activado entre la fecha
     * actual y la fecha límite.
     *
     * @return Lista de alarmas con su número y título
     */
    private ArrayList<String> getAlarms() {
        ArrayList<String> alarms = new ArrayList<>();
        String[] alarmsName = getResources().getStringArray(R.array.alarms_no_number);
        try {
            String json = null;
            InputStream is = this.getAssets().open("Simulación.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int rd = is.read(buffer);
            is.close();
            if (rd != -1)
                json = new String(buffer, "UTF-8");
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Iterator<String> keys = jsonObject.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (containsArray(alarmsName, key)) {
                            String value = jsonObject.getJSONObject(key).getString("value");
                            if (compareDates(date, value)) {
                                alarms.add(getNumber(value) + ": " + key);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alarms;
    }

    /**
     * Devuelve true si el elemento se encuentra en el array y false si no.
     *
     * @param array Array de String donde buscar
     * @param elem  Elemento a encontrar
     * @return True si el elemento se encuentra en el array y false si no
     */
    private boolean containsArray(String[] array, String elem) {
        for (String anArray : array) {
            if (anArray.compareTo(elem) == 0)
                return true;
        }
        return false;
    }

    /**
     * Devuelve true si la fecha límite es anterior a la fecha obtenida de la cadena de valores.
     * False si no.
     *
     * @param date   Fecha límite
     * @param values Cadena con los valores de un elemento del fichero de simulación
     * @return True si la fecha límite es anterior a la fecha obtenida y false si no
     */
    private boolean compareDates(Date date, String values) {
        int n = values.indexOf("timestamp");
        int year = Integer.parseInt(values.substring(n + 11, n + 15));
        int month = Integer.parseInt(values.substring(n + 16, n + 18));
        int day = Integer.parseInt(values.substring(n + 19, n + 21));
        Date date2 = new Date(year, month, day);
        return date.before(date2);
    }

    /**
     * Obtiene el número de la alarma a partir de la cadena de valores.
     *
     * @param values Cadena con los valores de un elemento del fichero de simulación
     * @return Número de la alarma
     */
    private String getNumber(String values) {
        int n = values.indexOf("nr");
        return values.substring(n + 9, n + 12);
    }

    /**
     * Rellena la pantalla con el número y título de las alarmas separadas por líneas.
     *
     * @param alarms Lista de las alarmas a mostrar en la pantalla
     */
    private void fillData(ArrayList<String> alarms) {
        LinearLayout llAlarms = findViewById(R.id.llAlarms);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams vParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, 3);

        for (final String alarm : alarms) {
            TextView tvAlarm = new TextView(this);
            tvAlarm.setLayoutParams(llParams);
            tvAlarm.setText(alarm);
            tvAlarm.setTextSize(25);
            View vLine = new View(this);
            vLine.setLayoutParams(vParams);
            vLine.setBackgroundColor(Color.BLACK);
            vLine.setTop(5);
            vLine.setBottom(5);
            llAlarms.addView(tvAlarm);
            llAlarms.addView(vLine);
            tvAlarm.setOnClickListener(new ButtonsOnClickListener(alarm.substring(0, 3)));
        }
    }

    /**
     * Clase que implementa el código que se ejecuta al pulsar en un TextView de una alarma.
     */
    class ButtonsOnClickListener implements View.OnClickListener {
        /**
         * Número de la alarma
         */
        String num;

        /**
         * Constructor que asigna el número de la alarma.
         *
         * @param num Número de la alarma
         */
        ButtonsOnClickListener(String num) {
            this.num = num;
        }

        /**
         * Parsea el json correspondiente y obtiene la alarma, si no está ya almacenada. Finalmente
         * nos lleva a la pantalla de la información de la alarma. Código que se ejecuta al pulsar.
         *
         * @param v Elemento View
         */
        @Override
        public void onClick(View v) {
            String lang, cod; //json = null;
            lang = getLanguage();
            cod = num + lang;

            Intent i = new Intent(ApiRestActivity.this, InfoAlarmaActivity.class);
            i.putExtra("codAlarm", cod);
            startActivity(i);
        }

        /**
         * Obtiene el idioma de las preferencias.
         *
         * @return Idioma elegido en las preferencias
         */
        private String getLanguage() {
            String language;
            language = preferences.getString("idioma", "");
            if (language.compareTo("Español") == 0)
                language = "es";
            else if (language.compareTo("English") == 0)
                language = "en";
            else if (language.isEmpty())
                language = Locale.getDefault().getLanguage();
            return language;
        }
    }
}

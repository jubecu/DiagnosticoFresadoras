package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Alarm;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class ApiRestActivity extends AppCompatActivity {

    SharedPreferences preferences;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_rest);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int day = preferences.getInt("Día", 0);
        int month = preferences.getInt("Mes", 0);
        int year = preferences.getInt("Año", 0);
        Log.i("Día", String.valueOf(day));
        Log.i("Mes", String.valueOf(month));
        Log.i("Año", String.valueOf(year));
        date = new Date(year, month, day);
        ArrayList<String> alarms = getAlarms();
        fillData(alarms);
    }

    private ArrayList<String> getAlarms() {
        ArrayList<String> alarms = new ArrayList<String>();
        String[] alarmsName = getResources().getStringArray(R.array.alarms_no_number);
        try {
            InputStream is = this.getAssets().open("Simulación.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
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

    private boolean containsArray(String[] array, String elem) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(elem) == 0)
                return true;
        }
        return false;
    }

    private boolean compareDates(Date date, String values) {
        int n = values.indexOf("timestamp");
        int year = Integer.parseInt(values.substring(n + 11, n + 15));
        int month = Integer.parseInt(values.substring(n + 16, n + 18));
        int day = Integer.parseInt(values.substring(n + 19, n + 21));
        Date date2 = new Date(year, month, day);
        return date.before(date2);
    }

    private String getNumber(String values) {
        int n = values.indexOf("nr");
        return values.substring(n + 9, n + 12);
    }

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

    class ButtonsOnClickListener implements View.OnClickListener {

        String num;

        public ButtonsOnClickListener(String num) {
            this.num = num;
        }

        @Override
        public void onClick(View v) {
            String lang, cod, json = null;
            lang = getLanguage();
            ManagementJSON managementJSON = new ManagementJSON(num, lang);
            cod = num + lang;
            if (!AlarmTable.getInstance().containsAlarm(cod)) {
                try {
                    json = managementJSON.createJsonFromAssets(ApiRestActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Alarm alarm = managementJSON.getAlarm(json);
                AlarmTable.getInstance().addAlarm(alarm, lang);
            }
            Intent i = new Intent(ApiRestActivity.this, InfoAlarmaActivity.class);
            i.putExtra("codAlarm", cod);
            startActivity(i);
        }

        private String getLanguage() {
            String language = preferences.getString("idioma", "");
            return language;
        }
    }
}

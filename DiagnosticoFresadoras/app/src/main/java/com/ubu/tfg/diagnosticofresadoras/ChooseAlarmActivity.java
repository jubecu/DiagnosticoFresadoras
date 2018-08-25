package com.ubu.tfg.diagnosticofresadoras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Alarm;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Answer;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class ChooseAlarmActivity extends AppCompatActivity {

    Button bInicio;
    Spinner alarmas;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_alarm);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        bInicio = findViewById(R.id.bInicio);
        alarmas = findViewById(R.id.spAlarmas);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        createRegistryFile();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.alarmas, android.R.layout.simple_list_item_1);
        alarmas.setAdapter(adapter);

        bInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alarm alarm;
                String num, json = null, lang, cod;
                num = alarmas.getSelectedItem().toString().substring(0, 3);
                lang = getLanguage();
                ManagementJSON managementJSON = new ManagementJSON(num, lang);
                cod = num + lang;
                if (!AlarmTable.getInstance().containsAlarm(cod)) {
                    try {
                        json = managementJSON.createJsonFromAssets(ChooseAlarmActivity.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    alarm = managementJSON.getAlarm(json);
                    AlarmTable.getInstance().addAlarm(alarm, lang);
                }
                Intent i = new Intent(ChooseAlarmActivity.this, InfoAlarmaActivity.class);
                i.putExtra("codAlarm", cod);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferencesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void createRegistryFile() {
        if (!existsFile("registry.csv")) {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(
                        "registry.csv", Context.MODE_APPEND));
                osw.write("Alarma,Pregunta,Respuesta,Timestamp\n");
                osw.close();
            } catch (IOException ex) {
                Log.v("MainActivity", "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private boolean existsFile(String name) {
        for (String file : fileList()) {
            if (file.equals(name))
                return true;
        }
        return false;
    }

    private String getLanguage() {
        String language = preferences.getString("idioma", "");
        return language;
    }
}

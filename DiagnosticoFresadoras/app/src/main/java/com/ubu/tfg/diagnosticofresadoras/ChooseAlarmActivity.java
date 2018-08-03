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
                this, R.array.alarmas, android.R.layout.simple_spinner_dropdown_item);
        alarmas.setAdapter(adapter);

        bInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alarm alarm;
                String cod, json = null, lang;
                cod = alarmas.getSelectedItem().toString().substring(0, 3);
                lang = getLanguage();
                try {
                    json = createJsonFromAssets(cod, lang);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                alarm = getAlarm(json, cod);
                AlarmTable.getInstance().addAlarm(alarm);
                Intent i = new Intent(ChooseAlarmActivity.this, InfoAlarmaActivity.class);
                i.putExtra("numAlarm", cod);
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

    private String createJsonFromAssets(String cod, String language) throws IOException {
        String json;
        InputStream is;
        try {
            if (language.isEmpty())
                language = Locale.getDefault().getLanguage();
            if (language.equals("es"))
                is = this.getAssets().open("Alarma " + cod + ".json");
            else
                is = this.getAssets().open("Alarm " + cod + ".json");
        } catch (FileNotFoundException ex) {
            is = this.getAssets().open("Alarma " + cod + ".json");
        }
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "ISO-8859-1");
        Log.v("MainActivity", "Load json ok");
        return json;
    }

    private Alarm getAlarm(String json, String cod) {
        Alarm alarm = null;
        int num = Integer.parseInt(cod);
        try {
            JSONObject jsonObject = new JSONObject(json);

            String title = jsonObject.getString("Título");
            String desp = jsonObject.getString("Descripción");
            alarm = new Alarm(num, title, desp);

            JSONArray questions = jsonObject.getJSONArray("Preguntas");
            for (int i = 0; i < questions.length(); i++) {
                JSONObject ques = questions.getJSONObject(i);

                double idQues = ques.getDouble("Id");
                String textQues = ques.getString("Texto");

                Question question = new Question(idQues, textQues);


                JSONArray images = ques.getJSONArray("Imágenes");
                for (int k = 0; k < images.length(); k++) {
                    String image = images.getString(k);
                    question.addImage(image);
                }

                JSONArray answers = ques.getJSONArray("Respuestas");
                for (int j = 0; j < answers.length(); j++) {
                    JSONObject ans = answers.getJSONObject(j);

                    String idAns = ans.getString("Id");
                    String textAns = ans.getString("Texto");
                    double next = ans.getDouble("Camino");

                    Answer answer = new Answer(idAns, textAns, next);

                    String message = ans.getString("Mensaje");
                    answer.setMessage(message);

                    question.addAnswer(answer);
                }
                alarm.addQuestion(question);
            }
            JSONArray images = jsonObject.getJSONArray("Imágenes");
            for (int i = 0; i < images.length(); i++) {
                String image = images.getString(i);
                alarm.addImage(image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return alarm;
    }
}

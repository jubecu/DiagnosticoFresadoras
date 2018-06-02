package com.example.pc.diagnosticofresadoras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pc.diagnosticofresadoras.modeloAlarmas.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    Button bInicio;
    String alarmNum[] = {"631: ORDEN DE GIRO MANDRINO SIN HTA.",
            "712: ASPIRACIÓN ACEITE DEL CABEZAL INSUFICIENTE",
            "713: AUTOMÁTICO BOMBA ASPIRACIÓN SALTADO",
            "821: FALLO OPERACIÓN 21",
            "822: FALLO OPERACIÓN 22",
            "846: PRESIÓN AIRE PARA ENGRASE AIRE/ACEITE INSUFICIENTE"};
    Spinner alarmas;
    FileOutputStream fileEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bInicio = findViewById(R.id.bInicio);
        alarmas = findViewById(R.id.spAlarmas);

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item_alarmas, alarmNum);
        alarmas.setAdapter(adapter);

        bInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alarm alarm;
                String cod = null, json;
                cod = alarmas.getSelectedItem().toString().substring(0, 3);
                json = loadJSONFromAsset(cod);
                alarm = getAlarm(json, cod);
                AlarmTable.getInstance().addAlarm(alarm);
                Intent i = new Intent(MainActivity.this, InfoAlarmaActivity.class);
                i.putExtra("numAlarm", cod);
                startActivity(i);
            }
        });


    }

    private File getFile(String filename) {
        for (File file : getFilesDir().listFiles()) {
            if (file.getName().equals(filename))
                return file;
        }
        return null;
    }


    private boolean existsFile(String name) {
        for (String file : fileList()) {
            if (file.equals(name))
                return true;
        }
        return false;
    }

    public String loadJSONFromAsset(String cod) {
        String json = null;
        try {
            InputStream is = this.getAssets().open("Alarma " + cod + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "ISO-8859-1");
            Log.v("MainActivity", "Load json ok");
        } catch (IOException ex) {
            Log.v("MainActivity", "Error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
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

                String image = ques.getString("Imagen");
                question.setImage(image);

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
            for (int i = 0; i < questions.length(); i++) {
                String image = images.getString(i);
                alarm.addImage(image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return alarm;
    }
}

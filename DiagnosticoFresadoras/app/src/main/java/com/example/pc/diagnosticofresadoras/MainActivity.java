package com.example.pc.diagnosticofresadoras;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
//import java.lang.Thread;


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

        getDataApiRest();

        bInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alarm alarm;
                String cod = null, json = null;
                cod = alarmas.getSelectedItem().toString().substring(0, 3);
                try {
                    json = loadJSONFromAsset(cod);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public String loadJSONFromAsset(String cod) throws IOException {
        String json = null;
        InputStream is = null;
        try {
            String idioma = Locale.getDefault().getLanguage();
            if (idioma.equals("es"))
                is = this.getAssets().open("Alarma " + cod + ".json");
            else
                is = this.getAssets().open("Alarm " + cod + ".json");
        } catch (FileNotFoundException ex) {
            is = this.getAssets().open("Alarma " + cod + ".json");
        /*} catch (IOException ex) {
            Log.v("MainActivity", "Error: " + ex.getMessage());
            ex.printStackTrace();
            return null;*/
        }
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "ISO-8859-1");
        Log.v("MainActivity", "Load json ok");
        return json;
    }

    private void getDataApiRest() {
        new Thread(new Runnable() {
            public void run() {
                URL githubEndpoint = null;
                try {
                    githubEndpoint = new URL("https://api.github.com/");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) githubEndpoint.openConnection();
                    if (myConnection.getResponseCode() == 200) {
                        //Log.i("Api Rest", "hola3");
                        String json = null;
                        InputStream responseBody = myConnection.getInputStream();
                        int size = responseBody.available();
                        byte[] buffer = new byte[size];
                        responseBody.read(buffer);
                        responseBody.close();
                        json = new String(buffer, "UTF-8");
                        Log.i("Api Rest OK", json);
                    } else {
                        Log.i("Error Api Rest", "No response");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

package com.ubu.tfg.diagnosticofresadoras;

import android.content.Context;
import android.util.Log;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Alarm;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Answer;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class ManagementJSON {
    private String num;
    private String language;

    public ManagementJSON(String num, String language) {
        this.num = num;
        this.language = language;
    }

    public String createJsonFromAssets(Context context) throws IOException {
        String json;
        InputStream is;
        try {
            if (language.isEmpty())
                language = Locale.getDefault().getLanguage();
            if (language.equals("es"))
                is = context.getAssets().open("Alarma " + num + ".json");
            else
                is = context.getAssets().open("Alarm " + num + ".json");
        } catch (FileNotFoundException ex) {
            is = context.getAssets().open("Alarma " + num + ".json");
        }
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "ISO-8859-1");
        Log.v("MainActivity", "Load json ok");
        return json;
    }

    public Alarm getAlarm(String json) {
        Alarm alarm = null;
        int num = Integer.parseInt(this.num);
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

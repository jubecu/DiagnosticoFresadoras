package com.ubu.tfg.diagnosticofresadoras;

import android.content.Context;
import android.util.Log;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Alarm;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Answer;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Clase que se encarga de transformsr un fichero JSON en un objeto Alarm.
 *
 * @author Juan Francisco Benito Cuesta
 */
class ManagementJSON {
    /**
     * Número de la alarma
     */
    private String num;
    /**
     * Idioma del fichero JSON
     */
    private String language;

    /**
     * Constructor que inicializa el número y el idioma.
     *
     * @param num      Número de la alarma
     * @param language Idioma del fichero JSON
     */
    ManagementJSON(String num, String language) {
        this.num = num;
        this.language = language;
    }

    /**
     * Pasa el contenido de un fichero JSON a un String.
     *
     * @param context Contexto o Activity a partir del cual se busca el fichero
     * @return String con el contenido del fichero JSON
     * @throws IOException Excepción que regula las operaciones con el fichero
     */
    String createJsonFromAssets(Context context) throws IOException {
        String json = null;
        InputStream is;
        if (language.isEmpty())
            language = Locale.getDefault().getLanguage();
        if (language.equals("es"))
            is = context.getAssets().open("Alarma " + num + ".json");
        else
            is = context.getAssets().open("Alarm " + num + ".json");

        int size = is.available();
        byte[] buffer = new byte[size];
        int rd = is.read(buffer);
        is.close();
        if (rd != -1)
            json = new String(buffer, "UTF-8");
        Log.v("INFO createJSON:", "Load json ok");
        return json;
    }

    /**
     * Crea y devuelve un objeto Alarm a partir del contenido de un String en formato JSON.
     *
     * @param json String en formato JSON con la información de una alarma
     * @return Objeto Alarm
     */
    Alarm getAlarm(String json) {
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

package com.example.pc.diagnosticofresadoras;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.pc.diagnosticofresadoras.modeloAlarmas.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    Button bInicio;
    String alarmNum[]={"631: ORDEN DE GIRO MANDRINO SIN HTA.",
                       "713: AUTOMÁTICO BOMBA ASPIRACIÓN SALTADO",
                       "821: FALLO OPERACIÓN 21",
                       "822: FALLO OPERACIÓN 22",
                       "846: PRESIÓN AIRE PARA ENGRASE AIRE/ACEITE INSUFICIENTE"};
    Spinner alarmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bInicio = (Button) findViewById(R.id.bInicio);
        alarmas=(Spinner) findViewById(R.id.spAlarmas);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_item_alarmas,alarmNum);
        alarmas.setAdapter(adapter);

        bInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alarm alarm;
                String cod = null,json;
                cod=alarmas.getSelectedItem().toString().substring(0,3);
                json=loadJSONFromAsset(cod);
                alarm=getAlarm(json,cod);
                //titulo="AUTOMÁTICO BOMBA ASPIRACIÓN SALTADO";
                Intent i=new Intent(MainActivity.this, InfoAlarmaActivity.class);
                i.putExtra("dato1",cod);
                i.putExtra("dato2",alarm.getTitle());
                i.putExtra("dato3",alarm.getDescription());
                i.putExtra("dato4",alarm);
                i.p
                startActivity(i);
            }
        });


    }

    public String loadJSONFromAsset(String cod) {
        String json = null;
        try {
            InputStream is = this.getAssets().open("Alarma "+cod+".json");
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

    private Alarm getAlarm(String json, String cod){
        Alarm alarm=null;
        int num=Integer.parseInt(cod);
        //String title = null,desp=null;
        try {
            JSONObject jsonObject=new JSONObject(json);

            String title = jsonObject.getString("Título");
            String desp = jsonObject.getString("Descripción");
            alarm=new Alarm(num,title,desp);

            JSONArray questions = jsonObject.getJSONArray("Preguntas");
            for(int i =0;i<questions.length();i++){
                JSONObject ques=questions.getJSONObject(i);

                double idQues = ques.getDouble("Id");
                String textQues = ques.getString("Texto");

                Question question = new Question(idQues, textQues);

                JSONArray answers = ques.getJSONArray("Respuestas");
                for(int j =0;j<answers.length();j++){
                    JSONObject ans=answers.getJSONObject(j);

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
        }catch (JSONException e){
            e.printStackTrace();
        }
        return alarm;
    }
}

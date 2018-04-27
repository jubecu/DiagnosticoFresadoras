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
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    Button bInicio;
    String alarmNum[]={"631","713","821","822","846"};
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
                String cod,titulo;
                cod=alarmas.getSelectedItem().toString();
                //titulo=getTituloAlarma(cod);
                titulo="AUTOMÁTICO BOMBA ASPIRACIÓN SALTADO";
                Intent i=new Intent(MainActivity.this, InfoAlarmaActivity.class);
                i.putExtra("dato1",cod);
                i.putExtra("dato2",titulo);
                startActivity(i);
            }
        });


    }

    private String getTituloAlarma(String cod){
        JSONParser parser=new JSONParser();
        String titulo = null;
        try {
            InputStream in=getAssets().open("Alarma "+cod+".json");
            Object objeto=parser.parse(in.toString());
            JSONObject jsonObject = (JSONObject) objeto;
            titulo = (String) jsonObject.get("Título");
        }catch (Exception e){
            e.printStackTrace();
        }
        return titulo;
    }
}

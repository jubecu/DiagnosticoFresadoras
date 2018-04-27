package com.example.pc.diagnosticofresadoras;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoAlarmaActivity extends AppCompatActivity {

    Button bComenzar;
    TextView tvAlarmaTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_alarma);

        bComenzar = (Button) findViewById(R.id.bComenzar);

        agregarTitulo();

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InfoAlarmaActivity.this, QuestionsActivity.class);
                i.putExtra("dato1",tvAlarmaTitulo.getText());
                startActivity(i);
            }
        });
    }

    private void agregarTitulo(){
        Bundle extras=getIntent().getExtras();
        String d1=extras.getString("dato1");
        String d2=extras.getString("dato2");

        tvAlarmaTitulo=(TextView) findViewById(R.id.tvAlarmaTitulo);
        tvAlarmaTitulo.setText("Alarma "+d1+": "+d2);
    }
}

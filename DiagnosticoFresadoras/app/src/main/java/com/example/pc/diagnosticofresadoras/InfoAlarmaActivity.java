package com.example.pc.diagnosticofresadoras;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import uk.co.senab.photoview.PhotoViewAttacher;

public class InfoAlarmaActivity extends AppCompatActivity {

    Button bComenzar;
    TextView tvAlarmaTitulo,tvDesc;
    ImageView image;
    PhotoViewAttacher pAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_alarma);

        bComenzar = (Button) findViewById(R.id.bComenzar);

        fillData();

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InfoAlarmaActivity.this, QuestionsActivity.class);
                i.putExtra("dato1",tvAlarmaTitulo.getText());
                startActivity(i);
            }
        });
    }

    private void fillData(){
        Bundle extras=getIntent().getExtras();
        String d1=extras.getString("dato1");
        String d2=extras.getString("dato2");
        String d3=extras.getString("dato3");

        tvAlarmaTitulo=(TextView) findViewById(R.id.tvAlarmaTitulo);
        tvDesc=(TextView) findViewById(R.id.tvDesc);
        image=(ImageView) findViewById(R.id.ivImage);

        tvAlarmaTitulo.setText("Alarma "+d1+": "+d2);
        tvDesc.setText(d3);

        switch (d1){
            case "631":
                image.setImageResource(R.drawable.a631);
                break;
            case "713":
                image.setImageResource(R.drawable.a713);
                break;
        }
        pAttacher = new PhotoViewAttacher(image);
    }
}

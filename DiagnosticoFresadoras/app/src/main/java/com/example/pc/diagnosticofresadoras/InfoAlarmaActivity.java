package com.example.pc.diagnosticofresadoras;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.diagnosticofresadoras.modeloAlarmas.AlarmTable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import uk.co.senab.photoview.PhotoViewAttacher;

public class InfoAlarmaActivity extends AppCompatActivity {

    Button bComenzar;
    TextView tvAlarmaTitulo, tvDesc;
    PhotoViewAttacher pAttacher;
    AlarmTable alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_alarma);

        bComenzar = findViewById(R.id.bComenzar);
        Bundle extras = getIntent().getExtras();
        final String cod = extras.getString("numAlarm");
        int num = Integer.parseInt(cod);
        alarms = AlarmTable.getInstance();

        fillData(cod);

        LinearLayout llImages = findViewById(R.id.llImages);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (String nameImage : alarms.getAlarm(num).getImages()) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(llParams);
            //String name = nameImage + ".png";
            image.setImageResource(alarms.getDiccImages().get(nameImage));
            pAttacher = new PhotoViewAttacher(image);
            llImages.addView(image);
        }

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoAlarmaActivity.this, QuestionsActivity.class);
                i.putExtra("numAlarm", cod);
                i.putExtra("idQuestion", 1.0);
                startActivity(i);
            }
        });

        try {
            //FileOutputStream fileEdit = new FileOutputStream(getFile("registry.csv"));
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileEdit));
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("registry.csv", Context.MODE_PRIVATE));
            //fileEdit.write(cod.getBytes());
            osw.write(cod);
            //bw.newLine();
            osw.close();
            //fileEdit.close();
        } catch (IOException ex) {
            Log.v("InfoAlarmaActivity", "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private File getFile(String filename) {
        for (File file : getFilesDir().listFiles()) {
            if (file.getName().equals(filename))
                return file;
        }
        return null;
    }

    private void fillData(String cod) {
        String title, desc;
        int num = Integer.parseInt(cod);

        tvAlarmaTitulo = findViewById(R.id.tvAlarmaTitulo);
        tvDesc = findViewById(R.id.tvDesc);
        title = AlarmTable.getInstance().getAlarm(num).getTitle();
        desc = AlarmTable.getInstance().getAlarm(num).getDescription();

        tvAlarmaTitulo.setText("Alarma " + cod + ": " + title);
        tvDesc.setText(desc);
    }

}


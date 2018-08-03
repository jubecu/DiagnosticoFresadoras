package com.ubu.tfg.diagnosticofresadoras;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;

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
        final int num = Integer.parseInt(cod);
        alarms = AlarmTable.getInstance();

        fillData(cod);

        fillImages(num);

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoAlarmaActivity.this, QuestionsActivity.class);
                i.putExtra("numAlarm", cod);
                i.putExtra("idQuestion", alarms.getAlarm(num).getQuestionByPosition(0).getId());
                startActivity(i);
            }
        });
    }

   /* private File getFile(String filename) {
        for (File file : getFilesDir().listFiles()) {
            if (file.getName().equals(filename))
                return file;
        }
        return null;
    }*/

    private void fillData(String cod) {
        String title, desc;
        int num = Integer.parseInt(cod);

        tvAlarmaTitulo = findViewById(R.id.tvAlarmaTitulo);
        tvDesc = findViewById(R.id.tvDesc);
        title = alarms.getAlarm(num).getTitle();
        desc = alarms.getAlarm(num).getDescription();

        tvAlarmaTitulo.setText("Alarma " + cod + ": " + title);
        tvDesc.setText(desc);
    }

    private void fillImages(int num) {
        LinearLayout llImages = findViewById(R.id.llImages);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (String nameImage : alarms.getAlarm(num).getImages()) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(llParams);
            int resImage = alarms.getDiccImages().get(nameImage);
            image.setImageResource(resImage);
            pAttacher = new PhotoViewAttacher(image);
            llImages.addView(image);
        }
    }

}

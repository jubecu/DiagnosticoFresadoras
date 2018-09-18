package com.ubu.tfg.diagnosticofresadoras;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Alarm;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Activity de la pantalla donde se muestra la información principal de una alarma.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class InfoAlarmaActivity extends AppCompatActivity {
    /**
     * Conjunto de todas las alarmas
     */
    AlarmTable alarms;
    /**
     * Alarma de la cual se muestra la información en la pantalla
     */
    Alarm alarm;
    /**
     * Código de la alarma
     */
    String cod;

    /**
     * Rellena la pantalla con toda la información de la alarma.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_alarma);

        Button bComenzar = findViewById(R.id.bComenzar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cod = extras.getString("codAlarm");
        }
        alarms = AlarmTable.getInstance();
        alarm = alarms.getAlarm(cod);

        fillData();

        fillImages();

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoAlarmaActivity.this, QuestionsActivity.class);
                i.putExtra("codAlarm", cod);
                i.putExtra("idQuestion", alarm.getFirstQuestion().getId());
                startActivity(i);
            }
        });
    }

    /**
     * Establece el número, título y descripción de la alarma para mostrarlo en la pantalla.
     */
    private void fillData() {
        String title, desc;

        TextView tvAlarmaTitulo = findViewById(R.id.tvAlarmaTitulo);
        TextView tvDesc = findViewById(R.id.tvDesc);
        long num = alarm.getNum();
        title = alarm.getTitle();
        desc = alarm.getDescription();

        tvAlarmaTitulo.setText("Alarma " + String.valueOf(num) + ": " + title);
        tvDesc.setText(desc);
    }

    /**
     * Establece las imágenes de la alarma para mostrarlas en la pantalla.
     */
    private void fillImages() {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;

        LinearLayout llImages = findViewById(R.id.llImages);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                widthPixels / 2, heightPixels / 3);

        for (String nameImage : alarm.getImages()) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(llParams);
            int resImage = alarms.getDiccImages().get(nameImage);
            image.setImageResource(resImage);
            new PhotoViewAttacher(image);
            llImages.addView(image);
        }
    }

}


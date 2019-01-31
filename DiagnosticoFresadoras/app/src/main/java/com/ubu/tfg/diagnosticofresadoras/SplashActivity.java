package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Alarm;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;

import java.io.IOException;

/**
 * Activity del splashscreen de la aplicación.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Define las características de la pantalla y el splashscreen.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        int DURACION_SPLASH = 2000;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);

        saveAlarms();

    }

    /**
     * Parsea todos los ficheros JSON de las alarmas, crea sus respectivos objetos Alarm y los
     * almacena en el Map alarms. Utiliza la clase ManagementJSON para ello.
     */
    private void saveAlarms() {
        Alarm alarmES, alarmEN;
        String num, jsonES = null, jsonEN = null;
        ManagementJSON managementJSON_es, managementJSON_en;
        String[] alarms = getResources().getStringArray(R.array.alarmas);
        for (String alarm : alarms) {
            num = alarm.substring(0, 3);
            managementJSON_es = new ManagementJSON(num, "es");
            managementJSON_en = new ManagementJSON(num, "en");
            try {
                jsonES = managementJSON_es.createJsonFromAssets(this);
                jsonEN = managementJSON_en.createJsonFromAssets(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            alarmES = managementJSON_es.getAlarm(jsonES);
            alarmEN = managementJSON_en.getAlarm(jsonEN);
            AlarmTable.getInstance().addAlarm(alarmES, "es");
            AlarmTable.getInstance().addAlarm(alarmEN, "en");
        }
    }
}

package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

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

    }
}

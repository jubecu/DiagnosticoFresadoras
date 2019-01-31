package com.ubu.tfg.diagnosticofresadoras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Activity de la pantalla que muestra una imagen a tamaño completo.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class ImageActivity extends AppCompatActivity {

    /**
     * Crea un ImageView y lo asocia con una imagen.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = findViewById(R.id.ivImage);
        int resImage = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resImage = extras.getInt("resImage");
        }
        imageView.setImageResource(resImage);
        new PhotoViewAttacher(imageView);
    }
}

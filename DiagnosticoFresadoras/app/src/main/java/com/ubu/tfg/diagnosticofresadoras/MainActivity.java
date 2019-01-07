package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity de la pantalla de inicio o pantalla principal.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Inicializa el toolbar de la sección de ajustes y los dos botones.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Button chooseAlarm = findViewById(R.id.bSingleAlarm);
        Button getAlarms = findViewById(R.id.bManyAlarms);
        TextView textView = findViewById(R.id.tvTitulo);

        textView.setTextSize(getResources().getDimension(R.dimen.text_size));
        chooseAlarm.setTextSize(getResources().getDimension(R.dimen.text_size_button));
        getAlarms.setTextSize(getResources().getDimension(R.dimen.text_size_button));

        chooseAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChooseAlarmActivity.class);
                startActivity(i);
            }
        });

        getAlarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ApiRestActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * Inicializa el contenido del menú/toolbar a partir del XML correspondiente.
     *
     * @param menu El menú de opciones
     * @return True para que se muestre el menú en la pantalla
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /**
     * Especifica la acción de cada elemento del menú/toolbar.
     *
     * @param item Elemento del menú que se ha seleccionado
     * @return False para permitir el procesamiento normal del menú, True para procesarlo aquí
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferencesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

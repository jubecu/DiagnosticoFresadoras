package com.ubu.tfg.diagnosticofresadoras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

/**
 * Activity de la pantalla donde se elige la alarma que se quiere consultar.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class ChooseAlarmActivity extends AppCompatActivity {
    /**
     * Spinner o menú desplegable con todas las alarmas disponibles para elegir
     */
    Spinner alarmas;
    /**
     * Preferencias compartidas de la aplicación
     */
    SharedPreferences preferences;
    NavigationDrawer navDr;

    /**
     * Inicializa y da funcionalidad a todos los elementos de la pantalla.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_alarm);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Button bInicio = findViewById(R.id.bInicio);
        alarmas = findViewById(R.id.spAlarmas);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        navDr = NavigationDrawer.getInstance();
        final DrawerLayout menu = findViewById(R.id.dlMenu);

        ExpandableListView listMenu = findViewById(R.id.lvMenu);
        ExpandableListAdapter adapterMenu =
                new ExpandableListAdapter(this, navDr.getListGroup(), navDr.getListChildren());
        listMenu.setAdapter(adapterMenu);

        navDr.onItemClick(listMenu, this);
        navDr.putImage(menu, myToolbar, this);

        createRegistryFile();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.alarmas, android.R.layout.simple_list_item_1);
        alarmas.setAdapter(adapter);

        bInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num, lang, cod;
                num = alarmas.getSelectedItem().toString().substring(0, 3);
                lang = getLanguage();
                cod = num + lang;

                Intent i = new Intent(ChooseAlarmActivity.this, InfoAlarmaActivity.class);
                i.putExtra("codAlarm", cod);
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

    /**
     * Crea un archivo CSV, en caso de que no exista, para guardar el registro de la actividad que
     * vaya realizando el usuario.
     */
    private void createRegistryFile() {
        if (!existsFile()) {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(
                        "registry.csv", Context.MODE_APPEND));
                osw.write("Alarma,Pregunta,Respuesta,Timestamp\n");
                osw.close();
            } catch (IOException ex) {
                Log.v("createRegistryFile", "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * Comprueba si el archivo para guardar el registro ya existe en el explorador de archivos del
     * dispositivo.
     *
     * @return True si ya existe, false si no
     */
    private boolean existsFile() {
        for (String file : fileList()) {
            if (file.equals("registry.csv"))
                return true;
        }
        return false;
    }

    /**
     * Obtiene el idioma de las preferencias.
     *
     * @return Idioma elegido en las preferencias
     */
    private String getLanguage() {
        String language;
        language = preferences.getString("idioma", "");
        if (language.compareTo("Español") == 0)
            language = "es";
        else if (language.compareTo("English") == 0)
            language = "en";
        else if (language.isEmpty())
            language = Locale.getDefault().getLanguage();
        return language;
    }
}

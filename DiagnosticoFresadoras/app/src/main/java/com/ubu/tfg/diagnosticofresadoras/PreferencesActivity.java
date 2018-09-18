package com.ubu.tfg.diagnosticofresadoras;

import android.preference.PreferenceActivity;
import android.os.Bundle;

/**
 * Activity de la pantalla que muestra la sección de ajustes y preferencias.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class PreferencesActivity extends PreferenceActivity {

    /**
     * Establece la lista de preferecias a partir de un XML.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}

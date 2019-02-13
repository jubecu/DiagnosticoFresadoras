package com.ubu.tfg.diagnosticofresadoras;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.Locale;

/**
 * Clase que implementa el panel lateral de navegación.
 *
 * @author Juan Francisco Benito Cuesta
 */
class NavigationDrawer {
    /**
     * Preferencias compartidas de la aplicación
     */
    private SharedPreferences preferences;
    /**
     * Activity donde se va a implementar el panel lateral
     */
    private Activity activity;

    /**
     * Constructor que establece la activity.
     */
    NavigationDrawer(Activity activity) {
        this.activity = activity;
    }

    /**
     * Establece qué debe hacer cada opción del menú al ser pulsada.
     *
     * @param expandableListView Lista expandible a la que se la va a dar esta configuración
     */
    void onItemClick(ExpandableListView expandableListView) {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view,
                                        int groupPosition, long l) {
                if (groupPosition == 0) {
                    Intent i = new Intent(activity, MainActivity.class);
                    activity.startActivity(i);
                } else if (groupPosition == 15) {
                    activity.finish();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent,
                                        View v, int groupPosition, int childPosition,
                                        long id) {
                String codAlarm, language;
                preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                language = getLanguage();
                switch (groupPosition) {
                    case 1:
                        codAlarm = "631" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, "6.1");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, "6.2");
                                break;
                        }
                        break;
                    case 2:
                        codAlarm = "712" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "0.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "0.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, "5.0");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, "5.1");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, "6.0");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, "6.1");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, "7.0");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, "7.1");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, "8.0");
                                break;
                            case 15:
                                goToQuestionActivity(codAlarm, "8.1");
                                break;
                            case 16:
                                goToQuestionActivity(codAlarm, "8.2");
                                break;
                            case 17:
                                goToQuestionActivity(codAlarm, "8.3");
                                break;
                            case 18:
                                goToQuestionActivity(codAlarm, "9.0");
                                break;
                        }
                        break;
                    case 3:
                        codAlarm = "713" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                        }
                        break;
                    case 4:
                        codAlarm = "821" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, "6.1");
                                break;
                        }
                        break;
                    case 5:
                        codAlarm = "822" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, "6.1");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, "6.2");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, "6.3");
                                break;
                        }
                        break;
                    case 6:
                        codAlarm = "846" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, "6.1");
                                break;
                        }
                        break;
                    case 7:
                        codAlarm = "852" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, "5.2");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, "5.3");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, "7.0");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, "7.1");
                                break;
                            case 15:
                                goToQuestionActivity(codAlarm, "8.0");
                                break;
                            case 16:
                                goToQuestionActivity(codAlarm, "8.1");
                                break;
                        }
                        break;
                    case 8:
                        codAlarm = "928" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, "4.2");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, "5.0");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, "5.1");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, "6.0");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, "6.1");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, "6.2");
                                break;
                        }
                        break;
                    case 9:
                        codAlarm = "968" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "1.2");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                        }
                        break;
                    case 10:
                        codAlarm = "969" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "1.2");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "2.1");
                                break;
                        }
                        break;
                    case 11:
                        codAlarm = "970" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.2");
                                break;
                        }
                        break;
                    case 12:
                        codAlarm = "971" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                        }
                        break;
                    case 13:
                        codAlarm = "972" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, "3.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, "3.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, "4.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, "4.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, "4.2");
                                break;
                        }
                        break;
                    case 14:
                        codAlarm = "973" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, "2.0");
                                break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    /**
     * Pasa a la pantalla/activity de una pregunta.
     *
     * @param codAlarm   Número de la alarma más el idioma a la que pertenece la pregunta
     * @param idQuestion Id de la pregunta
     */
    private void goToQuestionActivity(String codAlarm, String idQuestion) {
        Intent i = new Intent(activity, QuestionsActivity.class);
        i.putExtra("codAlarm", codAlarm);
        i.putExtra("idQuestion", idQuestion);
        activity.startActivity(i);
    }

    /**
     * Pasa a la pantalla/activity de la información de una alarma.
     *
     * @param codAlarm Número de la alarma más el idioma
     */
    private void goToInfoActivity(String codAlarm) {
        Intent i = new Intent(activity, InfoAlarmaActivity.class);
        i.putExtra("codAlarm", codAlarm);
        activity.startActivity(i);
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

    /**
     * Inicializa el panel lateral de navegación asignándole una imagen, un DrawerLayout y un
     * toolbar.
     *
     * @param menu    DrawerLayout que consiste en un contenedor que permite extraer vistas
     *                interactivas desde uno de los bordes verticales de la pantalla
     * @param toolbar Barra de herramientas
     */
    void initialize(final DrawerLayout menu, Toolbar toolbar) {
        Drawable image = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.ic_drawer,
                activity.getTheme());
        ActionBarDrawerToggle menuToggle = new ActionBarDrawerToggle(activity, menu, toolbar,
                R.string.menu_open, R.string.menu_close);
        menuToggle.setDrawerIndicatorEnabled(false);
        menuToggle.setHomeAsUpIndicator(image);

        menuToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu.isDrawerVisible(GravityCompat.START)) {
                    menu.closeDrawer(GravityCompat.START);
                } else {
                    menu.openDrawer(GravityCompat.START);
                }
            }
        });
    }
}

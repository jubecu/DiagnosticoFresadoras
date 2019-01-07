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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class NavigationDrawer {

    private static NavigationDrawer instance = null;
    private List<String> listGroup;
    private HashMap<String, List<String>> listChildren;
    /**
     * Preferencias compartidas de la aplicación
     */
    SharedPreferences preferences;

    private NavigationDrawer() {
        listGroup = new ArrayList<>();
        listChildren = new HashMap<>();
        createListData();

    }

    public static NavigationDrawer getInstance() {
        if (instance == null)
            instance = new NavigationDrawer();
        return instance;
    }

    public List<String> getListGroup() {
        return listGroup;
    }

    public HashMap<String, List<String>> getListChildren() {
        return listChildren;
    }

    private void createListData() {
        listGroup.add("Inicio");
        listGroup.add("Alarma 631");
        listGroup.add("Alarma 712");
        listGroup.add("Alarma 713");
        listGroup.add("Alarma 821");
        listGroup.add("Alarma 822");
        listGroup.add("Alarma 846");
        listGroup.add("Alarma 852");
        listGroup.add("Alarma 928");
        listGroup.add("Alarma 968");
        listGroup.add("Alarma 969");
        listGroup.add("Alarma 970");
        listGroup.add("Alarma 971");
        listGroup.add("Alarma 972");
        listGroup.add("Alarma 973");
        listGroup.add("Salir");

        List<String> alarm631 = new ArrayList<>();
        alarm631.add("Información");
        alarm631.add("Pregunta 1");
        alarm631.add("Pregunta 1.1");
        alarm631.add("Pregunta 2");
        alarm631.add("Pregunta 2.1");
        alarm631.add("Pregunta 3");
        alarm631.add("Pregunta 3.1");
        alarm631.add("Pregunta 4");
        alarm631.add("Pregunta 4.1");
        alarm631.add("Pregunta 5");
        alarm631.add("Pregunta 5.1");
        alarm631.add("Pregunta 6");
        alarm631.add("Pregunta 6.1");
        alarm631.add("Pregunta 6.2");

        List<String> alarm712 = new ArrayList<>();
        alarm712.add("Información");
        alarm712.add("Pregunta 0");
        alarm712.add("Pregunta 0.1");
        alarm712.add("Pregunta 1");
        alarm712.add("Pregunta 1.1");
        alarm712.add("Pregunta 2");
        alarm712.add("Pregunta 2.1");
        alarm712.add("Pregunta 4");
        alarm712.add("Pregunta 5");
        alarm712.add("Pregunta 5.1");
        alarm712.add("Pregunta 6");
        alarm712.add("Pregunta 6.1");
        alarm712.add("Pregunta 7");
        alarm712.add("Pregunta 7.1");
        alarm712.add("Pregunta 8");
        alarm712.add("Pregunta 8.1");
        alarm712.add("Pregunta 8.2");
        alarm712.add("Pregunta 8.3");
        alarm712.add("Pregunta 9");

        List<String> alarm713 = new ArrayList<>();
        alarm713.add("Información");
        alarm713.add("Pregunta 1");
        alarm713.add("Pregunta 1.1");

        List<String> alarm821 = new ArrayList<>();
        alarm821.add("Información");
        alarm821.add("Pregunta 1");
        alarm821.add("Pregunta 1.1");
        alarm821.add("Pregunta 2");
        alarm821.add("Pregunta 2.1");
        alarm821.add("Pregunta 3");
        alarm821.add("Pregunta 3.1");
        alarm821.add("Pregunta 4");
        alarm821.add("Pregunta 4.1");
        alarm821.add("Pregunta 5");
        alarm821.add("Pregunta 5.1");
        alarm821.add("Pregunta 6");
        alarm821.add("Pregunta 6.1");

        List<String> alarm822 = new ArrayList<>();
        alarm822.addAll(alarm821);
        alarm822.add("Pregunta 6.2");
        alarm822.add("Pregunta 6.3");

        List<String> alarm846 = new ArrayList<>();
        alarm846.addAll(alarm821);

        List<String> alarm852 = new ArrayList<>();
        alarm852.add("Información");
        alarm852.add("Pregunta 1");
        alarm852.add("Pregunta 1.1");
        alarm852.add("Pregunta 2");
        alarm852.add("Pregunta 2.1");
        alarm852.add("Pregunta 3");
        alarm852.add("Pregunta 3.1");
        alarm852.add("Pregunta 4");
        alarm852.add("Pregunta 4.1");
        alarm852.add("Pregunta 5a");
        alarm852.add("Pregunta 5a.1");
        alarm852.add("Pregunta 5b");
        alarm852.add("Pregunta 5b.1");
        alarm852.add("Pregunta 7");
        alarm852.add("Pregunta 7.1");
        alarm852.add("Pregunta 8");
        alarm852.add("Pregunta 8.1");

        List<String> alarm928 = new ArrayList<>();
        alarm928.add("Información");
        alarm928.add("Pregunta 1");
        alarm928.add("Pregunta 1.1");
        alarm928.add("Pregunta 2");
        alarm928.add("Pregunta 2.1");
        alarm928.add("Pregunta 3");
        alarm928.add("Pregunta 3.1");
        alarm928.add("Pregunta 4");
        alarm928.add("Pregunta 4.1");
        alarm928.add("Pregunta 4.2");
        alarm928.add("Pregunta 5");
        alarm928.add("Pregunta 5.1");
        alarm928.add("Pregunta 6");
        alarm928.add("Pregunta 6.1");
        alarm928.add("Pregunta 6.2");

        List<String> alarm968 = new ArrayList<>();
        alarm968.add("Información");
        alarm968.add("Pregunta 1");
        alarm968.add("Pregunta 1.1");
        alarm968.add("Pregunta 1.2");
        alarm968.add("Pregunta 2");

        List<String> alarm969 = new ArrayList<>();
        alarm969.addAll(alarm968);
        alarm969.add("Pregunta 2.1");

        List<String> alarm970 = new ArrayList<>();
        alarm970.add("Información");
        alarm970.add("Pregunta 1");
        alarm970.add("Pregunta 2");
        alarm970.add("Pregunta 3");
        alarm970.add("Pregunta 3.1");
        alarm970.add("Pregunta 4");
        alarm970.add("Pregunta 4.1");
        alarm970.add("Pregunta 4.2");

        List<String> alarm971 = new ArrayList<>();
        alarm971.add("Información");
        alarm971.add("Pregunta 1");
        alarm971.add("Pregunta 2");

        List<String> alarm972 = new ArrayList<>();
        alarm972.addAll(alarm970);

        List<String> alarm973 = new ArrayList<>();
        alarm973.addAll(alarm971);

        listChildren.put(listGroup.get(1), alarm631);
        listChildren.put(listGroup.get(2), alarm712);
        listChildren.put(listGroup.get(3), alarm713);
        listChildren.put(listGroup.get(4), alarm821);
        listChildren.put(listGroup.get(5), alarm822);
        listChildren.put(listGroup.get(6), alarm846);
        listChildren.put(listGroup.get(7), alarm852);
        listChildren.put(listGroup.get(8), alarm928);
        listChildren.put(listGroup.get(9), alarm968);
        listChildren.put(listGroup.get(10), alarm969);
        listChildren.put(listGroup.get(11), alarm970);
        listChildren.put(listGroup.get(12), alarm971);
        listChildren.put(listGroup.get(13), alarm972);
        listChildren.put(listGroup.get(14), alarm973);
    }

    public void onItemClick(ExpandableListView expandableListView, final Activity activity) {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
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
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, activity, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, activity, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, activity, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, activity, "6.1");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, activity, "6.2");
                                break;
                        }
                        break;
                    case 2:
                        codAlarm = "712" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "0.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "0.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, activity, "5.0");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, activity, "5.1");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, activity, "6.0");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, activity, "6.1");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, activity, "7.0");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, activity, "7.1");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, activity, "8.0");
                                break;
                            case 15:
                                goToQuestionActivity(codAlarm, activity, "8.1");
                                break;
                            case 16:
                                goToQuestionActivity(codAlarm, activity, "8.2");
                                break;
                            case 17:
                                goToQuestionActivity(codAlarm, activity, "8.3");
                                break;
                        }
                        break;
                    case 3:
                        codAlarm = "713" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                        }
                        break;
                    case 4:
                        codAlarm = "821" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, activity, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, activity, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, activity, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, activity, "6.1");
                                break;
                        }
                        break;
                    case 5:
                        codAlarm = "822" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, activity, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, activity, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, activity, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, activity, "6.1");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, activity, "6.2");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, activity, "6.3");
                                break;
                        }
                        break;
                    case 6:
                        codAlarm = "846" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, activity, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, activity, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, activity, "6.0");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, activity, "6.1");
                                break;
                        }
                        break;
                    case 7:
                        codAlarm = "852" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, activity, "5.0");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, activity, "5.1");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, activity, "5.2");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, activity, "5.3");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, activity, "7.0");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, activity, "7.1");
                                break;
                            case 15:
                                goToQuestionActivity(codAlarm, activity, "8.0");
                                break;
                            case 16:
                                goToQuestionActivity(codAlarm, activity, "8.1");
                                break;
                        }
                        break;
                    case 8:
                        codAlarm = "928" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 8:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 9:
                                goToQuestionActivity(codAlarm, activity, "4.2");
                                break;
                            case 10:
                                goToQuestionActivity(codAlarm, activity, "5.0");
                                break;
                            case 11:
                                goToQuestionActivity(codAlarm, activity, "5.1");
                                break;
                            case 12:
                                goToQuestionActivity(codAlarm, activity, "6.0");
                                break;
                            case 13:
                                goToQuestionActivity(codAlarm, activity, "6.1");
                                break;
                            case 14:
                                goToQuestionActivity(codAlarm, activity, "6.2");
                                break;
                        }
                        break;
                    case 9:
                        codAlarm = "968" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "1.2");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                        }
                        break;
                    case 10:
                        codAlarm = "969" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "1.1");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "1.2");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "2.1");
                                break;
                        }
                        break;
                    case 11:
                        codAlarm = "970" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.2");
                                break;
                        }
                        break;
                    case 12:
                        codAlarm = "971" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                        }
                        break;
                    case 13:
                        codAlarm = "972" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                            case 3:
                                goToQuestionActivity(codAlarm, activity, "3.0");
                                break;
                            case 4:
                                goToQuestionActivity(codAlarm, activity, "3.1");
                                break;
                            case 5:
                                goToQuestionActivity(codAlarm, activity, "4.0");
                                break;
                            case 6:
                                goToQuestionActivity(codAlarm, activity, "4.1");
                                break;
                            case 7:
                                goToQuestionActivity(codAlarm, activity, "4.2");
                                break;
                        }
                        break;
                    case 14:
                        codAlarm = "973" + language;
                        switch (childPosition) {
                            case 0:
                                goToInfoActivity(codAlarm, activity);
                                break;
                            case 1:
                                goToQuestionActivity(codAlarm, activity, "1.0");
                                break;
                            case 2:
                                goToQuestionActivity(codAlarm, activity, "2.0");
                                break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void goToQuestionActivity(String codAlarm, Activity activity, String idQuestion) {
        Intent i = new Intent(activity, QuestionsActivity.class);
        i.putExtra("codAlarm", codAlarm);
        i.putExtra("idQuestion", idQuestion);
        activity.startActivity(i);
    }

    private void goToInfoActivity(String codAlarm, Activity activity) {
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

    public void putImage(final DrawerLayout menu, Toolbar toolbar, Activity activity) {
        Drawable image = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.ic_drawer, activity.getTheme());
        ActionBarDrawerToggle menuToggle =
                new ActionBarDrawerToggle(activity, menu, toolbar, R.string.menu_open, R.string.menu_close);
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

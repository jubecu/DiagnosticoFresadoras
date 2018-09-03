package com.ubu.tfg.diagnosticofresadoras.modeloAlarmas;


import com.ubu.tfg.diagnosticofresadoras.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que almacena las alarmas que va parseando la aplicación y todas las imágenes que se
 * usan.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class AlarmTable {
    /**
     * Instancia de la clase
     */
    private static AlarmTable instance = null;
    /**
     * Map que almacena las alarmas parseadas/consultadas. La clave es el número de la alarma
     * seguido de “es” o “en” según el idioma, y el valor es el propio objeto Alarm.
     */
    private Map<String, Alarm> alarms;
    /**
     * Map con todas las imágenes de las alarmas y preguntas. La clave es el nombre del archivo y el
     * valor es la ruta donde se encuentra ese archivo.
     */
    private Map<String, Integer> diccImages;

    /**
     * Constructor que inicializa los Map y rellena el de imágenes.
     */
    private AlarmTable() {
        alarms = new LinkedHashMap<>();
        diccImages = new HashMap<>();
        fillDiccImages();
    }

    /**
     * Crea una instancia de AlarmTable si esta vacía y la devuelve.
     *
     * @return Instancia de AlarmTable
     */
    public static AlarmTable getInstance() {
        if (instance == null)
            instance = new AlarmTable();
        return instance;
    }

    /**
     * Añade una alarma al Map.
     *
     * @param alarm    Alarma a añadir
     * @param language Idioma de la alarma a añadir
     */
    public void addAlarm(Alarm alarm, String language) {
        Integer num = (int) (long) alarm.getNum();
        String cod = num + language;
        alarms.put(cod, alarm);
    }

    /**
     * Devuelve una alarma del Map a partir de la clave.
     *
     * @param cod Clave que consta del número de la alarma seguido de "es" o "en"
     * @return Una alarma concreta
     */
    public Alarm getAlarm(String cod) {
        return alarms.get(cod);
    }

    /**
     * Devuelve true si ya hay un registro en el Map con una clave concreta.
     *
     * @param cod Clave a comprobar si se encuentra en el Map
     * @return True si la clave se encuentra en el Map, false si no
     */
    public boolean containsAlarm(String cod) {
        return alarms.containsKey(cod);
    }

    /**
     * Devuelve el Map con todas las imágenes de las alarmas y preguntas.
     *
     * @return Map de las imágenes
     */
    public Map<String, Integer> getDiccImages() {
        return diccImages;
    }

    /**
     * Rellena el Map con todas las imágenes que se usan en la aplicación.
     */
    private void fillDiccImages() {
        diccImages.put("a631", R.drawable.a631);
        diccImages.put("a631q5", R.drawable.a631q5);
        diccImages.put("a712", R.drawable.a712);
        diccImages.put("a712_2", R.drawable.a712_2);
        diccImages.put("a713", R.drawable.a713);
        diccImages.put("a713_2", R.drawable.a713_2);
        diccImages.put("a821", R.drawable.a821);
        diccImages.put("a821q6", R.drawable.a821q6);
        diccImages.put("a822", R.drawable.a822);
        diccImages.put("a822q6", R.drawable.a822q6);
        diccImages.put("a846", R.drawable.a846);
        diccImages.put("a846_2", R.drawable.a846_2);
        diccImages.put("a846_3", R.drawable.a846_3);
        diccImages.put("a846_4", R.drawable.a846_4);
        diccImages.put("a852", R.drawable.a852);
        diccImages.put("a852q7", R.drawable.a852q7);
        diccImages.put("a928", R.drawable.a928);
        diccImages.put("a928_2", R.drawable.a928_2);
        diccImages.put("a928_3", R.drawable.a928_3);
        diccImages.put("a928_4", R.drawable.a928_4);
        diccImages.put("a968", R.drawable.a968);
        diccImages.put("a968_2", R.drawable.a968_2);
        diccImages.put("a969", R.drawable.a969);
        diccImages.put("a969_2", R.drawable.a969_2);
        diccImages.put("a970", R.drawable.a970);
        diccImages.put("a970_2", R.drawable.a970_2);
        diccImages.put("a971", R.drawable.a971);
        diccImages.put("a971_2", R.drawable.a971_2);
        diccImages.put("a972", R.drawable.a972);
        diccImages.put("a972_2", R.drawable.a972_2);
        diccImages.put("a973", R.drawable.a973);
        diccImages.put("a973_2", R.drawable.a973_2);
        diccImages.put("img_circuito_general_aire", R.drawable.img_circuito_general_aire);
        diccImages.put("img_filtros_deposito_recogida_aceite",
                R.drawable.img_filtros_deposito_recogida_aceite);
        diccImages.put("img_presion_circuito_engrase", R.drawable.img_presion_circuito_engrase);
        diccImages.put("img_presostato_general_aire", R.drawable.img_presostato_general_aire);
        diccImages.put("img_sennales_detectores_flujo_aceite",
                R.drawable.img_sennales_detectores_flujo_aceite);
        diccImages.put("img_vacio_1_vacuostato_en_la_t",
                R.drawable.img_vacio_1_vacuostato_en_la_t);
        diccImages.put("img_vacuostato_cable_eng", R.drawable.img_vacuostato_cable_eng);
        diccImages.put("img_vacuostato_cable_esp", R.drawable.img_vacuostato_cable_esp);
        diccImages.put("keyence_green", R.drawable.keyence_green);
        diccImages.put("keyence_red", R.drawable.keyence_red);
        diccImages.put("img_vacio_1_vacuostato_deposito",
                R.drawable.img_vacio_1_vacuostato_deposito);
    }
}

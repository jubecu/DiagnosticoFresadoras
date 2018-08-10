package com.ubu.tfg.diagnosticofresadoras.modeloAlarmas;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AlarmTable {

    private static AlarmTable instance = null;
    private Map<String, Alarm> alarms;
    private Map<String, Integer> diccImages;

    private AlarmTable() {
        alarms = new LinkedHashMap<>();
        diccImages = new HashMap<>();
        fillDiccImages();
    }

    public static AlarmTable getInstance() {
        if (instance == null)
            instance = new AlarmTable();
        return instance;
    }

    public void addAlarm(Alarm alarm, String language) {
        Integer num = (int) (long) alarm.getNum();
        String cod = num + language;
        alarms.put(cod, alarm);
    }

    public Alarm getAlarm(String cod) {
        return alarms.get(cod);
    }

    public boolean containsAlarm(String cod) {
        return alarms.containsKey(cod);
    }

    public Map<String, Integer> getDiccImages() {
        return diccImages;
    }

   /* public int getOrderAlarm(int num) {
        int order = 1;
        for (Entry<Integer, Alarm> entry : alarms.entrySet()) {
            if (entry.getKey() == num) {
                return order;
            }
            order++;
        }
        return 0;
    }*/

    private void fillDiccImages() {
        diccImages.put("a631", com.ubu.tfg.diagnosticofresadoras.R.drawable.a631);
        diccImages.put("a631q5", com.ubu.tfg.diagnosticofresadoras.R.drawable.a631q5);
        diccImages.put("a712", com.ubu.tfg.diagnosticofresadoras.R.drawable.a712);
        diccImages.put("a712_2", com.ubu.tfg.diagnosticofresadoras.R.drawable.a712_2);
        diccImages.put("a712q1_1", com.ubu.tfg.diagnosticofresadoras.R.drawable.a712q1_1);
        diccImages.put("a712q5", com.ubu.tfg.diagnosticofresadoras.R.drawable.a712q5);
        diccImages.put("a712q7", com.ubu.tfg.diagnosticofresadoras.R.drawable.a712q7);
        diccImages.put("a712q8_3", com.ubu.tfg.diagnosticofresadoras.R.drawable.a712q8_3);
        diccImages.put("a713", com.ubu.tfg.diagnosticofresadoras.R.drawable.a713);
        diccImages.put("a713_2", com.ubu.tfg.diagnosticofresadoras.R.drawable.a713_2);
        diccImages.put("a631q4a", com.ubu.tfg.diagnosticofresadoras.R.drawable.a631q4a);
        diccImages.put("a631q4b", com.ubu.tfg.diagnosticofresadoras.R.drawable.a631q4b);
        diccImages.put("a631q5", com.ubu.tfg.diagnosticofresadoras.R.drawable.a631q5);
    }
}

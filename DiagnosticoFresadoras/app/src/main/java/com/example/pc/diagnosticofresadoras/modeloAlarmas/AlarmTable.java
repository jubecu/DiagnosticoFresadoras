package com.example.pc.diagnosticofresadoras.modeloAlarmas;


import com.example.pc.diagnosticofresadoras.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AlarmTable {

    private static AlarmTable instance = null;
    Map<Integer, Alarm> alarms;
    Map<String, Integer> diccImages;

    private AlarmTable() {
        alarms = new LinkedHashMap<Integer, Alarm>();
        diccImages = new HashMap<String, Integer>();
        fillDiccImages();
    }

    public static AlarmTable getInstance() {
        if (instance == null)
            instance = new AlarmTable();
        return instance;
    }

    public void addAlarm(Alarm alarm) {
        Integer num = (int) (long) alarm.getNum();
        alarms.put(num, alarm);
    }

    public Alarm getAlarm(int num) {
        return alarms.get(num);
    }

    public Map<String, Integer> getDiccImages() {
        return diccImages;
    }

    public int getOrderAlarm(int num) {
        int order = 1;
        for (Entry<Integer, Alarm> entry : alarms.entrySet()) {
            if (entry.getKey() == num) {
                return order;
            }
            order++;
        }
        return 0;
    }

    private void fillDiccImages() {
        diccImages.put("a631", R.drawable.a631);
        diccImages.put("a631q5", R.drawable.a631q5);
        diccImages.put("a712", R.drawable.a712);
        diccImages.put("a712_2", R.drawable.a712_2);
        diccImages.put("a713", R.drawable.a713);
        diccImages.put("a713_2", R.drawable.a713_2);

    }
}

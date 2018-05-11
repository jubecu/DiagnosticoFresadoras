package com.example.pc.diagnosticofresadoras.modeloAlarmas;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AlarmTable {

    private static AlarmTable instance = null;
    Map<Integer, Alarm> alarms;

    private AlarmTable() {
        alarms = new LinkedHashMap<Integer, Alarm>();
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
}

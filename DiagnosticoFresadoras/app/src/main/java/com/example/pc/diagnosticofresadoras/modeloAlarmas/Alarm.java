package com.example.pc.diagnosticofresadoras.modeloAlarmas;

import java.util.ArrayList;
import java.util.Iterator;

public class Alarm {
    private long num;
    private String title, desp;
    private ArrayList<Question> questions;

    public Alarm(long num, String title, String desp) {
        this.num = num;
        this.title = title;
        this.desp = desp;
        questions = new ArrayList<Question>();
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return desp;
    }

    public void addQuestion(Question ques) {
        questions.add(ques);
    }

    public Question getPregunta(double id) {
        Question questionReturn = null;
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()) {
            Question question = iterator.next();
            if (question.getId() == id) {
                questionReturn = question;
                break;
            }
        }
        return questionReturn;
    }
}

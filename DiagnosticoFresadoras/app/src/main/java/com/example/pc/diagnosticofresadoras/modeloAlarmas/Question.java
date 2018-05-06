package com.example.pc.diagnosticofresadoras.modeloAlarmas;

import java.util.ArrayList;
import java.util.Iterator;

public class Question {
    private double id;
    private String text;
    private ArrayList<Answer> answers;

    public Question(double id, String text) {
        this.id = id;
        this.text = text;
        answers = new ArrayList<Answer>();
    }

    public void addAnswer(Answer ans) {
        answers.add(ans);
    }

    public Answer getAnswer(String id) {
        Answer answerReturn = null;
        Iterator<Answer> iterator = answers.iterator();
        while (iterator.hasNext()) {
            Answer answer = iterator.next();
            if (answer.getId().compareTo(id) == 0) {
                answerReturn = answer;
                break;
            }
        }
        return answerReturn;
    }

    public double getId() {
        return id;
    }
}

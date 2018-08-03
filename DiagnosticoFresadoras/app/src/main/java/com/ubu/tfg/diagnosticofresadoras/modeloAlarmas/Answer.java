package com.ubu.tfg.diagnosticofresadoras.modeloAlarmas;

public class Answer {
    private String id;
    private String text;
    private double next;
    private String finalMessage;

    public Answer(String id, String text, double next) {
        this.id = id;
        this.text = text;
        this.next = next;
    }

    public String getId() {
        return id;
    }

    public void setMessage(String message) {
        finalMessage = message;
    }

    public String getMessage() {
        return finalMessage;
    }

    public double getNext() {
        return next;
    }

    public String getText() {
        return text;
    }
}

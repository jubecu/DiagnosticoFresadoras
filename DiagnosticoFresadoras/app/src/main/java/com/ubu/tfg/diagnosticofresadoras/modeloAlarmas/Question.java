package com.ubu.tfg.diagnosticofresadoras.modeloAlarmas;

import java.util.ArrayList;

/**
 * Clase que representa una pregunta.
 *
 * @author Juan Francisco Benio Cuesta
 */
public class Question {
    /**
     * Identificador de la pregunta
     */
    private double id;
    /**
     * Texto de la pregunta
     */
    private String text;
    /**
     * Lista con los nombres de los archivos de las imágenes de la pregunta
     */
    private ArrayList<String> images;
    /**
     * Lista con las respuestas de la pregunta
     */
    private ArrayList<Answer> answers;

    /**
     * Constructor que asigna un id y un texto e inicializa los ArrayList.
     *
     * @param id   Identificador de la pregunta
     * @param text Texto de la pregunta
     */
    public Question(double id, String text) {
        this.id = id;
        this.text = text;
        answers = new ArrayList<>();
        images = new ArrayList<>();
    }

    /**
     * Devuelve el identificador de la pregunta.
     *
     * @return Identificador de la pregunta
     */
    public double getId() {
        return id;
    }

    /**
     * Devuelve el texto de la pregunta.
     *
     * @return Texto de la pregunta
     */
    public String getText() {
        return text;
    }

    /**
     * Devuelve la lista de respuestas de la pregunta.
     *
     * @return Lista de respuestas de la pregunta
     */
    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    /**
     * Devuelve la lista de los nombres de los archivos de las imágenes.
     *
     * @return Lista de los nombres de los archivos de las imágenes
     */
    public ArrayList<String> getImages() {
        return images;
    }

    /**
     * Añade a la lista un nombre de un archivo de una imagen.
     *
     * @param name Nombre del archivo de una imagen
     */
    public void addImage(String name) {
        images.add(name);
    }

    /**
     * Añade a la lista una respuesta.
     *
     * @param ans Respuesta a añadir
     */
    public void addAnswer(Answer ans) {
        answers.add(ans);
    }
}

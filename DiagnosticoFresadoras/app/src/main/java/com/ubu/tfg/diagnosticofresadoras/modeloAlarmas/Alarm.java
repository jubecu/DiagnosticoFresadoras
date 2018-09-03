package com.ubu.tfg.diagnosticofresadoras.modeloAlarmas;

import java.util.ArrayList;

/**
 * Clase que representa una alarma.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class Alarm {
    /**
     * Número de la alarma
     */
    private long num;
    /**
     * Título y descripción de la alarma
     */
    private String title, desp;
    /**
     * Lista con los nombres de los archivos de las imágenes de la alarma
     */
    private ArrayList<String> images;
    /**
     * Lista con las preguntas de la alarma
     */
    private ArrayList<Question> questions;

    /**
     * Constructor que asigna el número, título y descripción e inicializa los ArrayList.
     *
     * @param num   Número de la alarma
     * @param title Título de la alarma
     * @param desp  Descripción de la alarma
     */
    public Alarm(long num, String title, String desp) {
        this.num = num;
        this.title = title;
        this.desp = desp;
        questions = new ArrayList<>();
        images = new ArrayList<>();
    }

    /**
     * Devuelve el número de la alarma.
     *
     * @return Número de la alarma
     */
    public long getNum() {
        return num;
    }

    /**
     * Devuelve el título de la alarma.
     *
     * @return Título de la alarma
     */
    public String getTitle() {
        return title;
    }

    /**
     * Devuelve la descripción de la alarma.
     *
     * @return Descripción de la alarma
     */
    public String getDescription() {
        return desp;
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
     * Añade a la lista una pregunta.
     *
     * @param ques Pregunta a añadir
     */
    public void addQuestion(Question ques) {
        questions.add(ques);
    }

    /**
     * Devuelve una pregunta de la alarma según su id.
     *
     * @param id Identificador de la pregunta a devolver
     * @return Una pregunta concreta
     */
    public Question getQuestionById(double id) {
        Question questionReturn = null;
        for (Question question : questions) {
            if (question.getId() == id) {
                questionReturn = question;
                break;
            }
        }
        return questionReturn;
    }

    /**
     * Devuelve la primera pregunta de la alarma.
     *
     * @return Primera pregunta de la alarma
     */
    public Question getFirstQuestion() {
        return questions.get(0);
    }
}

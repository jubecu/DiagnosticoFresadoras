package com.ubu.tfg.diagnosticofresadoras.modeloAlarmas;

/**
 * Clase que representa una respuesta.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class Answer {
    /**
     * Identificador de la respuesta
     */
    private String id;
    /**
     * Texto de la respuesta
     */
    private String text;
    /**
     * Id de la pregunta con la que continua. Vale -1.0 en caso de que no haya continuación
     */
    private double next;
    /**
     * Mensaje que se muestra en caso de que no haya continuación
     */
    private String finalMessage;

    /**
     * Constructor que asigna un id, un texto y el id de una pregunta a una respuesta.
     *
     * @param id   Identificador de la respuesta
     * @param text Texto de la respuesta
     * @param next Id de la pregunta con la que continua
     */
    public Answer(String id, String text, double next) {
        this.id = id;
        this.text = text;
        this.next = next;
    }

    /**
     * Devuelve el identificador de la respuesta.
     *
     * @return Identificador de la respuesta
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el mensaje final.
     *
     * @param message Mensaje final
     */
    public void setMessage(String message) {
        finalMessage = message;
    }

    /**
     * Devuelve el mensaje final.
     *
     * @return Mensaje final
     */
    public String getMessage() {
        return finalMessage;
    }

    /**
     * Devuelve el identificador de la pregunta con la que continua.
     *
     * @return Id de la pregunta con la que continua
     */
    public double getNext() {
        return next;
    }

    /**
     * Devuelve el texto de la respuesta.
     *
     * @return Texto de la respuesta
     */
    public String getText() {
        return text;
    }
}

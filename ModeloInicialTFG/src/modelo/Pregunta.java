package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Pregunta {

	private double id;
	private String texto;
	private ArrayList<Respuesta> respuestas;

	public Pregunta(double id, String texto) {
		this.id = id;
		this.texto = texto;
		respuestas = new ArrayList<Respuesta>();
	}

	public void addRespuesta(Respuesta resp) {
		respuestas.add(resp);
	}

	public Respuesta getRespuesta(String id) {
		Respuesta respuestaDevolver = null;
		Iterator<Respuesta> iterador = respuestas.iterator();
		while (iterador.hasNext()) {
			Respuesta respuesta = iterador.next();
			if (respuesta.getId().compareTo(id) == 0) {
				respuestaDevolver = respuesta;
				break;
			}
		}
		return respuestaDevolver;
	}

	public double getId() {
		return id;
	}

	public void lanzarPregunta() {
		System.out.println(id + " " + texto);
		Iterator<Respuesta> iterador = respuestas.iterator();
		while (iterador.hasNext()) {
			Respuesta respuesta = iterador.next();
			System.out.println(respuesta.getId() + ") " + respuesta.getTexto());
		}
	}
}

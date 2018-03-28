package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Alarma {

	private long num;
	private String titulo, desp;
	private ArrayList<Pregunta> preguntas;

	public Alarma(long num, String titulo, String desp) {
		this.num = num;
		this.titulo = titulo;
		this.desp = desp;
		preguntas = new ArrayList<Pregunta>();
	}

	public void addPregunta(Pregunta preg) {
		preguntas.add(preg);
	}

	public Pregunta getPregunta(double id) {
		Pregunta preguntaDevolver = null;
		Iterator<Pregunta> iterador = preguntas.iterator();
		while (iterador.hasNext()) {
			Pregunta pregunta = iterador.next();
			if (pregunta.getId() == id) {
				preguntaDevolver = pregunta;
				break;
			}
		}
		return preguntaDevolver;
	}

	public String toString() {
		return "Alarma " + num + ": " + titulo + "\n" + desp + "\n";
	}
}

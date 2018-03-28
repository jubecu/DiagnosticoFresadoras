package ejecucion;

import modelo.*;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

	private static Alarma alarma;
	private static JSONParser parser;

	public static void main(String[] args) {

		boolean parar = false;
		Scanner sc = new Scanner(System.in);
		parser = new JSONParser();

		try {
			Object objeto = parser.parse(new FileReader("Alarma 631.json"));
			JSONObject jsonObject = (JSONObject) objeto;

			long num = (long) jsonObject.get("Número");
			String titulo = (String) jsonObject.get("Título");
			String desp = (String) jsonObject.get("Descripción");
			alarma = new Alarma(num, titulo, desp);

			JSONArray preguntas = (JSONArray) jsonObject.get("Preguntas");
			Iterator<JSONObject> iteradorPreg = preguntas.iterator();
			while (iteradorPreg.hasNext()) {
				JSONObject preg = iteradorPreg.next();

				double idPreg = (double) preg.get("Id");
				String textoPreg = (String) preg.get("Texto");

				Pregunta pregunta = new Pregunta(idPreg, textoPreg);

				JSONArray respuestas = (JSONArray) preg.get("Respuestas");
				Iterator<JSONObject> iteradorResp = respuestas.iterator();
				while (iteradorResp.hasNext()) {
					JSONObject resp = iteradorResp.next();

					String idResp = (String) resp.get("Id");
					String textoResp = (String) resp.get("Texto");
					double camino = (double) resp.get("Camino");

					Respuesta respuesta = new Respuesta(idResp, textoResp, camino);

					String mensaje = (String) resp.get("Mensaje");
					respuesta.setMensaje(mensaje);

					pregunta.addRespuesta(respuesta);
				}
				alarma.addPregunta(pregunta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(alarma.toString());
		double camino = 1.0;
		while (!parar) {
			alarma.getPregunta(camino).lanzarPregunta();
			String opcion = sc.nextLine();
			Respuesta resp = alarma.getPregunta(camino).getRespuesta(opcion);
			if (resp.getCamino() == 0.0) {
				System.out.println(resp.getMensaje());
				parar = true;
			} else
				camino = resp.getCamino();
		}
		sc.close();
	}

}

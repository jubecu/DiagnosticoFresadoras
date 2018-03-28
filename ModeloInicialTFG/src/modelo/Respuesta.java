package modelo;

public class Respuesta {

	private String id;
	private String texto;
	private double camino;
	private String mensajeFinal;

	public Respuesta(String id, String texto, double camino) {
		this.id = id;
		this.texto = texto;
		this.camino = camino;
	}

	public String getId() {
		return id;
	}

	public void setMensaje(String mensaje) {
		mensajeFinal = mensaje;
	}

	public String getMensaje() {
		return mensajeFinal;
	}

	public double getCamino() {
		return camino;
	}

	public String getTexto() {
		return texto;
	}
}

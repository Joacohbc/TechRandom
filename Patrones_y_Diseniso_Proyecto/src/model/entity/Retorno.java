package model.entity;

public class Retorno {

	private final String mensaje;
	private final Exception error;

	public Retorno(String mensaje, Exception error) {
		super();
		this.mensaje = mensaje;
		this.error = error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Exception getError() {
		return error;
	}

	public boolean isOK() {
		return error == null;
	}

	public boolean isError() {
		return error != null;
	}

	@Override
	public String toString() {
		if (isError()) {
			return "Error: " + mensaje + " : " + error.getMessage();
		} else {
			return mensaje;
		}
	}
}

package Practica101;

public class Monitor {

	private String id;
	private String categoria;
	private String numSerie;
	private String tipo;
	private int pulgadas;
	private double precio;
	
	public Monitor(String id, String categoria, String numSerie, String tipo, int pulgadas, double precio) {
		this.id = id;
		this.categoria = categoria;
		this.numSerie = numSerie;
		this.tipo = tipo;
		this.pulgadas = pulgadas;
		this.precio = precio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getPulgadas() {
		return pulgadas;
	}

	public void setPulgadas(int pulgadas) {
		this.pulgadas = pulgadas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Monitor [id=" + id + ", categoria=" + categoria + ", numSerie=" + numSerie + ", tipo=" + tipo
				+ ", pulgadas=" + pulgadas + ", precio=" + precio + "]";
	}
}

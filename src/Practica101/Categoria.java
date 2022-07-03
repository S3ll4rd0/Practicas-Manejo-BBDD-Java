package Practica101;

public class Categoria {

	private String ID;
	private String nombre;
	private String descripcion;
	
	public Categoria() {}
	
	public Categoria(String ID, String nombre, String descripcion) {
		this.ID = ID;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return this.getID() + "\t" + this.getNombre() + "\t" + this.getDescripcion() + "\n";
	}
}

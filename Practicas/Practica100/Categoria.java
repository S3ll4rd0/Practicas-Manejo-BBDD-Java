package Practica100;

public class Categoria {

	private int ID;
	private String nombre;
	private String descripcion;
	
	public Categoria() {}
	
	public Categoria(int ID, String nombre, String descripcion) {
		this.ID = ID;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
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

package taller2.mod.comportamiento;

public class Ingrediente {
	private String nombre;
	private int costoAdicional;
	private int calorias;
	
	
	public Ingrediente(String nombre, int costoAdicional, int calorias) {
		this.calorias = calorias;
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}
	
	public int getCalorias() 
	{
		return this.calorias;
	}
	public String getNombre() {
		return this.nombre;
	}
	public int getCostoAdicional() {
		return this.costoAdicional;
	}
}

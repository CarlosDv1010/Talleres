package taller2.mod.comportamiento;
import java.util.ArrayList;

public class Combo implements Producto {
	private double descuento;
	private String nombreCombo;
	private int calorias = 0;

	private ArrayList<Producto> productos = new ArrayList();
	
	public Combo(String nombre, double descuento) 
	{
		this.nombreCombo = nombre;
		this.descuento = descuento;
	}
	
	public void agregarItemACombo(Producto itemCombo)
	{
		productos.add(itemCombo);
	}

	@Override
	public boolean equals(Object o) 
	{
		Combo c = (Combo) o;
		if (!getNombre().equals(c.getNombre())) 
		{
			return false;
		}
		return true;
	}
	
	
	public String generarTextoFactura() 
	{
		return "Combo: " + nombreCombo + ": " + getPrecio() + "|	Calorías: " + getCalorias() + "\n";
	}
	
	public int getPrecio()
	{
		int precio = 0;
		for (Producto entry: productos) 
		{
			precio += entry.getPrecio();
		}
		
		return (int) (precio * (1-descuento));
	}

	@Override
	public String getNombre() 
	{
		return nombreCombo;
	}

	@Override
	public int getCalorias() {
		calorias = 0;
		
		for (Producto entry: productos) 
		{
			calorias += entry.getCalorias();
		}
		
		return calorias;
	}
		
}

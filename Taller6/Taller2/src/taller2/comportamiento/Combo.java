package taller2.comportamiento;
import java.util.ArrayList;

public class Combo implements Producto {
	private double descuento;
	private String nombreCombo;
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

	
	public String generarTextoFactura() 
	{
		return "Combo: " + nombreCombo + ": " + getPrecio() + "\n";
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


	public ArrayList<Producto> getProductos() {
		return productos;
	}

	@Override
	public String getNombre() 
	{
		return nombreCombo;
	}
		
}

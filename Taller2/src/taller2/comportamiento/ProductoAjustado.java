package taller2.comportamiento;

import java.util.ArrayList;

public class ProductoAjustado extends ProductoMenu implements Producto {
	
	private Producto producto = null;
	private int excedente = 0;
	private ArrayList<Ingrediente> agregados = new ArrayList();
	private ArrayList<Ingrediente> eliminados = new ArrayList();
	String texto = "Producto Base: " + super.getNombre() + ": " + super.getPrecio() + "\n" ;

	public ProductoAjustado(ProductoMenu base)
	{
		super(base.getNombre(), base.getPrecio());
		this.producto = base;		
	}
	
	public void agregarIngrediente(Ingrediente ingrediente) 
	{
		agregados.add(ingrediente);
	}
		
	public void eliminarIngrediente(Ingrediente ingrediente) 
	{
		eliminados.add(ingrediente);
	}
	
	@Override
	public String getNombre() {
		return super.getNombre();
	}
	
	@Override
	public String generarTextoFactura() {
		if (!agregados.isEmpty()) 
		{
			for (Ingrediente entry: agregados) 
			{
				excedente += entry.getCostoAdicional();
				String nwtexto = "Agregado: " + entry.getNombre() + ": " + entry.getCostoAdicional() + "\n";
				texto= texto.concat(nwtexto);
			}
		}
		
		if (!eliminados.isEmpty()) 
		{
			for (Ingrediente entry: eliminados) 
			{
				texto.concat("Eliminado: " + entry.getNombre() + "\n");
			}
		}
		
		agregados.clear();
		eliminados.clear();
		return texto;
	}
	
	@Override
	public int getPrecio() {
		return super.getPrecio() + excedente;
	}
}

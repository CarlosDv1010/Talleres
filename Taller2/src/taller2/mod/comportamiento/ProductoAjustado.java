package taller2.mod.comportamiento;

import java.util.ArrayList;

public class ProductoAjustado extends ProductoMenu implements Producto {
	
	private int excedente = 0;
	private int excedente_calorias = 0;
	private ArrayList<Ingrediente> agregados = null;
	private ArrayList<Ingrediente> eliminados = new ArrayList();
	String texto = "Producto Base: " + super.getNombre() + ": " + super.getPrecio() + "|	Calorías: " + super.getCalorias() + "\n" ;

	@Override
	public boolean equals(Object o) 
	{
		ProductoAjustado p = (ProductoAjustado) o;
		int index_1 = generarTextoFactura().indexOf("\n");
		int index_2 = p.generarTextoFactura().indexOf("\n");
		if (!generarTextoFactura().substring(index_1).strip().equals(p.generarTextoFactura().substring(index_2).strip())) 
		{
			return false;
		}
		return true;
	}
	
	
	public ProductoAjustado(ProductoMenu base)
	{
		super(base.getNombre(), base.getPrecio(), base.getCalorias());
		agregados = new ArrayList();
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
				System.out.println(entry.getNombre());
				excedente += entry.getCostoAdicional();
				excedente_calorias += entry.getCalorias();
				String nwtexto = "Agregado: " + entry.getNombre() + ": " + entry.getCostoAdicional() + "|	Calorías: " + entry.getCalorias() + "\n";
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
		
		return texto;
	}
		


	
	@Override
	public int getPrecio() {
		return super.getPrecio() + excedente;
	}
	
	@Override
	public int getCalorias() {
		return super.getCalorias() + excedente_calorias;
	}
}

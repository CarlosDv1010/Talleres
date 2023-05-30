package taller6;

import java.util.ArrayList;

import junit.framework.TestCase;
import taller2.comportamiento.Ingrediente;
import taller2.comportamiento.ProductoAjustado;
import taller2.comportamiento.ProductoMenu;

public class ProductoAjustadoTest extends TestCase
{
	private ProductoAjustado producto;
	
	public void escenario()
	{
		ProductoMenu productobase = new ProductoMenu("ProductoBase", 10000);
		producto = new ProductoAjustado(productobase);
		ArrayList<Ingrediente> ings = new ArrayList<Ingrediente>();
		
		for (int i = 0; i < 100; i++)
		{
			Ingrediente ing = new Ingrediente("Ingrediente" + i, (10000 + i * 100));
			ings.add(ing);
			producto.agregarIngrediente(ing);
		}
		
		for (Ingrediente ing: ings)
		{
			if ((ing.getCostoAdicional() % 1000) == 0 )
			{
				producto.eliminarIngrediente(ing);
			}
		}

	}
	
	public void escenario2()
	{
		ProductoMenu productobase = new ProductoMenu("ProductoBase", 10000);
		producto = new ProductoAjustado(productobase);
	}
	
	
	public void testGenerarTextoFactura()
	{
		escenario();
		String texto = "Producto Base: " + producto.getNombre() + ": " + (producto.getPrecio()-producto.getExcedente())  + "\n" ;
		ArrayList<Ingrediente> agregados = producto.getAgregados();
		ArrayList<Ingrediente> eliminados  = producto.getEliminados();
		
		if (!agregados.isEmpty()) 
		{
			for (Ingrediente entry: agregados) 
			{
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
		
		assertEquals(texto, producto.generarTextoFactura());
		
		escenario2();
		
		texto = "Producto Base: " + producto.getNombre() + ": " + (producto.getPrecio()-producto.getExcedente())  + "\n" ;
		assertEquals(texto, producto.generarTextoFactura());

	}
}

package taller6;

import junit.framework.TestCase;
import taller2.comportamiento.ProductoMenu;

public class ProductoMenuTest extends TestCase
{
	private ProductoMenu producto;
	
	public void escenario()
	{
		producto = new ProductoMenu("Producto1", 30000);
	}
	
	public void testGenerarTextoFactura()
	{
		escenario();
		String texto = producto.getNombre() + ": " + producto.getPrecio() + "\n";
		assertEquals(texto, producto.generarTextoFactura());
	}
}

package taller6;

import java.util.ArrayList;

import junit.framework.TestCase;
import taller2.comportamiento.Combo;
import taller2.comportamiento.Producto;
import taller2.comportamiento.ProductoMenu;

public class ComboTest extends TestCase
{
	private Combo combo;
	
	public void escenario()
	{
		combo = new Combo("Combo1", 0.2);
		
	}
	
	public void testAgregarItem()
	{
		escenario();

		ProductoMenu producto = new ProductoMenu("producto1", 15000);

		combo.agregarItemACombo(producto);
		ArrayList<Producto> productos = new ArrayList<Producto>();
		productos.add(producto);
		assertEquals(productos, combo.getProductos()); // assertion
		
		ProductoMenu producto2 = new ProductoMenu("producto2", 30000);
		combo.agregarItemACombo(producto2);
		productos.add(producto2);
		
		assertEquals(productos, combo.getProductos()); // assertion
	}
	
	public void testGenerarTextoFactura()
	{
		escenario();
		ProductoMenu producto = new ProductoMenu("producto1", 15000);
		ProductoMenu producto2 = new ProductoMenu("producto2", 30000);
		
		combo.agregarItemACombo(producto);
		combo.agregarItemACombo(producto2);


		String texto =  "Combo: " + combo.getNombre() + ": " + combo.getPrecio() + "\n";
		assertEquals(texto,combo.generarTextoFactura());
	}
}

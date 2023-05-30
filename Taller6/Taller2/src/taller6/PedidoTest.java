package taller6;

import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import taller2.comportamiento.Ingrediente;
import taller2.comportamiento.Pedido;
import taller2.comportamiento.Producto;
import taller2.comportamiento.ProductoAjustado;
import taller2.comportamiento.ProductoMenu;

public class PedidoTest extends TestCase
{
	private Pedido pedido;
	
	public void escenario()
	{
		pedido = new Pedido("Nombre1", "Dir1");
	}
	
	public void testClase() throws PedidoMuyCaroException, IOException
	{
		escenario();
		ProductoMenu productobase = new ProductoMenu("ProductoBase", 10000);
		ProductoAjustado producto = new ProductoAjustado(productobase);
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
		
		pedido.agregarProducto(producto);
		
		ProductoMenu producto2  = new ProductoMenu("Producto2", 15000);
		pedido.agregarProducto(producto2);
		
		String texto = "------ FACTURA ------\n" + "Nombre: " + pedido.getNombreCliente() + "|	Dirección: " + pedido.getDireccionCliente() + "|	Id Pedido: " + pedido.getIdPedido() + "\n";
		
		int precioNeto = 0;
		for (Producto entry: pedido.getProductos()) 
		{
			precioNeto += entry.getPrecio(); 
			texto = texto.concat(entry.generarTextoFactura());
		}
				
		
		texto = texto.concat("Precio Neto: " + pedido.getPrecioNeto() + "\nValor IVA: " + pedido.getPrecioIVA() + "\n------------------------\n" + "Precio total: " + (pedido.getPrecioNeto() + pedido.getPrecioIVA()) + "\n------------------------\n");
		
		assertEquals(texto, pedido.generarTextoFactura());
		
		String filePath = new File("").getAbsolutePath();	
		String ruta = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/facturaTest.txt");
		pedido.guardarFactura(new File(ruta));
		
		assertEquals(texto, pedido.generarTextoFactura());


	}
	
	@Test
	public void testhrowsPedidoMuyCaroException() throws PedidoMuyCaroException 
	{
		escenario();
		ProductoMenu producto2  = new ProductoMenu("Producto2", 160000);
		assertThrows(PedidoMuyCaroException.class, () -> pedido.agregarProducto(producto2));
	}
}

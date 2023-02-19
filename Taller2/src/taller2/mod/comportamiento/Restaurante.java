package taller2.mod.comportamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.function.ToIntFunction;

public class Restaurante {
	 private ArrayList<Producto> productos = new ArrayList();
	 private ArrayList<Ingrediente> ingredientes = new ArrayList();
	 private ArrayList<Combo> combos = new ArrayList();
	 private ArrayList<Pedido> pedidos = new ArrayList();
	 private ArrayList<Bebida> bebidas = new ArrayList();
	 private static Pedido pedido_en_curso = null;
	 private static int num_facturas = 1;
		
	public Restaurante() 
	{   
		String filePath = new File("").getAbsolutePath();	
		String ruta_menu = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/menu_mod.txt");
		String ruta_combos = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/combos.txt");
		String ruta_ingredientes = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/ingredientes_mod.txt");
		String ruta_bebidas = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/bebidas.txt");
		File archivo_bebidas = new File(ruta_bebidas);
		File archivo_menu = new File(ruta_menu);
		File archivo_combos = new File(ruta_combos);
		File archivo_ingredientes = new File(ruta_ingredientes);
		
		cargarInformacionRestaurante(archivo_ingredientes, archivo_menu, archivo_combos, archivo_bebidas);
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		pedido_en_curso = new Pedido(nombreCliente, direccionCliente);
	}
	
	public void cerrarYGuardarPedido() 
	{
			String filePath = new File("").getAbsolutePath();	
			String ruta = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/factura" + num_facturas + ".txt");
			pedido_en_curso.guardarFactura(new File(ruta));
			num_facturas ++;
	}
	
	public Pedido getPedidoEnCurso() {
		return pedido_en_curso;		
	}
	
	public int getNumPedidos() {
		return pedidos.size();		
	}
	
	public ArrayList<Producto> getMenuBase()
	{
		return productos;
		
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		return ingredientes;
		
	} 
	
	public ArrayList<Combo> getCombos()
	{
		return combos;		
	} 
	
	public ArrayList<Bebida> getBebidas()
	{
		return bebidas;		
	} 
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos, File archivoBebidas) 
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarBebidas(archivoBebidas);
		cargarCombos(archivoCombos);
	} 
		
	
	private void cargarIngredientes(File archivoIngredientes) 
	{		
		try {
			List<String> lines = Files.readAllLines(archivoIngredientes.toPath());
			for (String entry: lines) 
			{
				String[] split = entry.split(";");		

				Ingrediente ingrediente = new Ingrediente(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
				ingredientes.add(ingrediente);
			}				
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void cargarMenu(File archivoMenu) 
	{		
		try {
			List<String> lines = Files.readAllLines(archivoMenu.toPath());
			for (String entry: lines) 
			{
				String[] split = entry.split(";");		

				ProductoMenu producto = new ProductoMenu(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
				productos.add(producto);
			}						
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void cargarBebidas(File archivoBebidas) 
	{		
		try {
			List<String> lines = Files.readAllLines(archivoBebidas.toPath());
			for (String entry: lines) 
			{
				String[] split = entry.split(";");		

				Bebida bebida= new Bebida(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
				bebidas.add(bebida);
			}						
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void cargarCombos(File archivoCombos) 
	{		
		try {
			List<String> lines = Files.readAllLines(archivoCombos.toPath());
			for (String entry: lines) 
			{
				String[] split = entry.split(";");
		        ArrayList<String> Arrlist = new ArrayList<String>();
				
				Combo combo = new Combo(split[0], Double.parseDouble(split[1].substring(0,split[1].length()-1))*0.01);
		        		        
				for (int i = 2; i < split.length; i++)
				{					

					for (Producto producto: productos)
					{
						if (producto.getNombre().equals(split[i])) 
						{
							combo.agregarItemACombo(producto);
						}
					}
					for (Bebida bebida: bebidas)
					{
						if (bebida.getNombre().equals(split[i])) 
						{
							combo.agregarItemACombo(bebida);
						}
					}
					
				}
				combos.add(combo);
			}				
			
			// combos.forEach(value -> System.out.println("Combos:"+value.getNombreCombo()));
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	
}

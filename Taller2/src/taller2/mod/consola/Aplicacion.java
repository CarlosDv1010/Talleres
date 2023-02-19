package taller2.mod.consola;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import taller2.mod.comportamiento.Bebida;
import taller2.mod.comportamiento.Combo;
import taller2.mod.comportamiento.Ingrediente;
import taller2.mod.comportamiento.Pedido;
import taller2.mod.comportamiento.Producto;
import taller2.mod.comportamiento.ProductoAjustado;
import taller2.mod.comportamiento.ProductoMenu;
import taller2.mod.comportamiento.Restaurante;

public class Aplicacion {

	private static Boolean hay_pedido_en_curso = false;
	private static Boolean pedido_cerrado = true;
	Restaurante restaurante = new Restaurante();

	public static void mostrarMenu()
	{
		System.out.println("1. Mostrar menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar nuevo elemento al pedido actual");		
		System.out.println("4. Cerrar pedido actual y guardar factura");
		System.out.println("5. Consultar información de un pedido");
		System.out.println("6. Salir");

		System.out.println("Digite la opción a utilizar: ");
	}	
	
	private int obtenerOpcion() 
	{
		Scanner entrada = new Scanner(System.in);
		int numero = entrada.nextInt();
		return numero;
	}
	
	private static ArrayList<Pedido> todos_los_pedidos = new ArrayList();

	public void ejecutarOpcion(int opcionSeleccionada) 
	{
		Pedido pedido_en_curso = restaurante.getPedidoEnCurso();		
		ArrayList<Producto> productos = restaurante.getMenuBase();
		ArrayList<Combo> combos= restaurante.getCombos();
		ArrayList<Bebida> bebidas= restaurante.getBebidas();
		ArrayList<Ingrediente> ingredientes= restaurante.getIngredientes();
		ArrayList<HashMap<String, String>> mapas = new ArrayList();		
		String filePath = new File("").getAbsolutePath();	
		String ruta_menu = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/menu_mod.txt");
		String ruta_combos = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/combos.txt");
		String ruta_ingredientes = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/ingredientes_mod.txt");
		String ruta_bebidas = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/bebidas.txt");

		BufferedReader reader;
		String[] rutasArchivos ={ruta_menu, ruta_combos, ruta_ingredientes, ruta_bebidas};

		
		for (int i = 0; i < rutasArchivos.length; i++) {	

	
			try 
		
			{
		
				reader = new BufferedReader(new FileReader(rutasArchivos[i]));			
				String line;		
				while ((line = reader.readLine())!= null) 
		
				{
					String[] split = line.split(";");			
					HashMap mapa = new HashMap  <String, String>();			
					mapa.put(split[0], split[1]);			
					mapas.add(mapa);
		
				}									
				reader.close();				
		
			} 
		
						
			catch (IOException e) 				
			{			
				e.printStackTrace();
			}
		}
		
		
		switch (opcionSeleccionada) {
		case 1: 
		{
			
			int i = 1;
			String tipo = "Producto básico";
			String simbolo = "$";
			
			for (HashMap<String, String> entry : mapas) 
			{
			    for (String key : entry.keySet()) 
			    {
			    	try 
			    	{
				    	if (key.equals("combo corral")) 
				    	{
				    		tipo = "Combo";
				    		simbolo = "";
				    		i = 1;
				    	}
				    	else if (key.equals("lechuga"))
				    	{
							tipo = "Ingrediente";
							simbolo = "$";
							i = 1;				    	
				    	}
				    	
				    	else if (key.equals("agua cristal sin gas"))
				    	{
							tipo = "Bebida";
							simbolo = "$";
							i = 1;				    	
				    	}
				    	
				        System.out.println(tipo + "  "+ i + ") " + key.substring(0,1).toUpperCase() + key.substring(1).toLowerCase() + ": " + entry.get(key) + simbolo);
					    i++;
			    	}
				    	catch (Exception e) 
			    	{
						// TODO: handle exception
					}		    	
				}
			}
			    
		}
			break;
		
		
		case 2: 
		{
			if (pedido_cerrado.equals(true)) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Por favor digite su nombre: ");
				String nombre = scanner.nextLine();
				System.out.println("Por favor digite su dirección: ");
				String direccion = scanner.nextLine();
				
				restaurante.iniciarPedido(nombre, direccion);
				pedido_en_curso = restaurante.getPedidoEnCurso(); 
				hay_pedido_en_curso = true;
				System.out.println("Nuevo pedido inicializado con el id: " + pedido_en_curso.getIdPedido());
				pedido_cerrado = false;
			}
			
			else 
			{
				System.out.println("Cierre el pedido anterior primero por favor");
			}
			
			break;

		}
		
		case 3:
		{			
					
				//try {
					ProductoAjustado ajustado = null;
					ProductoMenu producto_agregar = null;

					if (hay_pedido_en_curso)
						{
							Scanner scanner = new Scanner(System.in);						
									
							System.out.println("¿Desea agregar un producto o un combo?");
							System.out.println("1. Producto");
							System.out.println("2. Combo");
							System.out.println("3. Bebida");
								
							int entry_1 = Integer.parseInt(scanner.next());
															
							if (entry_1 == 1) 
							{
								int i = 1;
								for (Producto entry: productos)
								{
									System.out.println(i + ") " + entry.getNombre());
									i++;
								}
								
								HashMap <Integer, Producto> casos = new HashMap<Integer, Producto>()
								{
									{
										for (int i = 0; i < productos.size(); i++) 
										{
											put(i+1, productos.get(i));
										}									
									}
								};
								
								// casos.forEach((key, value) -> System.out.println("key " + key.toString() + "value " + value.getNombre()));
								
								System.out.println("¿Cuál producto desea agregar?");								
								int entry_2 = Integer.parseInt(scanner.next());		
								producto_agregar = (ProductoMenu) casos.get(entry_2);
																
								System.out.println("¿Desea realizar modificaciones al producto?");		
								System.out.println("1. Si");			
								System.out.println("2. No");			

								int entry_3 = Integer.parseInt(scanner.next());
								Boolean seguir_modificando = true;
								ajustado = new ProductoAjustado(producto_agregar);
								
								if (entry_3 == 1) {
									do {
										
										System.out.println("¿Desea agregar o eliminar un ingrediente del producto?");		
										System.out.println("1. Agregar");			
										System.out.println("2. Eliminar");			
	
										int entry_4 = Integer.parseInt(scanner.next());
										
										int k = 1;
										for (Ingrediente entry: ingredientes)
										{
											System.out.println(k + ") " + entry.getNombre());
											k++;
										}
										
										HashMap <Integer, Ingrediente> casos_1 = new HashMap<Integer, Ingrediente>()
										{
											{
												for (int j = 0; j < ingredientes.size(); j++) 
												{
													put(j+1, ingredientes.get(j));
												}									
											}
										};
										
										if (entry_4 == 1) 
										{
											System.out.println("¿Cuál ingrediente desea agregar?");								
											int entry_5 = Integer.parseInt(scanner.next());
											
											Ingrediente agregar = casos_1.get(entry_5);
											ajustado.agregarIngrediente(agregar);									
										}
										else if (entry_4 == 2) 
										{
											System.out.println("¿Cuál ingrediente desea eliminar?");								
											int entry_5 = Integer.parseInt(scanner.next());
											
											Ingrediente eliminar = casos_1.get(entry_5);
											ajustado.eliminarIngrediente(eliminar);			
										}
										
										System.out.println("¿Desea continuar realizando modificaciones al producto?");
										System.out.println("1. Si");
										System.out.println("2. No");
										
										int entry_6 = scanner.nextInt();
										if (entry_6 == 2) 
										{
											seguir_modificando = false;
											pedido_en_curso.agregarProducto(ajustado);							
										
										} 
										
									} while(seguir_modificando.equals(true));
								} 
								
								
								else if (entry_3 == 2) 
								{								
									pedido_en_curso.agregarProducto(producto_agregar);									
								}
								
								break;								
							}
							
							else if (entry_1 == 2)
							{
								int i = 1;
								for (Combo entry: combos)
								{
									System.out.println(i + ") " + entry.getNombre());
									i++;
								}
								
								HashMap <Integer, Combo> casos = new HashMap<Integer, Combo>()
								{
									{
										for (int j = 0; j < combos.size(); j++) 
										{
											put(j+1, combos.get(j));
										}									
									}
								};
								
								System.out.println("¿Cuál combo desea agregar?");								
								int entry_8 = Integer.parseInt(scanner.next());
								
								Combo agregar = casos.get(entry_8);
								pedido_en_curso.agregarProducto(agregar);
								break;
								
								// casos.forEach((key, value) -> System.out.println("key " + key.toString() + "value " + value.getNombre()));
								

							}
							
							else if (entry_1 == 3) 
							{
								int i = 1;
								for (Bebida entry: bebidas)
								{
									System.out.println(i + ") " + entry.getNombre());
									i++;
								}
								
								HashMap <Integer, Bebida> casos = new HashMap<Integer, Bebida>()
								{
									{
										for (int j = 0; j < bebidas.size(); j++) 
										{
											put(j+1, bebidas.get(j));
										}									
									}
								};
								
								System.out.println("¿Cuál bebida desea agregar?");								
								int entry_8 = Integer.parseInt(scanner.next());
								
								Bebida agregar_1 = casos.get(entry_8);
								pedido_en_curso.agregarProducto(agregar_1);
								break;
							}
							
							else 
							{
								System.out.println("Ingrese un valor válido");		
								break;							

							}

					}			
						
						else
					{
						System.out.println("No hay un pedido en curso. Por favor, inicialice al menos un pedido antes");
						break;
					}//}
					/*catch (Exception e) 
					{
						System.out.println(e + " " + String.valueOf(e.getStackTrace()[0].getLineNumber()));
						break;							
					}*/
			}			
		case 4: 
		{
			for (Pedido pedido: todos_los_pedidos) 
			{
				if (pedido.equals(pedido_en_curso)) 
				{
					System.out.println("Ya existe un pedido idéntico. Por favor realice otro pedido");
					break;
				}				
			}
			
			todos_los_pedidos.add(restaurante.getPedidoEnCurso());
			restaurante.cerrarYGuardarPedido();
			pedido_cerrado = true;
			break;
		}
		
		case 5: 
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("El id de un pedido es un número natural mayor o igual a 1. Por ejemplo, el segundo pedido realizado tiene como id \"2\"");
			System.out.println("Por ahora se han creado " + restaurante.getNumPedidos() + " pedidos");
			System.out.println("Digite el id del pedido que quiere consultar: ");
			
			int opcion = scanner.nextInt();
			String ruta_archivo = filePath.concat("/Taller 2 - Hamburguesas_esqueleto/data/factura" + opcion + ".txt");
			try 
			
			{
		
				reader = new BufferedReader(new FileReader(ruta_archivo));			
				String line;		
				while ((line = reader.readLine())!= null) 
		
				{
					System.out.println(line);
				}									
				reader.close();				
		
			} 
		
						
			catch (IOException e) 				
			{			
				e.printStackTrace();
			}
			break;
		}
		
		
		case 6: 
		{
			System.out.println("Fin del programa");
			System.exit(0);
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + opcionSeleccionada);
		}
	}

	
	public static void main(String[] args) 
	{

		while (true) 
		{
			mostrarMenu();
		    Aplicacion app = new Aplicacion();
			int opcion = app.obtenerOpcion();
			app.ejecutarOpcion(opcion);
		}		

	}
}

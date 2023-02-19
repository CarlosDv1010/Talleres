package taller2.comportamiento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	private static int numeroPedidos = 1;
	private int idPedido = 0;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productos = new ArrayList();
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = numeroPedidos;
		numeroPedidos ++;
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) 
	{
		productos.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido() {
		int precioNeto = 0;
		
		for (Producto entry: productos) 
		{
			precioNeto += entry.getPrecio();
		}
		return precioNeto;
	}
	
	private int getPrecioTotalPedido() 
	{		

		return getPrecioNetoPedido() + getPrecioIVAPedido();
	}
	
	private int getPrecioIVAPedido() {
		int precioIVA = 0;
		
		int precioNeto = getPrecioNetoPedido();
		precioIVA = (int) (precioNeto * 0.19);
		
		return precioIVA;
		}
	
	private String generarTextoFactura() 
	{
		String texto = "------ FACTURA ------\n" + "Nombre: " + nombreCliente + "|	Dirección: " + direccionCliente + "|	Id Pedido: " + idPedido + "\n";
		for (Producto entry: productos) 
		{
			texto = texto.concat(entry.generarTextoFactura());
		}
		
		texto = texto.concat("Precio Neto: " + getPrecioNetoPedido() + "\nValor IVA: " + getPrecioIVAPedido() + "\n------------------------\n" + "Precio total: " + getPrecioTotalPedido()+ "\n------------------------\n");
		
		return texto;
	}
	
	public void guardarFactura(File archivo) 
	{
		try 
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
			writer.write(generarTextoFactura());
			writer.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

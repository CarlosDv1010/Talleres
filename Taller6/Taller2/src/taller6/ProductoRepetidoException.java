package taller6;

public class ProductoRepetidoException extends HamburguesaException 
{
	private String productoRepetido;
	private int precio;
	
	public ProductoRepetidoException(String productoRepetido, int precio)
	{
		super("El producto " + productoRepetido + " está repetido en la lista de datos");
		this.productoRepetido = productoRepetido;
		this.precio = precio;
	}
	
	public String getInfoProductoRepetido()
	{
		return "Producto repetido: " + productoRepetido + "|Precio: " + precio;
	}
}

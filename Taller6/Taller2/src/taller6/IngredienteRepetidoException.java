package taller6;

public class IngredienteRepetidoException extends HamburguesaException{
	
	private String ingRepetido;
	
	public IngredienteRepetidoException(String ingredienteRepetido)
	{
		super("El ingrediente " + ingredienteRepetido + " está repetido en la lista de datos");
		this.ingRepetido = ingredienteRepetido;
	}
	
	public String getIngredienteRepetido()
	{
		return ingRepetido;
	}
}

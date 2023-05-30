package taller6;

public abstract class HamburguesaException extends Exception 
{
	public HamburguesaException(String message)
	{
		super("Error en los datos introducidos| " + message);
	}
	
}

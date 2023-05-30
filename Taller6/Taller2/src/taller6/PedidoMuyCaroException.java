package taller6;

public class PedidoMuyCaroException extends Exception
{
	public PedidoMuyCaroException(float valorPedido)
	{
		super("El pedido actual supera el valor máximo de $150000 en $" + (Math.abs(150000-valorPedido)));
	}

}

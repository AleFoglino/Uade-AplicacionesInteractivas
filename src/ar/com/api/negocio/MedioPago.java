/**
 * 
 */
package ar.com.api.negocio;

/**
 * @author Alejandro Foglino
 *
 */
public class MedioPago {

	private int numeroTarjeta;
	private String marca;
	

	public MedioPago(int numeroTarjeta, String marca) {
		super();
		this.setNumeroTarjeta(numeroTarjeta);
		this.setMarca(marca);
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}


	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	
	
}

package tpparadigmas;


public class Usuario {
	
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion preferencia;
	
	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoAtraccion preferencia) 
	{
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
	}

	@Override
	public String toString() {
		return "Nombre: "+this.nombre+
				"\nPresupuesto final: "+this.presupuesto+
				"\nTiempo resultante: "+this.tiempoDisponible+" hs\n";
				
	}

	public int getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempo() {
		return this.tiempoDisponible;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoAtraccion getAtraccionPreferida() {
		return this.preferencia;
	}
	
	public void restarTiempo(double tiempo) {
		this.tiempoDisponible-=tiempo;
	}

	public void restarPresupuesto(double costo) {
		this.presupuesto-=costo;
	}
}

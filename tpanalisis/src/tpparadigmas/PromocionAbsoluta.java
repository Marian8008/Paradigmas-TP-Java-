package tpparadigmas;

public class PromocionAbsoluta extends Promocion {

	private int costoReducido;
	
	public PromocionAbsoluta(String nom, TipoAtraccion tipo, int costoPaquete) {
		super(nom, tipo);
		this.costoReducido = costoPaquete;
	}

	@Override
	public int getCosto() {
		return this.costoReducido;
	}
	
	public String toString() {
		return super.toString()+
				"\n- Costo Reducido:\t$"+this.costoReducido;
	}
}

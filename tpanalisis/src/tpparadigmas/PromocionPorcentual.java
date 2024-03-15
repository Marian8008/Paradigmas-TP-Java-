package tpparadigmas;

public class PromocionPorcentual extends Promocion {

	private double porcentaje; 
	
	public PromocionPorcentual(String nom, TipoAtraccion tipo, double porc) {
		super(nom, tipo);
		this.porcentaje = porc;
	}

	@Override
	public int getCosto() {
		return (int) (this.costo*(1 - this.porcentaje/100));
	}
	
	@Override
	public String toString() {
		return super.toString()+
				"\n- Costo con descuento:  $"+this.getCosto();
	}
}

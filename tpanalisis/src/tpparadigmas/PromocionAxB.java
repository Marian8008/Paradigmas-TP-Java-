package tpparadigmas;

public class PromocionAxB extends Promocion {

	private Atraccion attracionGratis;
	
	public PromocionAxB(String nom, TipoAtraccion tipo, Atraccion att) {
		super(nom, tipo);
		this.attracionGratis = att;
		atracciones.add(att);
		this.tiempo+=att.tiempo;
		this.costo+=att.costo;
	}

	@Override
	public int getCosto() {
		return this.costo - this.attracionGratis.getCosto();
	}
	
	@Override
	public String toString() {
		return super.toString()+
				"\n- Atraccion Gratis:\t"+this.attracionGratis.getNombre()+
				"\n- Costo Final:\t\t$"+this.getCosto();
	}
	
}

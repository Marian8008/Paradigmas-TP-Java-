package tpparadigmas;

import java.util.LinkedList;

public abstract class Promocion extends Producto {

	public static final int PROMO_ABSOLUTA = 1;
	public static final int PROMO_PROCENTUAL = 2;
	public static final int PROMO_AXB = 3;
	
	protected LinkedList<Atraccion> atracciones;
	protected boolean noHayCupo = false;
	
	public Promocion(String nom, TipoAtraccion tipo)
	{
		super(nom, tipo);
		this.atracciones = new LinkedList<Atraccion>();

		this.costo=0;
		this.tiempo=0;
	}

	public void addAtraccion(Atraccion att) {
		this.atracciones.add(att);
		this.costo+= att.costo;
		this.tiempo+=att.tiempo;
	}

	public LinkedList<Atraccion> getAtracciones() {
		return this.atracciones;
	}
	
	public void decrementarCupo() {
		atracciones.forEach((att) -> {
			att.decrementarCupo();
		});
	}
	
	public boolean sinCupo() {
		if(!this.noHayCupo) {
			atracciones.forEach((att) -> {
				if(att.sinCupo())
					this.noHayCupo = true;
			});
		}
		return this.noHayCupo;
	}
	
	public void calcularTiempo() {
		atracciones.forEach((att) -> {
			this.tiempo += att.getTiempo();
		});
	}
	
	@Override
	public boolean hayAtraccionAceptada(Atraccion otraAtraccion) {
		boolean atraccionAceptada=false;
		
		for(Atraccion myAtraccion : this.atracciones) {
			if(myAtraccion.equals(otraAtraccion)) {
				atraccionAceptada=true;
				break;
			}
		}
		return atraccionAceptada;
	}
	
	@Override
	public String toString() {
		String cadena = "\n>>>> PROMOCION "+this.nombre + " <<<<\n"+
				"\n- atracciones:\n";
		for(Atraccion atraccion : atracciones) {
			cadena+= String.format("   Atraccion %-12s\tCupo: %d%n", atraccion.nombre, atraccion.cupo);
		}
				cadena+="\n- Tiempo total:\t\t"+this.tiempo+" hs"+
						"\n- Tipo de Atracciones: "+this.tipoAtraccion+
						"\n- Costo Original:\t$" + costo;
					
				return cadena;
	}
	
}

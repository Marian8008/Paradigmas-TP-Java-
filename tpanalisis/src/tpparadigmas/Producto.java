package tpparadigmas;

public abstract class Producto implements Comparable<Producto> {
	protected String nombre;
	protected int costo;
	protected double tiempo;
	protected TipoAtraccion tipoAtraccion;
	
	public Producto(String nom, TipoAtraccion tipo) {
		this.nombre = nom;
		this.tipoAtraccion = tipo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public double getTiempo() {
		return this.tiempo;
	}
	
	public TipoAtraccion getTipoAtraccion() {
		return this.tipoAtraccion;
	}
	
	public abstract void decrementarCupo();
	
	public abstract boolean sinCupo();
	
	public abstract int getCosto();
	
	public abstract boolean hayAtraccionAceptada(Atraccion otraAtraccion);
	
	@Override
	public int compareTo(Producto otro) {
		
		boolean estoEsPromocion = this instanceof Promocion;
		boolean otroEsPromocion = otro instanceof Promocion;
		
		if(estoEsPromocion ==  otroEsPromocion) {
			int costoComparison = Double.compare(otro.costo,this.costo);
	        if (costoComparison != 0) {
	            return costoComparison;
			} else {
	            return Double.compare(otro.tiempo,this.tiempo);
			}
		} else if(estoEsPromocion) {
			return -1;
		} else return 1;
	}
	
//	@Override
//	public int compareTo(Producto otro) {
//		if(this.getClass().getSuperclass().equals(otro.getClass().getSuperclass())) {
//			if(otro.getCosto() < this.costo) {
//				return -1;
//			} else if(otro.getCosto() > this.costo) {
//				return 1;
//			} else {
//				if(otro.getTiempo() < this.tiempo) {
//					return -1;
//				} else if(otro.getTiempo() > this.tiempo) {
//					return 1;
//				} else return 0;
//			}
//		} else if(this.getClass().getSuperclass().getSimpleName().equals("Promocion")) {
//			return -1;
//		} else return 1;
//		
//	}

	


	@Override
	public String toString() {
		return "Producto [nombre=" + this.nombre + ", costo=" + this.costo + ", tiempo=" + this.tiempo + ", tipoAtraccion="
				+ this.tipoAtraccion + "]";
	}
	
	
	
}

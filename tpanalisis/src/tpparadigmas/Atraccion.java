package tpparadigmas;

import java.util.LinkedList;

public class Atraccion extends Producto {
	
	protected int cupo;
	
	public Atraccion(String nom, int cost, double time, TipoAtraccion tipo, int cup)
	{
		super(nom, tipo);	
		this.nombre = nom;
		this.costo = cost;
		this.tiempo = time;
		this.cupo = cup;
	}
	
	public void decrementarCupo() {
		this.cupo--;
	}
	
	public boolean sinCupo() {
		return cupo == 0;
	}
	
	@Override
	public int getCosto() {
		return this.costo;
	}
	
	public boolean equals(Atraccion otro) {
		return this.nombre == otro.getNombre();
	}
	
	public static Atraccion buscarAtraccionEnLista(String nombreAtr,LinkedList<Atraccion> listaAtracciones) throws Exception
	{
		for(Atraccion atraccion:listaAtracciones)
		{
			if(atraccion.getNombre().equals(nombreAtr))
				return atraccion;
		}
		return null;		
	}
	
	@Override
	public boolean hayAtraccionAceptada(Atraccion otraAtraccion) {
		return this.equals(otraAtraccion);
	}
	
	@Override
	public String toString() {
		return "\nAtraccion "+this.nombre+
				"\n\n- Costo:\t\t$"+this.costo+
				"\n- Tiempo:\t\t"+this.tiempo+" hs"+
				"\n- Tipo de Atraccion:\t"+this.tipoAtraccion+
				"\n- Cupo:\t\t\t"+this.cupo;
	}	
}

package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpparadigmas.Atraccion;
import tpparadigmas.Promocion;
import tpparadigmas.PromocionAbsoluta;
import tpparadigmas.PromocionAxB;
import tpparadigmas.PromocionPorcentual;
import tpparadigmas.SistemaSugerencia;
import tpparadigmas.TipoAtraccion;
import tpparadigmas.Usuario;
import tpparadigmas.Producto;

public class testOfertador {
	
	Atraccion atr1;
	Atraccion atr2;
	Atraccion atr3;
	Atraccion atr4;
	Atraccion atr5;
	Atraccion atr6;
	Atraccion atr7;
	Atraccion atr8;
	
	Promocion promo1;
	Promocion promo2;
	Promocion promo3;
	
	Usuario usuario1;
	
	ArrayList<Atraccion> aceptadas;
	SistemaSugerencia sugerencia;
	
	
	@Before
	public void inicializacion() {
		
		atr1 = new Atraccion("Moria", 10, 2, TipoAtraccion.Aventura, 6);
		atr2 = new Atraccion("Minas tirith", 5, 2, TipoAtraccion.Paisaje, 25);
		atr3 = new Atraccion("La Comarca", 3, 6, TipoAtraccion.Degustacion, 15);
		atr4 = new Atraccion("Mordor", 25, 3, TipoAtraccion.Aventura, 4);
		atr5 = new Atraccion("Abismo de Helm", 5, 2, TipoAtraccion.Paisaje, 1);
		atr6 = new Atraccion("Lothlorien", 35, 1, TipoAtraccion.Degustacion, 30);
		atr7 = new Atraccion("Erebor", 12, 3, TipoAtraccion.Aventura, 3);
		atr8 = new Atraccion("Bosque Negro", 3, 2, TipoAtraccion.Paisaje, 12);
		
		
		promo1 = new PromocionPorcentual("Pack Aventura", TipoAtraccion.Aventura, 10);
		promo1.addAtraccion(atr1);
		promo1.addAtraccion(atr4);
		promo2 = new PromocionAbsoluta("Pack Degustacion", TipoAtraccion.Degustacion, 36);
		promo2.addAtraccion(atr3);
		promo2.addAtraccion(atr6);
		promo3 = new PromocionAxB("Pack Paisaje", TipoAtraccion.Paisaje, atr8);
		promo3.addAtraccion(atr2);
		promo3.addAtraccion(atr5);
		
		
		usuario1 = new Usuario("Julian Alvarez", 100, 9, TipoAtraccion.Paisaje);
		
		aceptadas = new ArrayList<>();
		sugerencia = new SistemaSugerencia();
	}
	
	@Test
	public void testAtraccionesAceptadas() {
		aceptadas.add(atr2);
		
		boolean result1 = sugerencia.sePuedeSugerir(usuario1, atr1, aceptadas);
		boolean result2 = sugerencia.sePuedeSugerir(usuario1, atr2, aceptadas);
		
		Assert.assertEquals(true, result1);
		Assert.assertEquals(false, result2);
	}
	
	@Test
	public void testPromocionesAceptadas() {
		
		aceptadas.addAll(promo2.getAtracciones());
		
		boolean result1 = sugerencia.sePuedeSugerir(usuario1, promo1, aceptadas);
		boolean result2 = sugerencia.sePuedeSugerir(usuario1, promo2, aceptadas);
		
		Assert.assertEquals(true, result1);
		Assert.assertEquals(false, result2);
	}
	
	@Test
	public void testAtraccionSinCupo() {	
		atr5.decrementarCupo();  //tenia un cupo
		
		boolean sinCupo = atr5.sinCupo();
		boolean result = sugerencia.sePuedeSugerir(usuario1, atr5, aceptadas);
		
		Assert.assertEquals(true, sinCupo);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testPromocionSinCupo() {
		promo3.decrementarCupo();  //una atraccion de promo5 tenía un cupo
		
		boolean sinCupo = promo3.sinCupo();
		boolean result = sugerencia.sePuedeSugerir(usuario1, promo3, aceptadas);
		
		Assert.assertEquals(true, sinCupo);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testUsuarioConPocoPresupuesto() {
		usuario1.restarPresupuesto(95);
		
		boolean result = sugerencia.sePuedeSugerir(usuario1, atr1, aceptadas);
		
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testUsuarioConPocoTiempo() {
		usuario1.restarTiempo(8);
		
		boolean result = sugerencia.sePuedeSugerir(usuario1, atr2, aceptadas);
		
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testOrdenarRecomendacionesDegustacion()
	{
		LinkedList<Producto> listaOriginal = new LinkedList<Producto>();
		
		listaOriginal.add(atr1);
		listaOriginal.add(atr2);
		listaOriginal.add(atr3);
		listaOriginal.add(atr4);
		listaOriginal.add(promo2);		//Promo2 es una promocion de tipo degustacion, es la que debe figurar primero
		
		Collections.sort(listaOriginal);
		
		LinkedList<Producto> listaOrdenada = new LinkedList<Producto>();
		
		listaOrdenada.add(promo2);
		listaOrdenada.add(atr3);
		listaOrdenada.add(atr4);
		listaOrdenada.add(atr1);
		listaOrdenada.add(atr2);
			
		
		LinkedList<Producto> ofrecerAUsuariosDegustacion = new LinkedList<Producto>();
		
		ofrecerAUsuariosDegustacion = Stream.concat(listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() == TipoAtraccion.Degustacion),
													listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() != TipoAtraccion.Degustacion)).collect(Collectors.toCollection(LinkedList::new));
		
		
		
		Assert.assertArrayEquals(listaOrdenada.toArray(), ofrecerAUsuariosDegustacion.toArray());		
	}
}

package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpparadigmas.Atraccion;
import tpparadigmas.Promocion;
import tpparadigmas.PromocionAbsoluta;
import tpparadigmas.PromocionAxB;
import tpparadigmas.PromocionPorcentual;
import tpparadigmas.TipoAtraccion;

public class testPaquetes {
	Atraccion atr1;
	Atraccion atr2;
	Atraccion atr3;
	
	@Before
	public void inicializaciones() {
		atr1 = new Atraccion("Minas tirith", 5, 2, TipoAtraccion.Paisaje, 25);
		atr2 = new Atraccion("Abismo de Helm", 5, 2, TipoAtraccion.Paisaje, 1);
		atr3 = new Atraccion("Bosque Negro", 3, 2, TipoAtraccion.Paisaje, 12);

	}
	
	@Test
	public void testCostoAxB() {
		Promocion promo = new PromocionAxB("Pack Paisaje", TipoAtraccion.Paisaje, atr3);
		promo.addAtraccion(atr1);
		promo.addAtraccion(atr2);
		
		int result = promo.getCosto();
		
		Assert.assertEquals(10, result);
	}
	
	@Test
	public void testCostoPorcentual() {
		int porcentaje = 10;
		Promocion promo = new PromocionPorcentual("Pack Aventura", TipoAtraccion.Paisaje, porcentaje);
		promo.addAtraccion(atr1);
		promo.addAtraccion(atr2);
		
		int result = promo.getCosto();
		
		Assert.assertEquals(9, result);
	}
	
	@Test
	public void testCostoAbsoluto() {
		int precioAbsoluto = 8;
		Promocion promo = new PromocionAbsoluta("Pack Degustacion", TipoAtraccion.Paisaje, precioAbsoluto);
		promo.addAtraccion(atr1);
		promo.addAtraccion(atr2);
		
		int result = promo.getCosto();
		
		Assert.assertEquals(precioAbsoluto, result);
	}
}

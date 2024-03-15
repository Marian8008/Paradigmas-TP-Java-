package tests;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import tpparadigmas.Atraccion;
import tpparadigmas.Compra;
import tpparadigmas.Producto;
import tpparadigmas.SistemaSugerencia;
import tpparadigmas.TipoAtraccion;
import tpparadigmas.Usuario;

public class testTotalizadorItinerario {

	@Test
    public void totalizadorDeItinerarios() {

		int presupuestoInicial = 11000;
		double tiempoInicial = 67.8;
		
        Usuario usuario=new Usuario("Lucius", presupuestoInicial, tiempoInicial, TipoAtraccion.Aventura);
        Compra compra = new Compra(usuario);
        ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
        LinkedList<Producto> sugerencias = new LinkedList<Producto>();
        
        Atraccion attr1 = new Atraccion("Demacia", 3000, 40, TipoAtraccion.Aventura, 2);
        Atraccion attr2 = new Atraccion("Shadow Island",2500, 32, TipoAtraccion.Aventura, 1);
        Atraccion attr3 = new Atraccion("Aguas Turbias",2500, 32, TipoAtraccion.Degustacion, 0);
        Atraccion attr4 = new Atraccion("Targon", 8000, 25, TipoAtraccion.Paisaje, 5);

        sugerencias.add(attr1);
        sugerencias.add(attr2);
        sugerencias.add(attr3);
        sugerencias.add(attr4);


        atraccionesAceptadas = SistemaSugerencia.sugerirAlUsuario(usuario, compra, sugerencias);
        SistemaSugerencia.generarItinerario(usuario, compra, atraccionesAceptadas);
        
        Assert.assertTrue(presupuestoInicial >= compra.getCosto());
        Assert.assertTrue(tiempoInicial >= compra.getTiempo());

    }//aceptando Demacia debe saltar a Targon debido a que no hay tiempo suficiente para Shadow Island.
     //Aguas Turbias nunca lo va a ofrecer porque ya está sin cupos.
}

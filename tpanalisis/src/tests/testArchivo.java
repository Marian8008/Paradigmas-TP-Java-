package tests;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import junit.framework.Assert;
import tpparadigmas.Archivo;
import tpparadigmas.Atraccion;
import tpparadigmas.Producto;
import tpparadigmas.Promocion;
import tpparadigmas.TipoAtraccion;
import tpparadigmas.Usuario;

public class testArchivo {


	@Test
	public void abrirArchivoUsuarios() 
	{
		Archivo archUsuarios = new Archivo("Cliente.txt");		
		LinkedList<Usuario> listaUsuarios = archUsuarios.leerArchivoUsuarios();
		
		System.out.println(listaUsuarios);
	}
	
	@Test
	public void obtenerAtracciones() throws Exception 
	{
		LinkedList<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
		
		Atraccion attr1 = new Atraccion("Paraiso", 10, 50, TipoAtraccion.Degustacion, 1);
		Atraccion attr2 = new Atraccion("Tierra", 10, 50, TipoAtraccion.Aventura, 1);
		Atraccion attr3 = new Atraccion("Infierno", 10, 50, TipoAtraccion.Aventura, 1);
		
		listaAtracciones.add(attr1);
		listaAtracciones.add(attr2);
		listaAtracciones.add(attr3);
		
		String aBuscar = "Paraiso";
		 Atraccion encontrada = Atraccion.buscarAtraccionEnLista("Paraiso", listaAtracciones);
		
		 assertEquals(aBuscar,encontrada.getNombre());
	}
		
}

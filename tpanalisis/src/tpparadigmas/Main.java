package tpparadigmas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
		Archivo archAtracciones = new Archivo("Atracciones.txt");		
		LinkedList<Atraccion> listaAtracc = archAtracciones.leerArchivoAtracciones();
		
		Archivo archPromociones = new Archivo("Paquetes.txt");		
		LinkedList<Promocion> listaPromociones = archPromociones.leerArchivoPromociones(listaAtracc);
		
		Archivo archUsuarios = new Archivo("Cliente.txt");		
		LinkedList<Usuario> listaUsuarios = archUsuarios.leerArchivoUsuarios();
		
		LinkedList<Producto> listaOriginal = new LinkedList<Producto>();
		
		listaOriginal.addAll(listaAtracc);
		listaOriginal.addAll(listaPromociones);		
		
		Collections.sort(listaOriginal);
		
		//Listas para mostrar al usuario en base a sus preferencias
		LinkedList<Producto> ofrecerAUsuariosAventura 		= new LinkedList<Producto>();
		LinkedList<Producto> ofrecerAUsuariosPaisaje 		= new LinkedList<Producto>();
		LinkedList<Producto> ofrecerAUsuariosDegustacion 	= new LinkedList<Producto>();
		
		ofrecerAUsuariosAventura = Stream.concat(listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() == TipoAtraccion.Aventura), 
					listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() != TipoAtraccion.Aventura))
					.collect(Collectors.toCollection(LinkedList::new));
		
		ofrecerAUsuariosPaisaje = Stream.concat(listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() == TipoAtraccion.Paisaje),
					listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() != TipoAtraccion.Paisaje))
					.collect(Collectors.toCollection(LinkedList::new));
		
		ofrecerAUsuariosDegustacion = Stream.concat(listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() == TipoAtraccion.Degustacion),
					listaOriginal.stream().filter(elemento -> elemento.getTipoAtraccion() != TipoAtraccion.Degustacion))
					.collect(Collectors.toCollection(LinkedList::new));
		
		
		ArrayList<Compra> compras = new ArrayList<>();
		System.out.println(" ****** Sugerencias Turismo ******\n\n");
		for(Usuario usuario : listaUsuarios) {
			Compra compra = new Compra(usuario);
			
			LinkedList<Producto> sugerencias = SistemaSugerencia.buscarListaSugerenciasSegunTipo(usuario.getAtraccionPreferida(),
							ofrecerAUsuariosPaisaje, ofrecerAUsuariosDegustacion, ofrecerAUsuariosAventura);
			
			ArrayList<Atraccion> atraccionesAceptadas = SistemaSugerencia.sugerirAlUsuario(usuario, compra, sugerencias);
			SistemaSugerencia.generarItinerario(usuario, compra, atraccionesAceptadas);
			compras.add(compra);
		}
		
		Archivo archivoCompras= new Archivo("Compras.txt");
		archivoCompras.guardarArchivoCompras(compras);
	}
}

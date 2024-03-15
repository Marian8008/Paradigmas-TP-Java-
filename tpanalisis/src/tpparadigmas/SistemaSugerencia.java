package tpparadigmas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class SistemaSugerencia {

	public static LinkedList<Producto> buscarListaSugerenciasSegunTipo(TipoAtraccion tipo,
			LinkedList<Producto> sugerenciasPaisaje, LinkedList<Producto> sugerenciasDegustacion,
			LinkedList<Producto> sugerenciasAventura) {
		
		switch (tipo) {
		case Paisaje:
			return sugerenciasPaisaje;
		case Degustacion:
			return sugerenciasDegustacion;
		default:
			return sugerenciasAventura;
		}
	}

	public static ArrayList<Atraccion> sugerirAlUsuario(Usuario usuario, Compra compra, LinkedList<Producto> sugerencias) {
		System.out.println("Nombre del visitante: " + usuario.getNombre());

		ArrayList<Atraccion> atraccionesAceptadas = new ArrayList<>();

		for (Producto sugerencia : sugerencias) {

			if (usuario.getPresupuesto() == 0 || usuario.getTiempo() == 0)
				break;

			if (sePuedeSugerir(usuario, sugerencia, atraccionesAceptadas) == true) {

				mostrarDatosActulizadosUsuario(usuario);
				mostrarSugerencia(sugerencia);

				boolean respuesta = obtenerRespuesta();
				if (respuesta == true) {
					System.out.println("¡Aceptado!");
					procesarCompra(compra, sugerencia, atraccionesAceptadas);
				}
			}
		}

		return atraccionesAceptadas;
	}

	public static boolean sePuedeSugerir(Usuario usuario, Producto sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {
		boolean esAtraccionAceptada = esAtraccionAceptada(sugerencia, atraccionesAceptadas);

		return esAtraccionAceptada == false && usuario.getPresupuesto() >= sugerencia.getCosto()
				&& usuario.getTiempo() >= sugerencia.getTiempo() && sugerencia.sinCupo() == false;
	}

	private static boolean esAtraccionAceptada(Producto sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {

		boolean atraccionAceptada = false;
		for (Atraccion atraccion : atraccionesAceptadas) {
			if (sugerencia.hayAtraccionAceptada(atraccion)) {
				atraccionAceptada = true;
				break;
			}
		}

		return atraccionAceptada;
	}

	private static void mostrarDatosActulizadosUsuario(Usuario usuario) {
		System.out.println("\n\n-----------------------------------------------------------------------");
		System.out.println("Presupuesto actual de " + usuario.getNombre() + ": $" + usuario.getPresupuesto());
		System.out.println("Tiempo disponible de " + usuario.getNombre() + ": " + usuario.getTiempo() + " hs");
		
	}

	private static void mostrarSugerencia(Producto sugerencia) {
		System.out.println("*************************************************************");
		System.out.println(sugerencia.toString());
		System.out.println("\nAcepta la sugerecia? Ingrese S o N");
	}

	private static boolean obtenerRespuesta() {
		char opc;

		do {
			Scanner entrada = new Scanner(System.in);
			opc = entrada.next().charAt(0);

		} while (opc != 's' & opc != 'S' && opc != 'n' && opc != 'N');

		return opc == 's' || opc == 'S';
	}

	private static void procesarCompra(Compra compra, Producto sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {
		guardarAtraccionesAceptadas(sugerencia, atraccionesAceptadas);
		compra.getUsuario().restarTiempo(sugerencia.getTiempo());
		compra.getUsuario().restarPresupuesto(sugerencia.getCosto());

		compra.addSugerenciaDiaria(sugerencia);
		compra.incrementarCostoTotal(sugerencia.getCosto());
		compra.incrementarTiempoTotal(sugerencia.getTiempo());

		sugerencia.decrementarCupo();
	}

	private static void guardarAtraccionesAceptadas(Producto sugerencia, ArrayList<Atraccion> atraccionesAceptadas) {
		if (sugerencia.getClass() == Atraccion.class)
			atraccionesAceptadas.add((Atraccion) sugerencia);
		else
			atraccionesAceptadas.addAll(((Promocion) sugerencia).getAtracciones());
	}

	public static void generarItinerario(Usuario usuario, Compra compra, ArrayList<Atraccion> atraccionesAceptadas) {

		System.out.println("\n------------------------------------------------------------------");
		System.out.println("Itinerario de " + usuario.getNombre());
		for (Atraccion atraccion : atraccionesAceptadas) {
			System.out.printf("-- Atraccion: %-18s\tHoras de atracción:  %.1fhs\n", atraccion.getNombre(),
					atraccion.getTiempo());
		}
		System.out.println("\nHoras Necesarias: " + compra.getTiempo() + " hs\tMonto necesario: $" + compra.getCosto());
		System.out.println("\n------------------------------------------------------------------");
		Scanner entrada = new Scanner(System.in);
		System.out.println("Presione enter...\n\n\n");
		entrada.nextLine();
	}

}

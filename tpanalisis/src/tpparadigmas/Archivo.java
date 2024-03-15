package tpparadigmas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Archivo {

	private String ruta;

	// Constructor
	public Archivo(String ruta) {
		this.ruta = ruta;
	}

	public LinkedList<Atraccion> leerArchivoAtracciones() {
		Scanner scanner = null;
		LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();

		String nombreAtr;
		int costo;
		double tiempo;
		int cupo;
		String nombreTipoAtraccion;

		try {
			File file = new File(this.ruta);
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				nombreAtr = scanner.next();
				costo = scanner.nextInt();
				tiempo = scanner.nextDouble();
				cupo = scanner.nextInt();
				nombreTipoAtraccion = scanner.next();

				Atraccion atraccionAct = new Atraccion(nombreAtr, costo, tiempo,
						TipoAtraccion.asignarTipo(nombreTipoAtraccion), cupo);
				atracciones.add(atraccionAct);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return atracciones;
	}

	public LinkedList<Promocion> leerArchivoPromociones(LinkedList<Atraccion> listaAtracciones) {
		LinkedList<Promocion> listaProds = new LinkedList<Promocion>();
		Scanner scanner = null;
		int tipoPaquete;
		String nombrePaquete;
		String nombreTipoAtraccion;
		int cantAtracciones;
		TipoAtraccion tipo = null;
		String nombreAtrac;
		Atraccion atraccAct = null;

		try {
			File file = new File(this.ruta);
			scanner = new Scanner(file);
			Promocion promoActual = null;

			while (scanner.hasNext()) {
				nombrePaquete = scanner.next();
				tipoPaquete = scanner.nextInt();
				nombreTipoAtraccion = scanner.next();

				tipo = TipoAtraccion.asignarTipo(nombreTipoAtraccion);

				switch (tipoPaquete) {
				case Promocion.PROMO_ABSOLUTA : 
					promoActual = new PromocionAbsoluta(nombrePaquete, tipo, scanner.nextInt());
					break;

				case Promocion.PROMO_PROCENTUAL: 
					promoActual = new PromocionPorcentual(nombrePaquete, tipo, scanner.nextDouble());
					break;

				case Promocion.PROMO_AXB: 
					String nombreAtr = scanner.next();

					atraccAct = Atraccion.buscarAtraccionEnLista(nombreAtr, listaAtracciones);

					promoActual = new PromocionAxB(nombrePaquete, tipo, atraccAct);
					break;
				}

				cantAtracciones = scanner.nextInt();

				for (int i = 0; i < cantAtracciones; i++) {
					nombreAtrac = scanner.next();
					atraccAct = Atraccion.buscarAtraccionEnLista(nombreAtrac, listaAtracciones);
					promoActual.addAtraccion(atraccAct);
				}
				listaProds.add(promoActual);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			scanner.close();
		}
		return listaProds;
	}

	public LinkedList<Usuario> leerArchivoUsuarios() {
		LinkedList<Usuario> colaUsuarios = new LinkedList<Usuario>();

		Scanner scanner = null;
		String nombre;
		int presupuesto;
		double tiempoDisp;
		TipoAtraccion preferencia;
		Usuario usuarioAct;
		try {
			File file = new File(this.ruta);
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				nombre = scanner.next();

				preferencia = TipoAtraccion.asignarTipo(scanner.next());

				presupuesto = scanner.nextInt();

				tiempoDisp = scanner.nextDouble();

				usuarioAct = new Usuario(nombre, presupuesto, tiempoDisp, preferencia);

				colaUsuarios.add(usuarioAct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return colaUsuarios;
	}

	public void guardarArchivoCompras( ArrayList<Compra> compras) {
		
		FileWriter file = null; 
		PrintWriter escritura; 
	
		try{
			file = new FileWriter( this.ruta );
			escritura = new PrintWriter(file); //Referenciamos enviando el obj file donde queremos escribir.
			
			//Ahora sí, escrimos dato por dato:
			for(Compra compra : compras) {
				escritura.println(compra);
			}
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			if( file!= null ) {
				try {
					file.close();
				} catch( IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Archivo de Compras procesado.");
	}
}
	

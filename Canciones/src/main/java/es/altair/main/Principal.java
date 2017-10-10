package es.altair.main;



import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;



import es.altair.bean.Artista;
import es.altair.bean.Cancion;
import es.altair.bean.Estilo;
import es.altair.bean.Pais;
import es.altair.dao.ArtistaDAO;
import es.altair.dao.ArtistaDaoImplJDBC;
import es.altair.dao.CancionDAO;
import es.altair.dao.CancionDaoImplJDBC;

public class Principal {
	
	private static Scanner sc = new Scanner(System.in);
	private static Scanner tecladoLine = new Scanner(System.in);
	private static Scanner tecladoInt = new Scanner(System.in);
	
	private static ArtistaDAO aDAO = new ArtistaDaoImplJDBC();
	
	private static Estilo esti = new Estilo();
	
	private static Pais pp = new Pais();
	
	private static CancionDAO cDAO = new CancionDaoImplJDBC();


	public static void main(String[] args) {
						
       int opcion = 0;
       do {
   			menu();
   			opcion = sc.nextInt();
   			
   			switch (opcion) {
				case 1:
				       anadirDatosGenerales();
					break;
				case 2:
					// ACTUALIZAR EDAD DE UN ARTISTA //
				   listadoArtistas();
				   
				   System.out.println("Seleccione Artista para Actualizar Edad (ID): ");
				   
					int idArt = sc.nextInt();

					Artista artActualizar = aDAO.obtener(idArt);
					
					if(artActualizar != null) {
							   System.out.println("Introduzca la Edad del Artista: ");
				   
					int ctdeEdad = sc.nextInt();
						artActualizar.setEdad(ctdeEdad);
					//	artActualizar.setEdad(artActualizar.getEdad() + ctdeEdad);
						if(aDAO.actualizarArtista(artActualizar))
							System.out.println("Edad Actualizada");
						else
							System.out.println("NO se ha podido Actualizar la Edad");
						}
					else
						System.out.println("No se ha obtenido el Id del Artista");
					break;
				case 3:
						actualizaDuracionDeCanciones(); 
						
					break;
				case 4:
					/// BORRAR ARTISTA ///
				       listadoArtistas();
				       
				       System.out.println("Seleccione Artista para Borrar las Canciones (ID): ");
				       
						int borrado = sc.nextInt();
						
						Artista artBorrar = aDAO.obtener(borrado);
					
						if(artBorrar != null) {
					
							if(aDAO.borrarArtista(borrado))  
								System.out.println("Canciones Borradas");
							else
								System.out.println("NO se ha podido Borrar las Canciones"); 
						}
							else
								System.out.println("No se ha obtenido el Id del Artista");	
						
					break;
				case 5:
						// Listar todos los artistas de un determinado estilo
					  
				    listadoEstilos();
				 
	
					System.out.println("Seleccione Estilo a Mostrar (ID): ");
					int idest = sc.nextInt();
					
					List<Artista> listaArtistasB = aDAO.mostrarArtistasEstilos(idest);
					
					System.out.println("   Nombre 				");
					System.out.println("   ══════════════════════════════════════════");
					for (Artista artista : listaArtistasB) {
						System.out.println("   " + artista.getNombreArtista() + " " + artista.getApellidos());
	
					}
					
					break;
				case 6:
					// Listar todas las canciones de un artista //
					listadoArtistas();
					
					System.out.println("Seleccione Artista a Mostrar sus Canciones (ID): ");
					int idArtCancion = sc.nextInt();
					
					List<Cancion> listaCancionesArtista = aDAO.mostrarCancionesPorArtista(idArtCancion);
					System.out.println("   Canción " +  "				Duración");
					System.out.println("   ══════════════════════════════════════════════");
					for (Cancion canciones : listaCancionesArtista) {
						System.out.println("   " + canciones.getNombreCancion() + " 				" + canciones.getDuracion());
					} 
					
					break;
				case 7:
						// Listado de todos los artistas y sus canciones //
						List<Artista> artistas = null;
						artistas = aDAO.listarTodos();
						for(Artista artista : artistas) {
							System.out.println("   " + artista.getNombreArtista() + " " + artista.getApellidos());
							System.out.println("   ═════════════════════════════════════════════════════════════");
	
							int i = 1;
							List<Cancion> listaCancionesArtista2 = aDAO.mostrarCancionesPorArtista(artista.getIdArtista());
							if(listaCancionesArtista2.isEmpty()) {
								System.out.println("         Ese Artista NO Tiene Canciones Guardadas");
							}
							else {
								for (Cancion canciones : listaCancionesArtista2) {
									System.out.println("          " + i++ + " - " + canciones.getNombreCancion() + "		" + canciones.getDuracion());
								} 
							}
							
							System.out.println();
						} 
				break;
	
			//	default:
			//		break;
				}
	} while (opcion != 0);

			
		sc.close();
	}

	private static void actualizaDuracionDeCanciones() {
		listadoPaises();
		
		System.out.println("Seleccione Pais para Actualizar Duración de Canciones (ID): ");
		int idPaisCancion = sc.nextInt();
		  
		
		Map <Integer, Cancion> cancionesFin = new TreeMap<Integer, Cancion>();
		
		cancionesFin  =  cDAO.ListaActualizar(idPaisCancion);
		int contador = 0;
		
		if(cancionesFin.size() != 0) {
		
			System.out.println();
			
			for (Entry<Integer, Cancion> cancion : cancionesFin.entrySet()){
				Cancion valor = cancion.getValue();
				
				int minute = Integer.parseInt(valor.getDuracion().substring(0,2))  + 1;
				String second = valor.getDuracion().substring(3);
			
				valor.setDuracion(String.format("%02d:", minute) + second);
				
				if(cDAO.actualizarDuracionCanciones(valor, idPaisCancion))
					contador++;
										
			}
		} 
		else
			System.out.println("No se ha obtenido el Id del Pais");
		
		if(contador > 0)
			System.out.println("Duracion Actualizada");
		else
			System.out.println("NO se ha podido Actualizar la Duracion");
	}

	private static void anadirDatosGenerales() {
		
		// Añadir Artista, Canción y Estilo //
		   System.out.println("Introduzca los Datos del Nuevo Artista [ENTER para Cancelar]");
		   System.out.println();
		   System.out.println("Nombre: ");
		   String intNombre = tecladoLine.nextLine();	
		   
		   if(intNombre.trim().length() != 0) {
			   System.out.println("Apellidos: ");
			   String intApellidos = tecladoLine.nextLine();	
			   
			   System.out.println("Edad: ");
			   int intEdad = tecladoInt.nextInt();

			   
			   listadoEstilos();             
				System.out.println("Seleccione Estilo (ID): ");
				int intEstilo = sc.nextInt();
	 
				listadoPaises();
				System.out.println("Seleccione Pais (ID): ");
				int intPais = sc.nextInt();     
				
				Artista art = new Artista(intNombre, intApellidos, intEstilo,intEdad,intPais);
				
				//INSERTAR ARTISTA ///
				if(aDAO.insertarArtista(art)) 
					System.out.println("Artista Insertado");
				else
					System.out.println("Artista NO Insertado, Verificar Datos");
		   }
		   

			
			// INSERTAR CANCION //
			System.out.println();
		       System.out.println("Introduzca los Datos de la Nueva Canción [ENTER para Cancelar]");
		       System.out.println();
			   System.out.println("Nombre: ");
			   String intCancion = tecladoLine.nextLine();	

			   if(intCancion.trim().length() != 0) {
				   System.out.println("Duración (mm:ss): ");
			       String intDuracion = sc.next();
				
					listadoArtistas();
					System.out.println();
					System.out.println("A que Artista Pertenece (ID): ");
					int intArtista = tecladoInt.nextInt(); 
					
					Cancion can = new Cancion(intCancion, intDuracion, intArtista);
	
					if(cDAO.insertarCancion(can)) 
						System.out.println("Cancion Insertada");
					else
						System.out.println("Cancion NO Insertada");  
			   }

			
			
			// INSERTAR ESTILO //
			System.out.println();
			listadoEstilos();
			System.out.println();
		       System.out.println("Introduzca los Datos del Nuevo Estilo [ENTER para Cancelar]");
		       System.out.println();
			   System.out.println("Nombre: ");
			   String Estilo = tecladoLine.nextLine();	
			   if(Estilo.trim().length() != 0) {				
					Estilo est = new Estilo(Estilo);

					if(esti.insertarEstilo(est)) 
						System.out.println("Estilo Insertado");
					else
						System.out.println("Estilo NO Insertado");
			   }

	}

	private static void menu() {
		// Menu Principal
		System.out.println();
		System.out.println("╔════════════════════════════════════════╗");
		System.out.println("║              MENÚ PRINCIPAL            ║");
		System.out.println("╠════════════════════════════════════════╣");
		System.out.println("║ 1) Añadir un artista, canción y estilo ║");		
		System.out.println("║                                        ║");
		System.out.println("║ 2) Actualizar edad de un artista       ║");		
		System.out.println("║                                        ║");
		System.out.println("║ 3) Actualizar duración de canciones    ║");		
		System.out.println("║                                        ║");
		System.out.println("║ 4) Borrar Canciones de un  Artista     ║");
		System.out.println("║                                        ║");		
		System.out.println("║ 5) Listar Artista por Estilo           ║");
		System.out.println("║                                        ║");
		System.out.println("║ 6) Listar Canciones de un Artista      ║");
		System.out.println("║                                        ║");
		System.out.println("║ 7) Mostrar todo                        ║");	
		System.out.println("║________________________________________║");
		System.out.println("║          0) Salir                      ║");
		System.out.println("╚════════════════════════════════════════╝");
	}

	private static void listadoPaises() {
		List<Pais> paises = pp.listarTodos();
		System.out.println("Id " +  "Pais");
		System.out.println("═════════════════════");
		
		for(Pais pais : paises) {
			System.out.println(pais.getIdPais() + " " + pais.getPais());
		}
	}

	private static void listadoEstilos() {
		List<Estilo> estilos = esti.listarTodos();
		System.out.println("Id " +  "Estilo");
		System.out.println("═════════════════════");
		for(Estilo estilo : estilos) {
			System.out.println(estilo.getIdEstilo() + " " + estilo.getEstilo());
		}
	}

	private static void listadoCanciones() {
		List<Cancion> canciones = cDAO.listarTodas();
		System.out.println("Id " +  "Canción");
		System.out.println("═════════════════════");
		for(Cancion cancion : canciones) {
			System.out.println(cancion.getIdCancion() + " " + cancion.getNombreCancion());
		}
	}

	private static void listadoArtistas() {
		List<Artista> artistas = aDAO.listarTodos();
		System.out.println("Id " +  "Nombre del Artista");
		System.out.println("════════════════════════════════════════════════════");
		for(Artista artista : artistas) {
			System.out.println(artista.getIdArtista() + " " + artista.getNombreArtista() + " " + artista.getApellidos());
		}
			
	}	

}

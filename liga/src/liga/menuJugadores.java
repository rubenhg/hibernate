package liga;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controlador.JugadoresController;
import modelos.Jugadore;

public class menuJugadores {
	
	JugadoresController jugadores;
	Main main=new Main();
	
	Scanner sc;
	EntityManager em;
	EntityManagerFactory ent;

	HashMap<Integer, Jugadore> jugadoress;

	public menuJugadores() { 
//		super();
		sc = new Scanner(System.in);
		ent = Persistence.createEntityManagerFactory("liga");
		jugadores = new JugadoresController();
	}

	public void play(int opcion) {

		em = ent.createEntityManager();

		em.getTransaction().begin();

		jugadoress = new HashMap<Integer, Jugadore>();

		try {

			List<Jugadore> resultList2 = em.createQuery("select j from Jugadore j", Jugadore.class).getResultList();
			for (Jugadore next : resultList2) {
				jugadoress.put(next.getIdJugador(), next);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		while (opcion != 0) {

			System.out.println(" 1 para ver jugadores.\n" + " 2 para insertar jugadores.\n" + " 3 para modificar jugadores.\n"
					+ "Pulsa 4 para eliminar jugadores."+ "Pulsa 0 para volver al menu principal.");
			opcion = sc.nextInt();

			switch (opcion) {

			case 1:
				//ver jugadores
				jugadores.getJugadores();

				break;
			case 2:
				//insert jugadores
				
				Jugadore jugador = new Jugadore();
				System.out.println("Escriba el nombre del jugador");
				String nombre=sc.next();
		        jugador.setNombre(nombre);
		        System.out.println("Escriba el apellido del jugador");
				String apellido=sc.next();
		        jugador.setApellido(apellido);
		        System.out.println("Se insertara la fecha actual de este momento");
				Timestamp fecha = new Timestamp(System.currentTimeMillis());
				System.out.println("Fecha: "+ fecha);
				jugador.setFechaAlta(fecha);
		        System.out.println("Agrege la altura del jugador");
				BigDecimal altura=sc.nextBigDecimal();
		        jugador.setAltura(altura);
		        System.out.println("Agrege el id del capitan");
				int idCapitan=sc.nextInt();
				jugador.setIdCapitan(idCapitan);
				System.out.println("Escriba el puesto del jugador");
				String puesto=sc.next();
				jugador.setPuesto(puesto);
				System.out.println("Agrege el salario del jugador");
				int salario=sc.nextInt();
				jugador.setSalario(salario);
		        jugadores.InsertJugador(jugador);
				break;
			case 3:
				//update jugadores
				
				System.out.println("Agrega el ID del jugador a modificar:");
				int idJugador = sc.nextInt();

				em.getTransaction().begin();
				jugador = em.find(Jugadore.class, idJugador);

				System.out.println("Escriba el nombre del jugador");
				String nombreUp=sc.next();
		        jugador.setNombre(nombreUp);
		        System.out.println("Escriba el apellido del jugador");
				String apellidoUp=sc.next();
		        jugador.setApellido(apellidoUp);
		        System.out.println("Se insertara la fecha actual de este momento");
				Timestamp fechaUp = new Timestamp(System.currentTimeMillis());
				System.out.println("Fecha: "+ fechaUp);
				jugador.setFechaAlta(fechaUp);
		        System.out.println("Agrege la altura del jugador");
				BigDecimal alturaUp=sc.nextBigDecimal();
		        jugador.setAltura(alturaUp);
		        System.out.println("Agrege el id del capitan");
				int idCapitanUp=sc.nextInt();
				jugador.setIdCapitan(idCapitanUp);
				System.out.println("Escriba el puesto del jugador");
				String puestoUp=sc.next();
				jugador.setPuesto(puestoUp);
				System.out.println("Agrege el salario del jugador");
				int salarioUp=sc.nextInt();
				jugador.setSalario(salarioUp);
		        jugadores.updateJugador(jugador);

				break;

			case 4:
				 
				System.out.println("Agrega el id del jugador a eliminar:");
				int idJugadorDel = sc.nextInt();
				em.getTransaction().begin();
				jugador = em.find(Jugadore.class, idJugadorDel);
				em.remove(jugador);
				try {
					em.getTransaction().commit();
				} catch (Exception e) {
					System.err.println("Fail");
					e.printStackTrace();
				}
				System.out.println("funciona el delete");
				
				break;
			case 0:
				main.getClass();
				break;

			}
		} 

	}
}

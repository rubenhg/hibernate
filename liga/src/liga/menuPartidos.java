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
import controlador.partidosController;
import modelos.Equipo;
import modelos.Partido;


public class menuPartidos {

	partidosController partidos;
	Main main = new Main();

	Scanner sc;
	EntityManager em;
	EntityManagerFactory ent;

	HashMap<Integer, Partido> partido;

	public menuPartidos() { 
		super();
		sc = new Scanner(System.in);
		ent = Persistence.createEntityManagerFactory("liga");
		partidos = new partidosController();
	}

	public void play(int opcion) {

		em = ent.createEntityManager();

		em.getTransaction().begin();

		partido = new HashMap<Integer, Partido>();

		try {

			List<Partido> resultList2 = em.createQuery("select p from Partidos p", Partido.class).getResultList();
			for (Partido next : resultList2) {
				partido.put(next.getIdPartido(), next);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		while (opcion != 0) {

			System.out.println(
					" 1 para ver partidos.\n" + " 2 para insertar partidos.\n" + " 3 para modificar partidos.\n"
							+ "Pulsa 4 para eliminar partidos." + "Pulsa 0 para volver al menu principal.");
			opcion = sc.nextInt();

			switch (opcion) {

			case 1:
				// ver partidos
				partidos.getPartidos();
				
				break;
			case 2:
				// insert partidos

				Partido partido = new Partido();
				
				System.out.println("Inserte el idLocal");
				int equipo1 =sc.nextInt();
				Equipo local = new Equipo();
				local.setIdEquipo(equipo1);
				
				System.out.println("Inserte el idVisitante");
				int equipo2 = sc.nextInt();
				Equipo visitante = new Equipo();				
				visitante.setIdEquipo(equipo2);
				
				System.out.println("Se insertara la fecha actual de este momento");
				Timestamp fecha = new Timestamp(System.currentTimeMillis());
				System.out.println("Fecha: " + fecha);
				partido.setFecha(fecha);
				System.out.println("Agrege el resultado del partido");
				String resultado = sc.next();
				partido.setResultado(resultado);
				System.out.println("Agrege el numero del arbitro");
				String arbitro = sc.next();
				partido.setArbitro(arbitro);
				partidos.insertPartido(partido);
				break;
			case 3:
				// update partidos

				System.out.println("Agrega el ID partido a modificar:");
				int idPartido = sc.nextInt();

				em.getTransaction().begin();
				partido = em.find(Partido.class, idPartido);
				
				
				System.out.println("Escriba el idLocal");
				int equipo1Up =sc.nextInt();
				Equipo localUp = new Equipo();
				localUp.setIdEquipo(equipo1Up);
				
				System.out.println("Escriba el idVisitante");
				int equipo2Up = sc.nextInt();
				Equipo visitanteUp = new Equipo();				
				visitanteUp.setIdEquipo(equipo2Up);
				
				System.out.println("Se insertara la fecha actual de este momento");
				Timestamp fechaUp = new Timestamp(System.currentTimeMillis());
				System.out.println("Fecha: " + fechaUp);
				partido.setFecha(fechaUp);
				System.out.println("Agrege el resultado del partido");
				String resultadoUp = sc.next();
				partido.setResultado(resultadoUp);
				System.out.println("Agrege el numero del arbitro");
				String arbitroUp = sc.next();
				partido.setArbitro(arbitroUp);
				partidos.updatePartido(partido);
				break;

			case 4:
				//delete partidos
				System.out.println("Agrega el id del partido a eliminar:");
				int idPartidoDel = sc.nextInt();
				em.getTransaction().begin();
				partido = em.find(Partido.class, idPartidoDel);
				em.remove(partido);
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

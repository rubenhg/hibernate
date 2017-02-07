package liga;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import controlador.equiposController;
import modelos.Equipo;

public class menuEquipos {
	equiposController equipos;
	Main main =  new Main();

	Scanner sc;
	EntityManager em;
	EntityManagerFactory ent;

	HashMap<Integer, Equipo> equipo;

	public menuEquipos() { 
		super();
		sc = new Scanner(System.in);
		ent = Persistence.createEntityManagerFactory("liga");
		equipos= new equiposController();
	}

	public void play(int opcion) {

		em = ent.createEntityManager();

		em.getTransaction().begin();

		equipo = new HashMap<Integer, Equipo>();

		try {

			List<Equipo> resultList2 = em.createQuery("select e from Equipo e", Equipo.class).getResultList();
			for (Equipo next : resultList2) {
				equipo.put(next.getIdEquipo(), next);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		while (opcion != 0) {

			System.out.println(
					" 1 para ver equipos.\n" + " 2 para insertar equipos.\n" + " 3 para modificar equipos.\n"
							+ "Pulsa 4 para eliminar equipos." + "Pulsa 0 para volver al menu principal.");
			opcion = sc.nextInt();

			switch (opcion) {

			case 1:
				// ver equipos
				equipos.getEquipo();

				break;
			case 2:
				// insert equipos

				Equipo equipo = new Equipo();
				System.out.println("Escriba el nombre del equipo");
				String nombre = sc.next();
				equipo.setNombre(nombre);
				System.out.println("Escriba la localidad del equipo");
				String ciudad = sc.next();
				equipo.setCiudad(ciudad);
				System.out.println("Escriba la web");
				String web = sc.next();
				equipo.setWeb(web);
				System.out.println("Agrege los puntos del equipo");
				int puntos = sc.nextInt();
				equipo.setPuntos(puntos);
				equipos.insertEquipo(equipo);
				break;
			case 3:
				// update equipos

				System.out.println("Agrega el ID del equipo a modificar:");
				int idEquipo = sc.nextInt();

				em.getTransaction().begin();
				equipo = em.find(Equipo.class, idEquipo);

				System.out.println("Escriba el nombre del equipo");
				String nombreUp = sc.next();
				equipo.setNombre(nombreUp);
				System.out.println("Escriba la localidad del equipo");
				String ciudadUp = sc.next();
				equipo.setCiudad(ciudadUp);
				System.out.println("Agrege la web del equipo");
				String webUp = sc.next();
				equipo.setWeb(webUp);
				System.out.println("Agrege el id del capitan");
				int puntosUp = sc.nextInt();
				equipo.setPuntos(puntosUp);
				equipos.updateEquipo(equipo);

				break;

			case 4:

				System.out.println("Agrega el id del equipo a eliminar:");
				int idEquipoDel = sc.nextInt();
				em.getTransaction().begin();
				equipo = em.find(Equipo.class, idEquipoDel);
				em.remove(equipo);
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

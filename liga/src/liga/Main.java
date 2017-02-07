package liga;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controlador.JugadoresController;
 
public class Main {
	
	
public static void main(String[] args) throws ParseException {
		
		menuJugadores Jmenu = new menuJugadores();
		menuEquipos Emenu = new menuEquipos();
		menuPartidos Pmenu = new menuPartidos();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Seleccione una opcion:/n 1.Jugadores/n2.Equipos/n3.Partidos/n4.Salir de la App");
		int opcion = sc.nextInt();

		switch (opcion) {
		
		case 1:
			System.out.println("Opcion jugadores");
			Jmenu.getClass();
			break;
						
		case 2:
			System.out.println("Opcion equipos");
			Emenu.getClass();
			break;
		case 3:			
			System.out.println("Opcion menu");
			Pmenu.getClass();
			break;
			
		case 0:
			System.exit(0);
			break;
		}

	}

}

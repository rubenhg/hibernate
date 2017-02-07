package modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the equipos database table.
 * 
 */
@Entity
@Table(name="equipos")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")

public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_equipo")
	private int idEquipo;

	private String ciudad;

	private String nombre;

	private int puntos;

	private String web;

	//bi-directional many-to-one association to Jugadore
	@OneToMany(mappedBy="equipoBean", fetch=FetchType.EAGER)
	private List<Jugadore> jugadores;

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="equipo1", fetch=FetchType.EAGER)
	private List<Partido> partidos1;

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="equipo2", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Partido> partidos2;

	public Equipo() {
	}

	public int getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return this.puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public List<Jugadore> getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(List<Jugadore> jugadores) {
		this.jugadores = jugadores;
	}

	public Jugadore addJugadore(Jugadore jugadore) {
		getJugadores().add(jugadore);
		jugadore.setEquipoBean(this);

		return jugadore;
	}

	public Jugadore removeJugadore(Jugadore jugadore) {
		getJugadores().remove(jugadore);
		jugadore.setEquipoBean(null);

		return jugadore;
	}

	public List<Partido> getPartidos1() {
		return this.partidos1;
	}

	public void setPartidos1(List<Partido> partidos1) {
		this.partidos1 = partidos1;
	}

	public Partido addPartidos1(Partido partidos1) {
		getPartidos1().add(partidos1);
		partidos1.setEquipo1(this);

		return partidos1;
	}

	public Partido removePartidos1(Partido partidos1) {
		getPartidos1().remove(partidos1);
		partidos1.setEquipo1(null);

		return partidos1;
	}

	public List<Partido> getPartidos2() {
		return this.partidos2;
	}

	public void setPartidos2(List<Partido> partidos2) {
		this.partidos2 = partidos2;
	}

	public Partido addPartidos2(Partido partidos2) {
		getPartidos2().add(partidos2);
		partidos2.setEquipo2(this);

		return partidos2;
	}

	public Partido removePartidos2(Partido partidos2) {
		getPartidos2().remove(partidos2);
		partidos2.setEquipo2(null);

		return partidos2;
	}
	public String toString() {
        return " id de equipos=" + idEquipo + ", ciudad=" + ciudad
                + ", nombre=" + nombre + ", puntos=" + puntos + ", web="
                + web + "]";
    }    

}
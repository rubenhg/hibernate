package modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the jugadores database table.
 *  
 */
@Entity
@Table(name="jugadores")
@NamedQuery(name="Jugadore.findAll", query="SELECT j FROM Jugadore j")
public class Jugadore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_jugador")
	private int idJugador;

	private BigDecimal altura;

	private String apellido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Column(name="id_capitan")
	private int idCapitan;

	private String nombre;

	private String puesto;

	private int salario;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo")
	private Equipo equipoBean;

	public Jugadore() {
	}

	public int getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public BigDecimal getAltura() {
		return this.altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getIdCapitan() {
		return this.idCapitan;
	}

	public void setIdCapitan(int idCapitan) {
		this.idCapitan = idCapitan;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public int getSalario() {
		return this.salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public Equipo getEquipoBean() {
		return this.equipoBean;
	}

	public void setEquipoBean(Equipo equipoBean) {
		this.equipoBean = equipoBean;
	}
	public String toString() {
        return "id de jugador =" + idJugador + ", nombre=" + nombre + ", apellido=" + apellido            
                + ", altura=" + altura + ", fecha de alta=" + fechaAlta + ", id del capitan="
                + idCapitan + ", puesto=" + puesto+  ", salario=" + salario+ ", equipo=" + equipoBean+"]";
    }    

}
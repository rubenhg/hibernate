package modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the partidos database table.
 * 
 */
@Entity
@Table(name="partidos")
@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_partido")
	private int idPartido;

	private String arbitro;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String resultado;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="elocal")
	private Equipo equipo1;

	//bi-directional many-to-one association to Equipo
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="evisitante")
	private Equipo equipo2;

	public Partido() {
	}

	public int getIdPartido() {
		return this.idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public String getArbitro() {
		return this.arbitro;
	}

	public void setArbitro(String arbitro) {
		this.arbitro = arbitro;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Equipo getEquipo1() {
		return this.equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return this.equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	public String toString() {
        return "id del partido=" + idPartido + ", arbitro=" + arbitro
                + ", fecha=" + fecha + ", resultado=" + resultado + ", equipo1="
                + equipo1 +", equipo2="+equipo2 + "]";
    }    

}
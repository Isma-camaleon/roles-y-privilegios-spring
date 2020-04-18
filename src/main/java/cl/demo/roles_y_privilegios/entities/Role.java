package cl.demo.roles_y_privilegios.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
public class Role implements Serializable {

	private static final long serialVersionUID = -8933379875810486482L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String descripcion;
	private boolean activo;

	public Role(int id) {
		this.id = id;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rol_privilegios", joinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilegio_id", referencedColumnName = "id"))
	private List<Privilegio> privilegios;
}

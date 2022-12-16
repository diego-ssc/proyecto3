package mx.unam.ciencias.myp;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

/**
 * Clase que representa la tabla de proyectos
 * en la base de datos.
 *
 */
@Entity
@Table(name = "proyectos")
public class Proyecto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_proyecto")
    private Integer id;

    private String nombre;

    @ManyToMany(mappedBy = "proyectos", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

    public Integer getIdProyecto() {
        return id;
    }

    public void setIdProyecto(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

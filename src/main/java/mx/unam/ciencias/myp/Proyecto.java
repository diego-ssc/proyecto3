package mx.unam.ciencias.myp;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Clase que representa la tabla de proyectos
 * en la base de datos.
 *
 */
@Entity
@Table(name = "proyectos")
public class Proyecto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer id;

    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(joinColumns = {
            @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto",
                        nullable = false, updatable = false)},
        inverseJoinColumns = {
            @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario",
                        nullable = false, updatable = false)})
    @JsonBackReference
    private Set<Usuario> usuarios = new HashSet<>();

    private String mes;

    private String ano;

    private String descripcion;

    @Transient
    private String cadenaUsuarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCadenaUsuarios() {
        return cadenaUsuarios;
    }

    public void setCadenaUsuarios(String cadenaUsuarios) {
        this.cadenaUsuarios = cadenaUsuarios;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void agregaUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.getProyectos().add(this);
    }

    public void eliminaUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        usuario.getProyectos().remove(this);
    }

    @Override
    public String toString(){
        return this.id.toString();
    }
}

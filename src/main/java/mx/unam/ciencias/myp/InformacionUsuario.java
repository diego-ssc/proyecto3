package mx.unam.ciencias.myp;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;
/**
 * Clase para la autenticación de los usuarios
 * de la red de investigadores.
 *
 */
public class InformacionUsuario implements UserDetails {
    private Usuario usuario;

    public InformacionUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getPerfil().getDescripcion()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return usuario.getNombre() + " " + usuario.getApellido();
    }

    public String getInstitucion() {
        String s = (usuario.getInstitucion()).getNombre();
        return s;
    }

    public String getPerfil() {
        String s = (usuario.getPerfil()).getDescripcion();
        String t = s.substring(0, 1).toUpperCase() + s.substring(1);
        return t;
    }

    public String getEmail() {
        String s = usuario.getEmail();
        return s;
    }

    public String getFechaNacimiento() {
        String s = usuario.getFechaNacimiento();
        return s;
    }

    public boolean hasRole(String roleName) {
        return this.usuario.hasRole(roleName);
    }

    public String getAreaTrabajo() {
        String s = (usuario.getAreaTrabajo()).getDescripcion();
        String t = s.substring(0, 1).toUpperCase() + s.substring(1);
        return t;
    }
}

package Biblioteca.Servicio;

import Biblioteca.Clases.Libro;
import Biblioteca.Clases.Usuario;
import Biblioteca.DAO.DAOUsuario;

import java.util.List;

public class ServicioUsuario {
    DAOUsuario gestion = new DAOUsuario();
    List<Usuario> usuarios = gestion.leerUsuarios();


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void insertarUsuariosSincronizado (Usuario usuario) {
        gestion.insertarUsuario(usuario);
        setUsuarios(gestion.leerUsuarios());
    }

    public void actualizarUsuariosSincronizado (Usuario usuario) {
        gestion.actualizarUsuario(usuario);
        setUsuarios(gestion.leerUsuarios());
    }

    public void eliminarUsuariosSincronizado (Usuario usuario) {
        gestion.eliminarUsuario(usuario);
        setUsuarios(gestion.leerUsuarios());
    }
}

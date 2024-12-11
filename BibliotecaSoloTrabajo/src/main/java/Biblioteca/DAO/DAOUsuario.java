package Biblioteca.DAO;

import Biblioteca.Clases.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {
    EntityManagerFactory emt = Persistence.createEntityManagerFactory("default");
    EntityManager em = emt.createEntityManager();
    EntityTransaction et = em.getTransaction();

    public List<Usuario> leerUsuarios() {
        return (ArrayList<Usuario>) em.createQuery("SELECT u from Usuario u").getResultList();
    }

    public void insertarUsuario(Usuario usuario) {
        et.begin();
        em.persist(usuario);
        et.commit();
    }

    public void actualizarUsuario(Usuario usuario) {
        et.begin();
        em.merge(usuario);
        et.commit();
    }

    public void eliminarUsuario(Usuario usuario) {
        et.begin();
        em.remove(usuario);
        et.commit();
    }
}

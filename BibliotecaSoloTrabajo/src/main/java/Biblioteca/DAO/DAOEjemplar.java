package Biblioteca.DAO;

import Biblioteca.Clases.Ejemplar;
import Biblioteca.Clases.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class DAOEjemplar {
    EntityManagerFactory emt = Persistence.createEntityManagerFactory("default");
    EntityManager em = emt.createEntityManager();
    EntityTransaction et = em.getTransaction();

    public List<Ejemplar> leerEjemplares() {
        return (ArrayList<Ejemplar>) em.createQuery("SELECT e from Ejemplar e").getResultList();
    }

    public Ejemplar getEjemplarDisponible(Libro libro) {
        return (Ejemplar) em.createQuery("SELECT e from Ejemplar e WHERE e.estado = 'Disponible' and e.libroByIsbn = Libro").getSingleResult();
    }

    public void insertarEjemplares(Ejemplar ejemplar) {
        et.begin();
        em.persist(ejemplar);
        et.commit();
    }

    public void actualizarEjemplar(Ejemplar ejemplar) {
        et.begin();
        em.merge(ejemplar);
        et.commit();
    }

    public void eliminarEjemplares(Ejemplar ejemplar) {
        et.begin();
        em.remove(ejemplar);
        et.commit();
    }

}

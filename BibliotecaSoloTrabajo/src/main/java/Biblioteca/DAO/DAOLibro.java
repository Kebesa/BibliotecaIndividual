package Biblioteca.DAO;

import Biblioteca.Clases.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class DAOLibro {
    EntityManagerFactory emt = Persistence.createEntityManagerFactory("default");
    EntityManager em = emt.createEntityManager();
    EntityTransaction et = em.getTransaction();

    public List<Libro> leerLibros() {
        return (ArrayList<Libro>) em.createQuery("SELECT l from Libro l").getResultList();
    }

    public void insertarLibros(Libro libro) {
        et.begin();
        em.persist(libro);
        et.commit();
    }

    public void actualizarLibro(Libro libro) {
        et.begin();
        em.merge(libro);
        et.commit();
    }
}

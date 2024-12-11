package Biblioteca.DAO;

import Biblioteca.Clases.Ejemplar;
import Biblioteca.Clases.Prestamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class DAOPrestamo {
    EntityManagerFactory emt = Persistence.createEntityManagerFactory("default");
    EntityManager em = emt.createEntityManager();
    EntityTransaction et = em.getTransaction();

    public List<Prestamo> leerPrestamos() {
        return (ArrayList<Prestamo>) em.createQuery("SELECT p from Prestamo p").getResultList();
    }

    public void insertarPrestamos(Prestamo prestamo) {
        et.begin();
        em.persist(prestamo);
        et.commit();
    }

    public void borrarPrestamos(Prestamo prestamo) {
        et.begin();
        em.remove(prestamo);
        et.commit();
    }
}

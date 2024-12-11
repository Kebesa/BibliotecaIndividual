package Biblioteca.Servicio;

import Biblioteca.Clases.Ejemplar;
import Biblioteca.Clases.Libro;
import Biblioteca.DAO.DAOEjemplar;

import java.util.List;

public class ServicioEjemplar {
    DAOEjemplar gestion = new DAOEjemplar();
    List<Ejemplar> ejemplares = gestion.leerEjemplares();

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public Ejemplar getEjemplarDisponible(Libro libro) {
        return gestion.getEjemplarDisponible(libro);
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void insertarEjemplaresSincronizado (Ejemplar ejemplar) {
        gestion.insertarEjemplares(ejemplar);
        setEjemplares(gestion.leerEjemplares());
    }

    public void actualizarEjemplares (Ejemplar ejemplar) {
        gestion.actualizarEjemplar(ejemplar);
        setEjemplares(gestion.leerEjemplares());
    }

    public void eliminarEjemplares (Ejemplar ejemplar) {
        gestion.eliminarEjemplares(ejemplar);
        setEjemplares(gestion.leerEjemplares());
    }
}

package Biblioteca.Servicio;

import Biblioteca.Clases.Libro;
import Biblioteca.DAO.DAOLibro;

import java.util.List;

public class ServicioLibro {
    DAOLibro gestion = new DAOLibro();
    List<Libro> libros = gestion.leerLibros();

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


    public void insertarLibrosSincronizado (Libro libro) {
        gestion.insertarLibros(libro);
        setLibros(gestion.leerLibros());
    }

    public void actualizarLibrosSincronizado (Libro libro) {
        gestion.actualizarLibro(libro);
        setLibros(gestion.leerLibros());
    }
}

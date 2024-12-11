package Biblioteca.Servicio;

import Biblioteca.Clases.Prestamo;
import Biblioteca.DAO.DAOPrestamo;

import java.util.List;

public class ServicioPrestamo {
    DAOPrestamo gestion = new DAOPrestamo();

    List<Prestamo> prestamos = gestion.leerPrestamos();

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void insertarPrestamo(Prestamo prestamo) {
        gestion.insertarPrestamos(prestamo);
        setPrestamos(prestamos);
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        gestion.borrarPrestamos(prestamo);
        setPrestamos(prestamos);
    }
}

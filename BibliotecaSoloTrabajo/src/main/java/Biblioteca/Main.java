package Biblioteca;

import Biblioteca.Clases.Ejemplar;
import Biblioteca.Clases.Libro;
import Biblioteca.Clases.Prestamo;
import Biblioteca.Clases.Usuario;
import Biblioteca.Servicio.ServicioEjemplar;
import Biblioteca.Servicio.ServicioLibro;
import Biblioteca.Servicio.ServicioPrestamo;
import Biblioteca.Servicio.ServicioUsuario;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.spi.LocaleServiceProvider;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ServicioUsuario gestionUsuario = new ServicioUsuario();
        ServicioLibro gestionLibro = new ServicioLibro();
        ServicioPrestamo gestionPrestamo = new ServicioPrestamo();
        ServicioEjemplar gestionEjemplar = new ServicioEjemplar();
        boolean sigo = true;
        System.out.println("Dime tu usuario/email");
        String nombre_email = teclado.nextLine();
        System.out.println("Dime tu contraseña");
        String contrasenia = teclado.next();
        for (Usuario usuario : gestionUsuario.getUsuarios()) {
            if ((usuario.getNombre().equals(nombre_email) || usuario.getEmail().equals(nombre_email)) && usuario.getPassword().equals(contrasenia) && usuario.getTipo().equals("normal")){
                for (Prestamo prestamo : gestionPrestamo.getPrestamos()) {
                    if (prestamo.getUsuarioByUsuarioId().equals(usuario)) {
                        System.out.println(prestamo);
                    }
                }
            } else if ((usuario.getNombre().equals(nombre_email) || usuario.getEmail().equals(nombre_email)) && usuario.getPassword().equals(contrasenia) && usuario.getTipo().equals("administrador")) {
                while (sigo) {
                    System.out.println("MENÚ DE USO:" + "\n" +
                            "1 - Registrar Préstamo" + "\n" +
                            "2 - Devolver Préstamo" + "\n" +
                            "3 - Registrar Libro" + "\n" +
                            "4 - Actualizar Libro" + "\n" +
                            "5 - Quitar Libro" + "\n" +
                            "6 - Registrar Ejemplar" + "\n" +
                            "7 - Actualizar Ejemplar" + "\n" +
                            "8 - Quitar Ejemplar" + "\n" +
                            "----------------------" + "\n" +
                            "MENÚ DE ADMINISTRADOR:" + "\n" +
                            "a - Crear usuario" + "\n" +
                            "b - Actualizar usuario" + "\n" +
                            "c - Borrar usuario" + "\n" + "\n" +
                            "* - Salir");
                    switch (teclado.next()) {
                        case "1":
                            int contador = 0;
                            System.out.println("Dime el DNI o NIE del usuario a realizar el préstamo");
                            String DNI3 = teclado.next();
                            System.out.println("Dime el ISBN del ejemplar");
                            String ISBN = teclado.next();
                            for (Usuario usuario1 : gestionUsuario.getUsuarios()) {
                                if (usuario1.getDni().equals(DNI3)) {
                                    for (Libro libro : gestionLibro.getLibros()) {
                                        if (libro.getIsbn().equals(ISBN)) {
                                            for (Ejemplar ejemplar : gestionEjemplar.getEjemplares()) {
                                                if (contador == 0) {
                                                    if (ejemplar.getLibroByIsbn().equals(libro) && ejemplar.getEstado().equals("Disponible")) {
                                                        Prestamo prestamo2 = new Prestamo(usuario1, ejemplar);
                                                        gestionPrestamo.insertarPrestamo(prestamo2);
                                                        ejemplar.setEstado("Prestado");
                                                        gestionEjemplar.actualizarEjemplares(ejemplar);
                                                        contador++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case "2":
                            int contador1 = 0;
                            System.out.println("Dime el DNI o NIE del libro");
                            String DNI4 = teclado.next();
                            System.out.println("Dime el ISBN del ejemplar");
                            String ISBN4 = teclado.next();
                            for (Usuario usuario1 : gestionUsuario.getUsuarios()) {
                                if (usuario1.getDni().equals(DNI4)) {
                                    for (Libro libro : gestionLibro.getLibros()) {
                                        if (libro.getIsbn().equals(ISBN4)) {
                                            if (contador1 == 0) {
                                                for (Ejemplar ejemplar : gestionEjemplar.getEjemplares()) {
                                                    for (Prestamo prestamo : gestionPrestamo.getPrestamos()) {
                                                        if (prestamo.getUsuarioByUsuarioId().equals(usuario1) && prestamo.getEjemplarByEjemplarId().equals(ejemplar)) {
                                                            if (LocalDate.now().isAfter(prestamo.getFechaDevolucion())) {
                                                                usuario1.setPenalizacionHasta(LocalDate.now().plusDays(15));
                                                                gestionUsuario.actualizarUsuariosSincronizado(usuario1);
                                                            } else {
                                                                usuario1.setPenalizacionHasta(null);
                                                                gestionUsuario.actualizarUsuariosSincronizado(usuario1);
                                                            }
                                                            ejemplar.setEstado("Disponible");
                                                            gestionEjemplar.actualizarEjemplares(ejemplar);
                                                            gestionPrestamo.eliminarPrestamo(prestamo);
                                                            contador1++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case "3":
                            System.out.println("Dime el ISBN del libro");
                            String isbn = teclado.next();
                            System.out.println("Dime el titulo del libro");
                            String titulo = teclado.next();
                            System.out.println("Dime el autor del libro");
                            String autor = teclado.next();
                            Libro libro = new Libro(isbn, titulo, autor);
                            Ejemplar ejemplar = new Ejemplar(libro);
                            gestionLibro.insertarLibrosSincronizado(libro);
                            gestionEjemplar.insertarEjemplaresSincronizado(ejemplar);
                            System.out.println("Dime cuántos libros tienes");
                            for (int i = 0; i < teclado.nextInt() - 1; i++) {
                                gestionEjemplar.insertarEjemplaresSincronizado(ejemplar);
                            }
                            break;
                        case "4":
                            System.out.println("Dime el ISBN del libro");
                            String isbn1 = teclado.next();
                            for (Libro libro2 : gestionLibro.getLibros()) {
                                if (libro2.getIsbn().equals(isbn1)) {
                                    System.out.println("¿Qué quieres cambiar?" + "\n" +
                                            "1 - Título" + "\n" +
                                            "2 - Autor" + "\n" +
                                            "* - Salir");
                                    switch (teclado.next()) {
                                        case "1":
                                            System.out.println("Dime el nuevo título");
                                            String titulo1 = teclado.next();
                                            libro2.setTitulo(titulo1);
                                            gestionLibro.actualizarLibrosSincronizado(libro2);
                                            break;
                                        case "2":
                                            System.out.println("Dime el nuevo autor");
                                            String autor1 = teclado.next();
                                            libro2.setAutor(autor1);
                                            gestionLibro.actualizarLibrosSincronizado(libro2);
                                            break;
                                    }
                                }
                            }
                            break;
                        case "5":
                            System.out.println("Dime el ISBN del libro");
                            String isbn2 = teclado.next();
                            for (Libro libro2 : gestionLibro.getLibros()) {
                                if (libro2.getIsbn().equals(isbn2)) {
                                    for (Ejemplar ejemplar1 : gestionEjemplar.getEjemplares()) {
                                        if (ejemplar1.getLibroByIsbn().equals(libro2)) {
                                            gestionEjemplar.eliminarEjemplares(ejemplar1);
                                            for (Prestamo prestamo : gestionPrestamo.getPrestamos()) {
                                                if (prestamo.getEjemplarByEjemplarId().equals(ejemplar1)) {
                                                    gestionPrestamo.eliminarPrestamo(prestamo);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case "6":
                            System.out.println("Dime el ISBN del libro");
                            String isbn3 = teclado.next();
                            for (Libro libro2 : gestionLibro.getLibros()) {
                                if (libro2.getIsbn().equals(isbn3)) {
                                    Ejemplar ejemplar1 = new Ejemplar(libro2);
                                    gestionEjemplar.insertarEjemplaresSincronizado(ejemplar1);
                                }
                            }
                            break;
                        case "7":
                            System.out.println("Dime el ID del préstamo");
                            int id = teclado.nextInt();
                            for (Ejemplar ejemplar1 : gestionEjemplar.getEjemplares()) {
                                if (ejemplar1.getId() == id) {
                                    System.out.println("A qué tipo de estado quieres cambiar el préstamo (Disponible, Dañado o Prestado)");
                                    ejemplar1.setEstado(teclado.next());
                                }
                            }
                            break;
                        case "8":
                            System.out.println("Dime el ID del préstamo");
                            int id1 = teclado.nextInt();
                            for (Ejemplar ejemplar1 : gestionEjemplar.getEjemplares()) {
                                if (ejemplar1.getId() == id1) {
                                    for (Prestamo prestamo : gestionPrestamo.getPrestamos()) {
                                        if (prestamo.getEjemplarByEjemplarId().equals(ejemplar1)) {
                                            gestionPrestamo.eliminarPrestamo(prestamo);
                                            gestionEjemplar.eliminarEjemplares(ejemplar1);
                                        }
                                    }
                                }
                            }
                            break;
                        case "a":
                            System.out.println("Dime el DNI o NIE del usuario");
                            String DNI = teclado.next();
                            teclado.nextLine();
                            System.out.println("Dime el nombre y apellidos del usuario");
                            String nombre = teclado.nextLine();
                            System.out.println("Dime el email del usuario");
                            String email = teclado.next();
                            System.out.println("Dime la contraseña (tiene que ser entre 6 y 16 caracteres)");
                            String contrasenia_introducida = teclado.next();
                            System.out.println("Dime el tipo de usuario que quieres crear (administrador o normal, con la primera letra minúscula)");
                            String tipo = teclado.next();
                            Usuario usuario1 = new Usuario(DNI, nombre, email, contrasenia_introducida, tipo);
                            gestionUsuario.insertarUsuariosSincronizado(usuario1);
                            break;
                        case "b":
                            teclado.nextLine();
                            System.out.println("Dime el DNI o NIE del usuario");
                            String DNI1 = teclado.nextLine();
                            for (Usuario usuario2 : gestionUsuario.getUsuarios()) {
                                if (usuario2.getDni().equals(DNI1)) {
                                System.out.println("¿Qué quieres cambiar?" + "\n" +
                                        "1 - Contraseña" + "\n" +
                                        "2 - Email" + "\n" +
                                        "3 - Tipo" + "\n" +
                                        "* - Salir");
                                switch (teclado.next()) {
                                    case "1":
                                        System.out.println("Dime la nueva contraseña");
                                        String contrasenia1 = teclado.next();
                                        usuario2.setPassword(contrasenia1);
                                        gestionUsuario.actualizarUsuariosSincronizado(usuario2);
                                        break;
                                    case "2":
                                        System.out.println("Dime el nuevo email");
                                        String email1 = teclado.next();
                                        usuario2.setEmail(email1);
                                        gestionUsuario.actualizarUsuariosSincronizado(usuario2);
                                        break;
                                    case "3":
                                        if (usuario2.getTipo().equals("administrador")) {
                                            usuario2.setTipo("normal");
                                        } else {
                                            usuario2.setTipo("administrador");
                                        }
                                        gestionUsuario.actualizarUsuariosSincronizado(usuario2);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            }
                            break;
                        case "c":
                            teclado.nextLine();
                            System.out.println("Dime el DNI del usuario");
                            String DNI2 = teclado.nextLine();
                            for (Usuario usuario3 : gestionUsuario.getUsuarios()) {
                                if (usuario3.getDni().equals(DNI2)) {
                                    if ((!usuario3.getNombre().equals(nombre_email) || !usuario3.getEmail().equals(nombre_email)) && !usuario3.getPassword().equals(contrasenia)) {
                                        gestionUsuario.eliminarUsuariosSincronizado(usuario3);
                                    } else {
                                        throw new RuntimeException("No puedes borrar el usuario con el que has iniciado sesión");
                                    }

                                }
                            }
                            break;
                        default:
                            sigo = false;
                    }
                }
            } else {
            }
        }
    }
}

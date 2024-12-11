package Biblioteca.Clases;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "usuario", schema = "biblioteca", catalog = "")
public class Usuario {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "dni", nullable = false, length = 15)
    private String dni;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "tipo", nullable = false)
    private String tipo;
    @Basic
    @Column(name = "penalizacionHasta", nullable = true)
    private LocalDate penalizacionHasta;

    public boolean ValidarDNI(String dni) {
        String[] letras = dni.split("");
        String[] dni_letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        if (letras.length != 9){
            return false;
        }
        if (Character.isLetter(letras[0].charAt(0))){
            int numeros = Integer.parseInt(dni.substring(1, 8));
            if (letras[0].equals("X")) {
                if (letras[8].equals(dni_letras[numeros % 23])){
                    return true;
                } else
                    return false;
            } else if (letras[0].equals("Y")) {
                String numeros_actualizado = "1" + String.valueOf(numeros);
                if (letras[8].equals(dni_letras[Integer.parseInt(numeros_actualizado) % 23])){
                    return true;
                } else
                    return false;
            } else if (letras[0].equals("Z")) {
                String numeros_actualizado = "2" + String.valueOf(numeros);
                if (letras[8].equals(dni_letras[Integer.parseInt(numeros_actualizado) % 23])){
                    return true;
                } else
                    return false;
            } else
                return false;
        } else {
            int numeros = Integer.parseInt(dni.substring(0, 8));
            if (letras[8].equals(dni_letras[numeros % 23])) {
                return true;
            } else
                return false;
        }
    }

    public Usuario(String dni, String nombre, String email, String password, String tipo) {
        if(ValidarDNI(dni))
            this.dni = dni;
        else
            throw new RuntimeException("El DNI está mal escrito");
        this.nombre = nombre;
        if (email.contains("@"))
            this.email = email;
        else
            throw new RuntimeException("El email está mal escrito");
        if (password.toCharArray().length >= 6 && password.toCharArray().length <= 16)
            this.password = password;
        else
            throw new RuntimeException("La contraseña tiene que ser entre 6 y 16 caracteres");
        if (tipo.equals("normal") || tipo.equals("administrador"))
            this.tipo = tipo;
        else
            throw new RuntimeException("El usuario solo puede ser normal o administrador");
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPenalizacionHasta() {
        return penalizacionHasta;
    }

    public void setPenalizacionHasta(LocalDate penalizacionHasta) {
        this.penalizacionHasta = penalizacionHasta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario that = (Usuario) o;

        if (id != that.id) return false;
        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (penalizacionHasta != null ? !penalizacionHasta.equals(that.penalizacionHasta) : that.penalizacionHasta != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (penalizacionHasta != null ? penalizacionHasta.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                ", penalizacionHasta=" + penalizacionHasta +
                '}';
    }
}

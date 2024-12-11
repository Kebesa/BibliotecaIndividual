package Biblioteca.Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "ejemplar", schema = "biblioteca", catalog = "")
public class Ejemplar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "estado", nullable = true)
    private String estado;
    @ManyToOne
    @JoinColumn(name = "isbn", referencedColumnName = "isbn", nullable = false)
    private Libro libroByIsbn;

    public Ejemplar(Libro libroByIsbn) {
        this.libroByIsbn = libroByIsbn;
        this.estado = "Disponible";
    }

    public Ejemplar(String estado, Libro libroByIsbn) {
        if (estado.equals("Disponible") || estado.equals("Prestado") || estado.equals("Dañado"))
            this.estado = estado;
        else
            throw new RuntimeException("El estado solo puede ser disponible, prestado y dañado");
        this.libroByIsbn = libroByIsbn;
    }

    public Ejemplar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ejemplar that = (Ejemplar) o;

        if (id != that.id) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    public Libro getLibroByIsbn() {
        return libroByIsbn;
    }

    public void setLibroByIsbn(Libro libroByIsbn) {
        this.libroByIsbn = libroByIsbn;
    }


    @Override
    public String toString() {
        return "EjemplarEntity{" +
                "id=" + id +
                ", estado=" + estado +
                ", libroByIsbn=" + libroByIsbn +
                '}';
    }
}

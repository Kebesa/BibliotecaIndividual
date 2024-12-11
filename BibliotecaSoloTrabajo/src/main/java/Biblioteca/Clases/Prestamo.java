package Biblioteca.Clases;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestamo", schema = "biblioteca", catalog = "")
public class Prestamo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;
    @Basic
    @Column(name = "fechaDevolucion", nullable = true)
    private LocalDate fechaDevolucion;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuarioByUsuarioId;
    @ManyToOne
    @JoinColumn(name = "ejemplar_id", referencedColumnName = "id", nullable = false)
    private Ejemplar ejemplarByEjemplarId;

    public Prestamo(Usuario usuarioByUsuarioId, Ejemplar ejemplarByEjemplarId) {
        this.fechaInicio = LocalDate.now();
        this.fechaDevolucion = this.fechaInicio.plusDays(15);
        if (usuarioByUsuarioId.getPenalizacionHasta() == null)
            this.usuarioByUsuarioId = usuarioByUsuarioId;
        else
            throw new RuntimeException("No se puede crear un préstamo si el usuario tiene una penalización activa");
        if (!ejemplarByEjemplarId.getEstado().equals("Prestado"))
            this.ejemplarByEjemplarId = ejemplarByEjemplarId;
        else
            throw new RuntimeException("El ejemplar ya está prestado");
    }

    public Prestamo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prestamo that = (Prestamo) o;

        if (id != that.id) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaDevolucion != null ? !fechaDevolucion.equals(that.fechaDevolucion) : that.fechaDevolucion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaDevolucion != null ? fechaDevolucion.hashCode() : 0);
        return result;
    }

    public Usuario getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(Usuario usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public Ejemplar getEjemplarByEjemplarId() {
        return ejemplarByEjemplarId;
    }

    public void setEjemplarByEjemplarId(Ejemplar ejemplarByEjemplarId) {
        this.ejemplarByEjemplarId = ejemplarByEjemplarId;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaDevolucion=" + fechaDevolucion +
                ", usuarioByUsuarioId=" + usuarioByUsuarioId +
                ", ejemplarByEjemplarId=" + ejemplarByEjemplarId +
                '}';
    }
}

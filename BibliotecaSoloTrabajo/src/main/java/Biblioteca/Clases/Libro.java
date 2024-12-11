package Biblioteca.Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "libro", schema = "biblioteca", catalog = "")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;
    @Basic
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    @Basic
    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Libro that = (Libro) o;

        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (autor != null ? !autor.equals(that.autor) : that.autor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LibroEntity{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}

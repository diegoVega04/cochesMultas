package diego.cochesmultas.Clases;

import javafx.fxml.FXML;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="multas")
public class Multa {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_multa")
    private Long id_multa;

    @Column(name="precio")
    private double precio;

    @Column(name="fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    private Coche coche;

    public Multa(){}

    public Multa(double precio, LocalDate fecha, Coche coche) {
        this.precio = precio;
        this.fecha = fecha;
        this.coche = coche;
    }

    public double getPrecio() {
        return precio;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getId_multa() {
        return id_multa;
    }
}

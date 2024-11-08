package diego.cochesmultas.Clases;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="coches")

public class Coche {

    @Id
    @Column(name="matricula")
    private String matricula;
    @Column(name="marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column (name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Multa> multas;

    public Coche() {}

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Coche{ " + matricula + " " + marca + " " + modelo + " (" + tipo + ")}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return Objects.equals(matricula, coche.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

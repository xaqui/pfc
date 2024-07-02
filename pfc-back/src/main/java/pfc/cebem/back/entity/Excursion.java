package pfc.cebem.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "excursion")
public class Excursion {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="excursionId", unique = true, nullable = false)
    private long excursionId;
    
    @NotBlank
    @Column(name="ciudad")
    private String ciudad;

    @NotBlank
    @Column(name="descripcion")
    private String descripcion;

    @NotNull
    @Column(name="fechaInicio")
    private LocalDateTime fechaInicio;

    @Positive
    @Column(name="precio")
    private double precio;

    @Positive
    @Column(name="maxNumPlazas")
    private int maxNumPlazas;
    
    @NotNull
    @Column(name="fechaAlta", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaAlta;
    
    @Positive
    @Column(name="numPlazas")
    private int numPlazas;

    public Excursion() {
    }

    public Excursion(Long excursionId, String ciudad, String descripcion, LocalDateTime fechaInicio, double precio, int maxNumPlazas, LocalDateTime fechaAlta, int numPlazas) {
        setExcursionId(excursionId);
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.precio = precio;
        this.maxNumPlazas = maxNumPlazas;
        this.fechaAlta = fechaAlta;
        this.numPlazas = numPlazas;
    }
    
   
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public int getMaxNumPlazas() {
        return maxNumPlazas;
    }

    public Long getExcursionId () {
        return excursionId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = (excursionId == null ? -1L : excursionId);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excursion excursion = (Excursion) o;
        return excursionId == excursion.excursionId && Double.compare(excursion.precio, precio) == 0 && maxNumPlazas == excursion.maxNumPlazas && numPlazas == excursion.numPlazas && Objects.equals(ciudad, excursion.ciudad) && Objects.equals(descripcion, excursion.descripcion) && Objects.equals(fechaInicio, excursion.fechaInicio) && Objects.equals(fechaAlta, excursion.fechaAlta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(excursionId, ciudad, descripcion, fechaInicio, precio, maxNumPlazas, fechaAlta, numPlazas);
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio( double precio) { this.precio = precio; }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas)  {this.numPlazas = numPlazas; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setMaxNumPlazas(int maxNumPlazas) {
        this.maxNumPlazas = maxNumPlazas;
    }

}

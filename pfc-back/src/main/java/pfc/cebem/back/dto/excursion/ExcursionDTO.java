package pfc.cebem.back.dto.excursion;

import java.time.LocalDateTime;

public class ExcursionDTO {

    private Long excursionId;
    private String ciudad;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private double precio;
    private int maxNumPlazas;
    private LocalDateTime fechaAlta;
    private int numPlazas;

    public Long getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getMaxNumPlazas() {
        return maxNumPlazas;
    }

    public void setMaxNumPlazas(int maxNumPlazas) {
        this.maxNumPlazas = maxNumPlazas;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }
}
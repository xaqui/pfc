package pfc.cebem.back.dto.reserva;

import java.time.LocalDateTime;


public class ReservaSummaryDTO {
    private ReservaDTO reserva;
    private String ciudad;
    private LocalDateTime fechaInicio;

    public ReservaDTO getReserva() {
        return reserva;
    }
    public void setReserva(ReservaDTO reserva) {
        this.reserva = reserva;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
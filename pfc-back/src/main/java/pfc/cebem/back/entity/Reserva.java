package pfc.cebem.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservaId", unique = true, nullable = false)
    private Long reservaId;

    @ManyToOne
    @JoinColumn(name = "excursionId", nullable = false)
    private Excursion excursion;

    @Email
    @NotNull
    @Column(name="email")
    private String email;

    @Column(name="fechaCancelacion")
    private LocalDateTime fechaCancelacion;

    @Positive
    @Column(name="numPlazas")
    private int numPlazas;

    @Positive
    @Column(name="precio")
    private double precio;

    @NotNull
    @Column(name="fechaReserva")
    private LocalDateTime fechaReserva;

    // Getters and Setters
    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    @Override
    public String toString() {
        return "Reserva [reservaId=" + reservaId + ", excursion=" + excursion + ", email=" + email
                + ", fechaCancelacion=" + fechaCancelacion +
                + numPlazas + ", precio=" + precio + ", fechaReserva=" + fechaReserva + "]";
    }

    
}

package pfc.cebem.back.service.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.entity.Reserva;
import pfc.cebem.back.exception.ActionAlreadyDoneException;
import pfc.cebem.back.exception.ExcursionAlreadyStartedException;
import pfc.cebem.back.exception.NotEnoughSeatsException;
import pfc.cebem.back.exception.ResourceNotFoundException;
import pfc.cebem.back.exception.ValidationException;
import pfc.cebem.back.repository.ExcursionRepository;
import pfc.cebem.back.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ExcursionRepository excursionRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ExcursionRepository excursionRepository) {
        this.reservaRepository = reservaRepository;
        this.excursionRepository = excursionRepository;
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> getReservasByEmail(String email) {
        return reservaRepository.findByEmail(email);
    }

    public Reserva createReserva(Reserva reserva) {
        Long excursionId = reserva.getExcursion().getExcursionId();
        Excursion excursion = excursionRepository.findById(excursionId)
                .orElseThrow(() -> new ResourceNotFoundException("Excursion not found with id " + excursionId));

        if (reserva.getFechaReserva().isAfter(excursion.getFechaInicio())) {
            throw new ExcursionAlreadyStartedException("Reservation date cannot be after excursion start date");
        }

        if (excursion.getNumPlazas() < reserva.getNumPlazas()) {
            throw new NotEnoughSeatsException("Not enough available seats for the excursion");
        }

        if (reserva.getNumPlazas() <= 0) {
            throw new ValidationException("Number of seats must be greater than 0");
        }

        excursion.setNumPlazas(excursion.getNumPlazas() - reserva.getNumPlazas());
        excursionRepository.save(excursion);

        reserva.setPrecio(excursion.getPrecio() * reserva.getNumPlazas());

        return reservaRepository.save(reserva);
    }

    public Reserva cancelReserva(Long id) {
        Reserva reserva = getReservaById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reserva not found with id " + id));

        // Reservation is already cancelled
        if (reserva.getFechaCancelacion() != null) {
            throw new ActionAlreadyDoneException("Reservation is already cancelled.");
        }
        // Reservation is for an excursion that has already started
        if (reserva.getExcursion().getFechaInicio().isBefore(LocalDateTime.now())) {
            throw new ExcursionAlreadyStartedException("Reservation is for an excursion that has already started.");
        }

        reserva.setFechaCancelacion(LocalDateTime.now());
        return updateReserva(reserva);
    }

    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    // Not used by application
    public Reserva updateReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
}
package pfc.cebem.back.controller.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pfc.cebem.back.dto.reserva.ReservaCreationDTO;
import pfc.cebem.back.dto.reserva.ReservaDTO;
import pfc.cebem.back.dto.reserva.ReservaGetByUserDTO;
import pfc.cebem.back.dto.reserva.ReservaSummaryDTO;
import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.entity.Reserva;
import pfc.cebem.back.exception.ActionAlreadyDoneException;
import pfc.cebem.back.exception.ExcursionAlreadyStartedException;
import pfc.cebem.back.exception.NotEnoughSeatsException;
import pfc.cebem.back.exception.ResourceNotFoundException;
import pfc.cebem.back.service.reserva.ReservaService;
import pfc.cebem.back.service.excursion.ExcursionService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ExcursionService excursionService;

    @Autowired
    public ReservaController(ReservaService reservaService, ExcursionService excursionService) {
        this.reservaService = reservaService;
        this.excursionService = excursionService;
    }

    @PostMapping("/user-reservations")
    public List<Reserva> getReservasByEmail(@RequestBody ReservaGetByUserDTO reservaEmailDTO) {
        return reservaService.getReservasByEmail(reservaEmailDTO.getEmail());
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<ReservaSummaryDTO> getReservaSummary(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.getReservaById(id);
        if (reserva.isPresent()) {
            ReservaSummaryDTO summary = new ReservaSummaryDTO();

            ReservaDTO reservaDTO = new ReservaDTO();
            reservaDTO.setReservaId(reserva.get().getReservaId());
            reservaDTO.setExcursionId(reserva.get().getExcursion().getExcursionId());
            reservaDTO.setEmail(reserva.get().getEmail());
            reservaDTO.setFechaCancelacion(reserva.get().getFechaCancelacion());
            reservaDTO.setNumPlazas(reserva.get().getNumPlazas());
            reservaDTO.setPrecio(reserva.get().getPrecio());
            reservaDTO.setFechaReserva(reserva.get().getFechaReserva());
            summary.setReserva(reservaDTO);

            Excursion excursion = reserva.get().getExcursion();
            summary.setCiudad(excursion.getCiudad());
            summary.setFechaInicio(excursion.getFechaInicio());

            return ResponseEntity.ok(summary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createReserva(@Valid @RequestBody ReservaCreationDTO reservaCreationDTO) {
        Reserva reserva = new Reserva();
        Excursion excursion = new Excursion();
        excursion.setExcursionId(reservaCreationDTO.getExcursionId());
        reserva.setExcursion(excursion);
        reserva.setEmail(reservaCreationDTO.getEmail());
        reserva.setFechaCancelacion(null);
        reserva.setNumPlazas(reservaCreationDTO.getNumPlazas());
        reserva.setFechaReserva(LocalDateTime.now());

        try {
            Reserva createdReserva = reservaService.createReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReserva);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ExcursionAlreadyStartedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (NotEnoughSeatsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReserva(@PathVariable Long id) {
        try {
            Reserva updatedReserva = reservaService.cancelReserva(id);
            return ResponseEntity.ok(updatedReserva);
        } catch (ActionAlreadyDoneException e) {
            return ResponseEntity.ok(e.getMessage());
        } catch (ExcursionAlreadyStartedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Unused by app
    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    // Unused by app
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.getReservaById(id);
        return reserva.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Unused by app
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @Valid @RequestBody ReservaDTO reservaDTO) {
        Excursion excursion = excursionService.getExcursionById(reservaDTO.getExcursionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Excursion not found with id " + reservaDTO.getExcursionId()));

        Reserva reserva = new Reserva();
        reserva.setReservaId(id);
        reserva.setExcursion(excursion);
        reserva.setEmail(reservaDTO.getEmail());
        reserva.setFechaCancelacion(reservaDTO.getFechaCancelacion());
        reserva.setNumPlazas(reservaDTO.getNumPlazas());
        reserva.setPrecio(reservaDTO.getPrecio());
        reserva.setFechaReserva(reservaDTO.getFechaReserva());

        Reserva updatedReserva = reservaService.updateReserva(reserva);
        return ResponseEntity.ok(updatedReserva);
    }

    // Unused by app
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}

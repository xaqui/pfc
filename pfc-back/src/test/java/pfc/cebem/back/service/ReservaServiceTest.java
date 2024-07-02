package pfc.cebem.back.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.entity.Reserva;
import pfc.cebem.back.exception.ExcursionAlreadyStartedException;
import pfc.cebem.back.exception.NotEnoughSeatsException;
import pfc.cebem.back.exception.ValidationException;
import pfc.cebem.back.repository.ExcursionRepository;
import pfc.cebem.back.repository.ReservaRepository;
import pfc.cebem.back.service.reserva.ReservaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ReservaServiceTest {

    @Autowired
    private ReservaService reservaService;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private ExcursionRepository excursionRepository;

    private Reserva reserva;
    private Excursion excursion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        excursion = new Excursion(1L, "City", "Description", LocalDateTime.now().plusDays(2), 100.0, 10,
                LocalDateTime.now(), 10);
        reserva = new Reserva();
        reserva.setExcursion(excursion);
        reserva.setEmail("test@example.com");
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setNumPlazas(5);
        reserva.setPrecio(100.0);
    }

    @Test
    void testCreateReserva() {
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursion));
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Reserva result = reservaService.createReserva(reserva);
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(excursionRepository).findById(1L);
        verify(reservaRepository).save(reserva);
    }

    @Test
    void testCreateReservaWithInvalidDate() {
        reserva.setFechaReserva(LocalDateTime.now().plusDays(3));
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursion));

        ExcursionAlreadyStartedException exception = assertThrows(ExcursionAlreadyStartedException.class, () -> {
            reservaService.createReserva(reserva);
        });

        assertEquals("Reservation date cannot be after excursion start date", exception.getMessage());
        verify(excursionRepository).findById(1L);
        verify(reservaRepository, never()).save(reserva);
    }

    @Test
    void testCreateReservaWithInsufficientSeats() {
        reserva.setNumPlazas(15);
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursion));

        NotEnoughSeatsException exception = assertThrows(NotEnoughSeatsException.class, () -> {
            reservaService.createReserva(reserva);
        });

        assertEquals("Not enough available seats for the excursion", exception.getMessage());
        verify(excursionRepository).findById(1L);
        verify(reservaRepository, never()).save(reserva);
    }

    @Test
    void testGetReservasByEmail() {
        String email = "test@example.com";
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva);

        when(reservaRepository.findByEmail(email)).thenReturn(reservas);

        List<Reserva> result = reservaService.getReservasByEmail(email);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reserva, result.get(0));
        verify(reservaRepository).findByEmail(email);
    }

    @Test
    void testGetReservasByEmailWithNoReservas() {
        String email = "test@example.com";

        when(reservaRepository.findByEmail(email)).thenReturn(Collections.emptyList());

        List<Reserva> result = reservaService.getReservasByEmail(email);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(reservaRepository).findByEmail(email);
    }
}

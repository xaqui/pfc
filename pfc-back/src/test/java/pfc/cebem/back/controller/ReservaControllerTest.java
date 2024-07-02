package pfc.cebem.back.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import pfc.cebem.back.controller.reserva.ReservaController;
import pfc.cebem.back.dto.reserva.ReservaCreationDTO;
import pfc.cebem.back.dto.reserva.ReservaDTO;
import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.entity.Reserva;
import pfc.cebem.back.exception.ExcursionAlreadyStartedException;
import pfc.cebem.back.exception.NotEnoughSeatsException;
import pfc.cebem.back.exception.ResourceNotFoundException;
import pfc.cebem.back.service.excursion.ExcursionService;
import pfc.cebem.back.service.reserva.ReservaService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ReservaController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @MockBean
    private ExcursionService excursionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Reserva reserva;
    private Excursion excursion;
    private Excursion excursion_ExcursionAlreadyStarted;

    private ReservaDTO reservaDTO;

    private ReservaCreationDTO reservaCreationDTO;
    private ReservaCreationDTO reservaCreationDTO_ResourceNotFound;
    private ReservaCreationDTO reservaCreationDTO_ExcursionStarted;
    private ReservaCreationDTO reservaCreationDTO_NotEnoughSeats;

    @BeforeEach
    void setUp() {
        excursion = new Excursion(1L, "City", "Description", LocalDateTime.now().plusDays(2), 100.0, 10,
                LocalDateTime.now(), 10);

        excursion_ExcursionAlreadyStarted = new Excursion(2L, "City", "Description", LocalDateTime.now().minusDays(2), 100.0, 10,
        LocalDateTime.now(), 10);

        reserva = new Reserva();
        reserva.setReservaId(1L);
        reserva.setExcursion(excursion);
        reserva.setEmail("test@example.com");
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setNumPlazas(5);
        reserva.setPrecio(100.0);

        reservaDTO = new ReservaDTO();
        reservaDTO.setExcursionId(1L);
        reservaDTO.setEmail("test@example.com");
        reservaDTO.setFechaReserva(LocalDateTime.now());
        reservaDTO.setNumPlazas(5);
        reservaDTO.setPrecio(100.0);

        reservaCreationDTO = new ReservaCreationDTO();
        reservaCreationDTO.setExcursionId(1L);
        reservaCreationDTO.setEmail("test@example.com");
        reservaCreationDTO.setNumPlazas(2);

        reservaCreationDTO_ResourceNotFound = new ReservaCreationDTO();
        reservaCreationDTO_ResourceNotFound.setExcursionId(-1L);
        reservaCreationDTO_ResourceNotFound.setEmail("test@example.com");
        reservaCreationDTO_ResourceNotFound.setNumPlazas(1);

        reservaCreationDTO_ExcursionStarted = new ReservaCreationDTO();
        reservaCreationDTO_ExcursionStarted.setExcursionId(2L);
        reservaCreationDTO_ExcursionStarted.setEmail("test@example.com");
        reservaCreationDTO_ExcursionStarted.setNumPlazas(1);

        reservaCreationDTO_NotEnoughSeats = new ReservaCreationDTO();
        reservaCreationDTO_NotEnoughSeats.setExcursionId(1L);
        reservaCreationDTO_NotEnoughSeats.setEmail("test@example.com");
        reservaCreationDTO_NotEnoughSeats.setNumPlazas(99999999);
        
    }

    @Test
    void testCreateReserva() throws Exception {
        when(reservaService.createReserva(any(Reserva.class))).thenReturn(reserva);

        mockMvc.perform(post("/reservas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservaCreationDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void testCreateReserva_ResourceNotFound() throws Exception {
        when(reservaService.createReserva(any(Reserva.class)))
                .thenThrow(new ResourceNotFoundException("Excursion not found"));

        mockMvc.perform(post("/reservas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservaCreationDTO_ResourceNotFound)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Excursion not found"));
    }

    @Test
    void testCreateReserva_ExcursionAlreadyStarted() throws Exception {
        when(reservaService.createReserva(any(Reserva.class)))
                .thenThrow(new ExcursionAlreadyStartedException("Excursion already started"));

        mockMvc.perform(post("/reservas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservaCreationDTO_ExcursionStarted)))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Excursion already started"));
    }

    @Test
    void testCreateReserva_NotEnoughSeats() throws Exception {
        when(reservaService.createReserva(any(Reserva.class)))
                .thenThrow(new NotEnoughSeatsException("Not enough seats"));

        mockMvc.perform(post("/reservas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservaCreationDTO_NotEnoughSeats)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Not enough seats"));
    }

    @Test
    void testGetReservaById() throws Exception {
        when(reservaService.getReservaById(1L)).thenReturn(Optional.of(reserva));

        mockMvc.perform(get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }
}

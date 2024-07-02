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

import pfc.cebem.back.controller.excursion.ExcursionController;
import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.service.excursion.ExcursionService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ExcursionController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ExcursionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExcursionService excursionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Excursion excursion;

    @BeforeEach
    void setUp() {
        excursion = new Excursion(1L, "City", "Description", LocalDateTime.now(), 100.0, 10, LocalDateTime.now(), 10);
    }

    @Test
    void testGetExcursionById() throws Exception {
        when(excursionService.getExcursionById(1L)).thenReturn(Optional.of(excursion));

        mockMvc.perform(get("/excursions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ciudad").value("City"));
    }

    @Test
    void testCreateExcursion() throws Exception {
        Excursion newExcursion = new Excursion(null, "City", "Description", LocalDateTime.now(), 100.0, 10, LocalDateTime.now(), 10);
        when(excursionService.createExcursion(any(Excursion.class))).thenReturn(newExcursion);

        mockMvc.perform(post("/excursions")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newExcursion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ciudad").value("City"));
    }
}

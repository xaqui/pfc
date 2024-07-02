package pfc.cebem.back.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.repository.ExcursionRepository;
import pfc.cebem.back.service.excursion.ExcursionService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ExcursionServiceTest {

    @Autowired
    private ExcursionService excursionService;

    @MockBean
    private ExcursionRepository excursionRepository;

    private Excursion excursion;

    @BeforeEach
    void setUp() {
        excursion = new Excursion(1L, "City", "Description", LocalDateTime.now(), 100.0, 10, LocalDateTime.now(), 10);
    }

    @Test
    void testGetExcursionById() {
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursion));

        Optional<Excursion> result = excursionService.getExcursionById(1L);
        assertTrue(result.isPresent());
        assertEquals("City", result.get().getCiudad());
    }

    @Test
    void testCreateExcursion() {
        when(excursionRepository.save(excursion)).thenReturn(excursion);

        Excursion result = excursionService.createExcursion(excursion);
        assertNotNull(result);
        assertEquals("City", result.getCiudad());
    }
}

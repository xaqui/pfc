package pfc.cebem.back.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.entity.Reserva;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ExcursionRepository excursionRepository;

    private Excursion excursion;

    @BeforeEach
    void setUp() {
        excursion = new Excursion(null, "City", "Description", LocalDateTime.now().plusDays(2), 100.0, 10, LocalDateTime.now(), 10);
        excursion = excursionRepository.save(excursion);
    }

    @Test
    void testSaveAndFindById() {
        Reserva reserva = new Reserva();
        reserva.setExcursion(excursion);
        reserva.setEmail("test@example.com");
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setNumPlazas(5);
        reserva.setPrecio(100.0);

        Reserva savedReserva = reservaRepository.save(reserva);

        Optional<Reserva> foundReserva = reservaRepository.findById(savedReserva.getReservaId());
        assertThat(foundReserva).isPresent();
        assertThat(foundReserva.get().getEmail()).isEqualTo("test@example.com");
    }
}
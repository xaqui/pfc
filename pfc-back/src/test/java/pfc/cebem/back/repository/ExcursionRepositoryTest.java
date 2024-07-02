package pfc.cebem.back.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pfc.cebem.back.entity.Excursion;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExcursionRepositoryTest {

    @Autowired
    private ExcursionRepository excursionRepository;

    @Test
    void testSaveAndFindById() {
        Excursion excursion = new Excursion(null, "City", "Description", LocalDateTime.now(), 100.0, 10, LocalDateTime.now(), 10);
        Excursion savedExcursion = excursionRepository.save(excursion);

        Optional<Excursion> foundExcursion = excursionRepository.findById(savedExcursion.getExcursionId());
        assertThat(foundExcursion).isPresent();
        assertThat(foundExcursion.get().getCiudad()).isEqualTo("City");
    }

    @Test
    void testFindByFechaInicioGreaterThanEqualAndFechaInicioLessThanEqualAndCiudadIgnoreCaseLike() {
        Excursion excursion = new Excursion(null, "City", "Description", LocalDateTime.now(), 100.0, 10, LocalDateTime.now(), 10);
        Excursion savedExcursion = excursionRepository.save(excursion);
        
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(7);
        String city = "City";

        List<Excursion> excursions = excursionRepository.findByFechaInicioGreaterThanEqualAndFechaInicioLessThanEqualAndCiudadIgnoreCaseLike(startDate, endDate, city);
        assertThat(excursions).isNotEmpty();
    }
}
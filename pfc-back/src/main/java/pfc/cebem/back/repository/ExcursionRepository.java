package pfc.cebem.back.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pfc.cebem.back.entity.Excursion;


// Spring Data JPA
public interface ExcursionRepository extends CrudRepository<Excursion, Long> {
    /*
     * El nombre de método
     * findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndCityLike es un tipo
     * especial de nombre de método que Spring Data JPA interpreta para generar una
     * consulta. Esto se denomina DSL (Domain Specific Language) de método de
     * consulta.
     */
    List<Excursion> findByFechaInicioGreaterThanEqualAndFechaInicioLessThanEqualAndCiudadIgnoreCaseLike(
            LocalDateTime startDate, LocalDateTime enDate, String city);
}
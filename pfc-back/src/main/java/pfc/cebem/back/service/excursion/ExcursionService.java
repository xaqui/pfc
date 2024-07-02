package pfc.cebem.back.service.excursion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.exception.ResourceNotFoundException;
import pfc.cebem.back.repository.ExcursionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExcursionService {

    private final ExcursionRepository excursionRepository;

    @Autowired
    public ExcursionService(ExcursionRepository excursionRepository) {
        this.excursionRepository = excursionRepository;
    }

    public List<Excursion> getAllExcursions() {
        return StreamSupport.stream(excursionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Excursion> getExcursionsByFilter(LocalDate startDate, LocalDate endDate, String city) {
        if (startDate == null) {
            startDate = LocalDate.of(1900, 1, 1);
        }
        if (endDate == null) {
            endDate = LocalDate.of(2100, 12, 31); 
        }
        if (city == null) {
            city = "";
        }
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atStartOfDay();
        return excursionRepository.findByFechaInicioGreaterThanEqualAndFechaInicioLessThanEqualAndCiudadIgnoreCaseLike(startDateTime, endDateTime,"%" + city + "%");
    }

    public Optional<Excursion> getExcursionById(Long id) {
        return Optional.ofNullable(excursionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Excursion not found with id " + id)));
    }

    // Not used by application
    public Excursion createExcursion(Excursion excursion) {
        return excursionRepository.save(excursion);
    }

    // Not used by application
    public Excursion updateExcursion(Excursion excursion) {
        return excursionRepository.save(excursion);
    }

    // Not used by application 
    public void deleteExcursion(Long id) {
        excursionRepository.deleteById(id);
    }
}
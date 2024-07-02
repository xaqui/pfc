package pfc.cebem.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pfc.cebem.back.entity.Reserva;

// Spring Data JPA
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    /*
     * Este método utiliza el mecanismo de creación de consultas de Spring Data JPA
     * para generar una consulta que encontrará todas las entidades Reserva con un
     * correo electrónico coincidente. La implementación real del método es
     * proporcionada automáticamente en tiempo de ejecución por Spring Data JPA.
     */
    List<Reserva> findByEmail(String email);
}

package pfc.cebem.back.controller.excursion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import pfc.cebem.back.dto.excursion.ExcursionDTO;
import pfc.cebem.back.dto.excursion.ExcursionSubtotalDTO;
import pfc.cebem.back.entity.Excursion;
import pfc.cebem.back.service.excursion.ExcursionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/excursions")
public class ExcursionController {

    private final ExcursionService excursionService;

    @Autowired
    public ExcursionController(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }

    /*
     * @GetMapping
     * public ResponseEntity<List<ExcursionDTO>> getAllExcursions() {
     * List<Excursion> excursions = excursionService.getAllExcursions();
     * List<ExcursionDTO> excursionDTOs = excursions.stream()
     * .map(this::convertToDto)
     * .collect(Collectors.toList());
     * return ResponseEntity.ok(excursionDTOs);
     * }
     */

    @GetMapping
    public ResponseEntity<List<ExcursionDTO>> getExcursionsByFilter(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String city) {
        List<Excursion> excursions = excursionService.getExcursionsByFilter(startDate, endDate, city);
        List<ExcursionDTO> excursionDTOs = excursions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(excursionDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExcursionDTO> getExcursionById(@PathVariable Long id) {
        Optional<Excursion> excursion = excursionService.getExcursionById(id);
        return excursion.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/subtotal/{seats}")
    public ResponseEntity<ExcursionSubtotalDTO> getExcursionSubtotal(@PathVariable Long id, @PathVariable int seats) {
        Optional<Excursion> excursion = excursionService.getExcursionById(id);
        if (excursion.isPresent()) {
            ExcursionSubtotalDTO subtotal = new ExcursionSubtotalDTO();
            subtotal.setExcursion(convertToDto(excursion.get()));
            subtotal.setSeats(seats);
            subtotal.setSubtotal(excursion.get().getPrecio() * seats);
            return ResponseEntity.ok(subtotal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Unused by app
    @PostMapping
    public ResponseEntity<ExcursionDTO> createExcursion(@Valid @RequestBody ExcursionDTO excursionDTO) {
        Excursion excursion = convertToEntity(excursionDTO);
        Excursion createdExcursion = excursionService.createExcursion(excursion);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdExcursion));
    }

    // Unused by app
    @PutMapping("/{id}")
    public ResponseEntity<ExcursionDTO> updateExcursion(@PathVariable Long id,
            @Valid @RequestBody ExcursionDTO excursionDTO) {
        Excursion excursion = convertToEntity(excursionDTO);
        excursion.setExcursionId(id);
        Excursion updatedExcursion = excursionService.updateExcursion(excursion);
        return ResponseEntity.ok(convertToDto(updatedExcursion));
    }

    // Unused by app
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcursion(@PathVariable Long id) {
        excursionService.deleteExcursion(id);
        return ResponseEntity.noContent().build();
    }

    private ExcursionDTO convertToDto(Excursion excursion) {
        ExcursionDTO excursionDTO = new ExcursionDTO();
        excursionDTO.setExcursionId(excursion.getExcursionId());
        excursionDTO.setCiudad(excursion.getCiudad());
        excursionDTO.setDescripcion(excursion.getDescripcion());
        excursionDTO.setFechaInicio(excursion.getFechaInicio());
        excursionDTO.setPrecio(excursion.getPrecio());
        excursionDTO.setMaxNumPlazas(excursion.getMaxNumPlazas());
        excursionDTO.setFechaAlta(excursion.getFechaAlta());
        excursionDTO.setNumPlazas(excursion.getNumPlazas());
        return excursionDTO;
    }

    private Excursion convertToEntity(ExcursionDTO excursionDTO) {
        Excursion excursion = new Excursion();
        excursion.setExcursionId(excursionDTO.getExcursionId());
        excursion.setCiudad(excursionDTO.getCiudad());
        excursion.setDescripcion(excursionDTO.getDescripcion());
        excursion.setFechaInicio(excursionDTO.getFechaInicio());
        excursion.setPrecio(excursionDTO.getPrecio());
        excursion.setMaxNumPlazas(excursionDTO.getMaxNumPlazas());
        excursion.setFechaAlta(excursionDTO.getFechaAlta());
        excursion.setNumPlazas(excursionDTO.getNumPlazas());
        return excursion;
    }
}
import com.example.demo.dto.Entity1DTO;
import com.example.demo.service.Entity1Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller genérico para Entity1.
 * Responsável por receber requisições HTTP e delegar o processamento para o Service.
 */

@RestController
@RequestMapping("/api/entity1")
public class Entity1Controller {

    private final Entity1Service entity1Service;

    // Injeção de dependência via construtor
    public Entity1Controller(Entity1Service entity1Service) {
        this.entity1Service = entity1Service;
    }

    // --- CREATE ---
    @PostMapping
    public ResponseEntity<Entity1DTO> createEntity(@RequestBody Entity1DTO dto) {
        Entity1DTO created = entity1Service.createEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // --- READ ALL ---
    @GetMapping
    public ResponseEntity<List<Entity1DTO>> getAllEntities() {
        List<Entity1DTO> list = entity1Service.getAllEntities();
        return ResponseEntity.ok(list);
    }

    // --- READ BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<Entity1DTO> getEntityById(@PathVariable Long id) {
        Entity1DTO dto = entity1Service.getEntityById(id);
        return ResponseEntity.ok(dto);
    }

    // --- UPDATE ---
    @PutMapping("/{id}")
    public ResponseEntity<Entity1DTO> updateEntity(@PathVariable Long id, @RequestBody Entity1DTO dto) {
        Entity1DTO updated = entity1Service.updateEntity(id, dto);
        return ResponseEntity.ok(updated);
    }

    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        entity1Service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
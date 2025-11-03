import com.example.demo.dto.Entity1DTO;
import com.example.demo.entity.Entity1;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.Entity1Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Camada de serviço responsável pela lógica de negócio de Entity1.
 * Atua entre o Controller e o Repository.
 */

@Service
public class Entity1Service {

    private final Entity1Repository entity1Repository;

    // Injeção via construtor
    public Entity1Service(Entity1Repository entity1Repository) {
        this.entity1Repository = entity1Repository;
    }

    // --- CREATE ---
    @Transactional
    public Entity1DTO createEntity(Entity1DTO dto) {
        Entity1 entity = mapToEntity(dto);
        entity = entity1Repository.save(entity);
        return mapToDTO(entity);
    }

    // --- READ ALL ---
    @Transactional(readOnly = true)
    public List<Entity1DTO> getAllEntities() {
        return entity1Repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // --- READ BY ID ---
    @Transactional(readOnly = true)
    public Entity1DTO getEntityById(Long id) {
        Entity1 entity = entity1Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity1 com ID " + id + " não encontrada"));
        return mapToDTO(entity);
    }

    // --- UPDATE ---
    @Transactional
    public Entity1DTO updateEntity(Long id, Entity1DTO dto) {
        Entity1 existing = entity1Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity1 com ID " + id + " não encontrada"));

        existing.setNome(dto.getNome());
        existing.setDescricao(dto.getDescricao());
        // adicione outros campos conforme o modelo real

        existing = entity1Repository.save(existing);
        return mapToDTO(existing);
    }

    // --- DELETE ---
    @Transactional
    public void deleteEntity(Long id) {
        if (!entity1Repository.existsById(id)) {
            throw new EntityNotFoundException("Entity1 com ID " + id + " não encontrada");
        }
        entity1Repository.deleteById(id);
    }

    // --- MÉTODOS DE CONVERSÃO ENTRE ENTITY E DTO ---
    private Entity1DTO mapToDTO(Entity1 entity) {
        Entity1DTO dto = new Entity1DTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    private Entity1 mapToEntity(Entity1DTO dto) {
        Entity1 entity = new Entity1();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}
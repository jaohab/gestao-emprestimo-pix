import Entity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository responsável pelo acesso aos dados da Entity1.
 * Utiliza os recursos do Spring Data JPA para operações automáticas de CRUD.
 */
@Repository
public interface Entity1Repository extends JpaRepository<Entity1, Long> {

    // 🔹 Aqui você pode definir consultas personalizadas (métodos query) caso necessário.
    // Exemplo:
    // List<Entity1> findByNameContainingIgnoreCase(String name);

}
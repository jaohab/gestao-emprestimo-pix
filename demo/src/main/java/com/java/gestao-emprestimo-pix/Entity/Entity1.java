import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "entity_1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(max = 50, message = "O nome pode ter no máximo 50 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição não pode ser vazia.")
    @Size(max = 150, message = "A descrição pode ter no máximo 150 caracteres.")
    private String descricao;

    @NotNull(message = "O campo ativo é obrigatório.")
    private Boolean ativo;

    @NotNull(message = "A data de criação é obrigatória.")
    private java.time.LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status é obrigatório.")
    private StatusEntity status;
}

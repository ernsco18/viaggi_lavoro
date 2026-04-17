package ernesto.viaggi_lavoro.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="dipendenti")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    @Column(unique = true)
    private String email;
}
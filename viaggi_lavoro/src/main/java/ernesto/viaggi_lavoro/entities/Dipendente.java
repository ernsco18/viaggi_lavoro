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
    private long id;

    @Column
    private String username;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private String email;
}
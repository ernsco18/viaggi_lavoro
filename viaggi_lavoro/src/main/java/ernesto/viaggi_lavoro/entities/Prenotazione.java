package ernesto.viaggi_lavoro.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "prenotazioni", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dipendente_id", "data_prenotazione"})
})
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private LocalDate data_prenotazione;
    private String note;

    @OneToOne
    private Viaggio viaggio;
    @OneToOne
    private Dipendente dipendente;
}

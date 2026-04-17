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
    private Long id;

    @Column
    private LocalDate data_prenotazione;
    @Column
    private String note;

    @OneToOne
    private Viaggio viaggio;
    @OneToOne
    private Dipendente dipendente;
}

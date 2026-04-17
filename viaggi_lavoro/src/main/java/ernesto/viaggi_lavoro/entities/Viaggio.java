package ernesto.viaggi_lavoro.entities;

import ernesto.viaggi_lavoro.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="viaggi")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String destinazione;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;
}

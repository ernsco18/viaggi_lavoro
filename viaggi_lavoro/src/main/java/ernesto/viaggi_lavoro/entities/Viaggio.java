package ernesto.viaggi_lavoro.entities;

import ernesto.viaggi_lavoro.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private long id;

    @Column
    private String destinazione;
    @Column
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;
}

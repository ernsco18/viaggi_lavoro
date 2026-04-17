package ernesto.viaggi_lavoro.repository;

import ernesto.viaggi_lavoro.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndDataPrenotazione(Long dipendenteId, LocalDate dataPrenotazione);
}

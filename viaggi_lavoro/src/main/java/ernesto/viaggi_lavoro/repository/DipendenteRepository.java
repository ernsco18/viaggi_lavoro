package ernesto.viaggi_lavoro.repository;

import ernesto.viaggi_lavoro.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente,Long> {
}

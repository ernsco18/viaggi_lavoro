package ernesto.viaggi_lavoro.repository;

import ernesto.viaggi_lavoro.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio,Long> {
}

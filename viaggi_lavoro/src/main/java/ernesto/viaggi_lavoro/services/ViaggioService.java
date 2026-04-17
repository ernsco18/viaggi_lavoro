package ernesto.viaggi_lavoro.services;

import ernesto.viaggi_lavoro.entities.Viaggio;
import ernesto.viaggi_lavoro.exceptions.NotFoundException;
import ernesto.viaggi_lavoro.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio save(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Page<Viaggio> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio findById(long id) {
        return viaggioRepository.findById(id).get();
    }

    public Viaggio update(Viaggio nuovoViaggio, Long id) {
        Viaggio viaggio = findById(id);
        viaggio.setData(nuovoViaggio.getData());
        viaggio.setStato(nuovoViaggio.getStato());
        viaggio.setDestinazione(nuovoViaggio.getDestinazione());
        return viaggioRepository.save(nuovoViaggio);
    }

    public void deleteById(Long id){
        if(!this.viaggioRepository.existsById(id)){
            throw new RuntimeException("viaggio non trovato");
        }
        viaggioRepository.deleteById(id);
    }
}

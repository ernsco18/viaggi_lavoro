package ernesto.viaggi_lavoro.services;

import ernesto.viaggi_lavoro.entities.Prenotazione;
import ernesto.viaggi_lavoro.exceptions.NotFoundException;
import ernesto.viaggi_lavoro.repository.PrenotazioneRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione save(Prenotazione prenotazione){
        if(prenotazioneRepository.existsByDipendenteIdAndDataPrenotazione(
                prenotazione.getDipendente().getId(),
                prenotazione.getData_prenotazione())){
            throw new EntityExistsException("Prenotazione existente");
        }
        return prenotazioneRepository.save(prenotazione);
    }

    public Page<Prenotazione> findAll(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findById(Long id){
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Prenotazione update (Long id, Prenotazione nuovaPrenotazione){
        Prenotazione prenotazione = findById(id);
        prenotazione.setNote(nuovaPrenotazione.getNote());
        return prenotazioneRepository.save(prenotazione);
    }

    public void deleteById(Long id){
        if (!prenotazioneRepository.existsById(id)){
            throw new RuntimeException("Prenotazione existente");
        }
        prenotazioneRepository.deleteById(id);
    }
}

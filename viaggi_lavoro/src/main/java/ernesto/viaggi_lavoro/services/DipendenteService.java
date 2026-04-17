package ernesto.viaggi_lavoro.services;

import ernesto.viaggi_lavoro.entities.Dipendente;
import ernesto.viaggi_lavoro.exceptions.NotFoundException;
import ernesto.viaggi_lavoro.exceptions.ValidationException;
import ernesto.viaggi_lavoro.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente save(Dipendente dipendente){
        if(dipendenteRepository.existsByEmail(dipendente.getEmail())){
            throw new ValidationException("Email existente");
        }
            return dipendenteRepository.save(dipendente);
    }

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id));
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size,Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }
    public Dipendente update(Long id, Dipendente nuovoDipendente){
        Dipendente dipendente= findById(id);
        dipendente.setNome(nuovoDipendente.getNome());
        dipendente.setCognome(nuovoDipendente.getCognome());
        dipendente.setEmail(nuovoDipendente.getEmail());
        dipendente.setUsername(nuovoDipendente.getUsername());
        return dipendenteRepository.save(dipendente);
    }

     public void delete(Long id){
        if(!dipendenteRepository.existsById(id)){
            throw new ValidationException("Id dipendente existente");
        }
        dipendenteRepository.deleteById(id);
     }
}

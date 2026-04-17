package ernesto.viaggi_lavoro.controllers;

import ernesto.viaggi_lavoro.entities.Dipendente;
import ernesto.viaggi_lavoro.entities.Prenotazione;
import ernesto.viaggi_lavoro.entities.Viaggio;
import ernesto.viaggi_lavoro.payloads.PrenotazioneDTO;
import ernesto.viaggi_lavoro.services.DipendenteService;
import ernesto.viaggi_lavoro.services.PrenotazioneService;
import ernesto.viaggi_lavoro.services.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;
    private final DipendenteService dipendenteService;
    private final ViaggioService viaggioService;

    public PrenotazioneController(PrenotazioneService prenotazioneService, DipendenteService dipendenteService, ViaggioService viaggioService) {
        this.prenotazioneService = prenotazioneService;
        this.dipendenteService = dipendenteService;
        this.viaggioService = viaggioService;
    }

    @GetMapping // <-- AGGIUNTO: mancava l'annotazione HTTP!
    public Page<Prenotazione> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "dataRichiesta") String sortBy
    ){
        return prenotazioneService.findAll(page, size, sortBy);
    }

    @PostMapping // <-- CORRETTO: da @GetMapping a @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Valid PrenotazioneDTO body, BindingResult validationResult){ // <-- CORRETTO: @RequestBody al posto di @RequestParam

        // CORRETTO: Tolto il "!" che invertiva la logica!
        if (validationResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validationResult.getFieldError().getDefaultMessage());
        }

        Dipendente newDipendente = dipendenteService.findById(body.dipendenteId());
        Viaggio newViaggio = viaggioService.findById(body.viaggioId());
        Prenotazione newPrenotazione = new Prenotazione();

        newPrenotazione.setDipendente(newDipendente);
        newPrenotazione.setViaggio(newViaggio);
        newPrenotazione.setDataPrenotazione(LocalDate.now());
        newPrenotazione.setNote(body.note());

        return prenotazioneService.save(newPrenotazione);
    }

    @PutMapping("/{id}")
    public Prenotazione update(@PathVariable Long id, @RequestBody @Valid PrenotazioneDTO request) {
        /*
          NOTA BENE SULL'UPDATE:
          Così stai passando un oggetto "vuoto" con solo le note valorizzate al service.
          A seconda di come hai scritto "prenotazioneService.update()", rischi di
          sovrascrivere a null il dipendente e il viaggio sul database.
          Solitamente l'aggiornamento si fa recuperando prima l'entità esistente dal DB.
        */
        Prenotazione newPrenotazione = new Prenotazione();
        newPrenotazione.setNote(request.note());

        return prenotazioneService.update(id, newPrenotazione);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        prenotazioneService.deleteById(id);
    }
}
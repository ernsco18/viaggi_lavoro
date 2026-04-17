package ernesto.viaggi_lavoro.controllers;

import ernesto.viaggi_lavoro.entities.Dipendente;
import ernesto.viaggi_lavoro.payloads.DipendenteDTO;
import ernesto.viaggi_lavoro.services.DipendenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.query.PreprocessedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping
    public Page<Dipendente> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "cognome") String sortBy
    ) {
        return dipendenteService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente create(@RequestBody @Validated DipendenteDTO body,  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());
        }
        Dipendente newDipendente = new Dipendente();
        newDipendente.setUsername(body.username());
        newDipendente.setNome(body.nome());
        newDipendente.setCognome(body.cognome());
        newDipendente.setEmail(body.email());

        return dipendenteService.save(newDipendente);
    }

    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody @Validated DipendenteDTO body) {
        Dipendente dettagli = new Dipendente();
        dettagli.setUsername(body.username());
        dettagli.setNome(body.nome());
        dettagli.setCognome(body.cognome());
        dettagli.setEmail(body.email());

        return dipendenteService.update(id, dettagli);
    }

    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }
}

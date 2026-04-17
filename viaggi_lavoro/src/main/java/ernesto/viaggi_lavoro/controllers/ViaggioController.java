package ernesto.viaggi_lavoro.controllers;

import ernesto.viaggi_lavoro.entities.Viaggio;
import ernesto.viaggi_lavoro.payloads.ViaggioDTO;
import ernesto.viaggi_lavoro.services.ViaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    private final ViaggioService ViaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        ViaggioService = viaggioService;
    }

    @GetMapping
    public Page<Viaggio> getViaggi(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "data") String sortBy
                                   ) {
        return ViaggioService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable long id) {
        return ViaggioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio create(@RequestBody @Validated ViaggioDTO body, BindingResult validationResult) {
//        if (validationResult.hasErrors()) {
//            List<String> errors = validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
//            throw new ValidationException(errors);
//        }
        Viaggio newViaggio = new Viaggio();
        newViaggio.setDestinazione(body.destinazione());
        newViaggio.setData(body.data());
        newViaggio.setStato(body.stato());

        return ViaggioService.save(newViaggio);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable Long id, @RequestBody @Validated ViaggioDTO body) {
        Viaggio newViaggio = new Viaggio();
        newViaggio.setDestinazione(body.destinazione());
        newViaggio.setData(body.data());
        newViaggio.setStato(body.stato());

        return ViaggioService.update(newViaggio, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        ViaggioService.deleteById(id);
    }
}

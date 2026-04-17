package ernesto.viaggi_lavoro.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PrenotazioneDTO(
        @NotNull
        Long dipendenteId,
        @NotNull
        Long viaggioId,
        @Size(max = 255)
        String note
) {
}

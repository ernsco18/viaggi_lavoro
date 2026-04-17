package ernesto.viaggi_lavoro.payloads;

import ernesto.viaggi_lavoro.enums.StatoViaggio;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ViaggioDTO(@NotBlank
                        String destinazione,
                        @NotNull
                        @FutureOrPresent
                        LocalDate data,
                        @NotNull
                        StatoViaggio stato) {
}

package ernesto.viaggi_lavoro.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank
        String username,
        @NotBlank
        String nome,
        @NotBlank
        String cognome,
        @Email
        @NotBlank
        String email
) {
}

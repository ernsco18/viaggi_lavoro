package ernesto.viaggi_lavoro.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErroriDTO (String message, LocalDateTime timestamp, List<String> errors){
    public ErroriDTO {
        if (errors == null) {
            errors = List.of();
        }
    }

    public ErroriDTO(String message, LocalDateTime timestamp) {
        this(message, timestamp, List.of());
    }
}

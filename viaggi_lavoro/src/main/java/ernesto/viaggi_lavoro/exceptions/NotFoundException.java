package ernesto.viaggi_lavoro.exceptions;

public class NotFoundException extends RuntimeException {
    public  NotFoundException(Long id) {
        super(String.format("Nessun objeto non existe il id %d", id));
    }
}

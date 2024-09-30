package domain.exception.cliente;

public class AlreadyExistsClienteException extends RuntimeException {
    public AlreadyExistsClienteException(final String message) {
        super(message);
    }
}
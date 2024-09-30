package domain.exception.agencia;

public class AlreadyExistsAgenciaException extends RuntimeException {
    public AlreadyExistsAgenciaException(final String message) {
        super(message);
    }
}

package domain.exception.veiculo;

public class AlreadyExistsVeiculoException extends RuntimeException{
    public AlreadyExistsVeiculoException(final String message) {
        super(message);
    }
}

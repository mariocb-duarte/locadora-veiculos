package domain.exception.operacao;

public class AlreadyExistsOperacaoException extends RuntimeException{
    public AlreadyExistsOperacaoException(final String message) {
        super(message);
    }
}

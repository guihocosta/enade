package exception;

/** Base das exceções do sistema. */
public abstract class EnadeException extends Exception {
    public EnadeException(String mensagem) {
        super(mensagem);
    }

    public EnadeException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

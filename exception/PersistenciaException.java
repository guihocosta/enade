package exception;

/** Falha ao ler ou gravar o arquivo de dados. */
public class PersistenciaException extends EnadeException {
    public PersistenciaException(String mensagem) {
        super(mensagem);
    }

    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

package exception;

/** Valor que não pode ser aceito, como nota fora de 0 a 10 ou nome vazio. */
public class DadoInvalidoException extends EnadeException {
    public DadoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

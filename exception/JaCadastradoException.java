package exception;

/** Código ou matrícula que já existe no sistema. */
public class JaCadastradoException extends EnadeException {
    public JaCadastradoException(String mensagem) {
        super(mensagem);
    }
}

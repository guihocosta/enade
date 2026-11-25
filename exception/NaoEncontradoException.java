package exception;

/** Área, curso ou aluno que não existe no sistema. */
public class NaoEncontradoException extends EnadeException {
    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

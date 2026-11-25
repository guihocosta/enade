package exception;

/**
 * Não há mais nada para ler. Não herda de EnadeException porque não é um erro de uma
 * operação: se os menus a tratassem, o programa ficaria preso pedindo uma opção.
 */
public class FimDaEntradaException extends RuntimeException {
    public FimDaEntradaException() {
        super("Fim da entrada.");
    }
}

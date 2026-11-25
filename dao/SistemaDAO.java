package dao;

import exception.PersistenciaException;
import model.Sistema;

/** Leitura e escrita do arquivo de dados. */
public class SistemaDAO {
    private static final String ARQUIVO = "dados.txt";

    // TODO (Guilherme): ler o arquivo e reconstruir os objetos
    public Sistema carregar() throws PersistenciaException {
        return new Sistema();
    }

    // TODO (Guilherme): gravar todos os dados no arquivo
    public void salvar(Sistema sistema) throws PersistenciaException {
    }
}

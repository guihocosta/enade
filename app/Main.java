package app;

import dao.SistemaDAO;
import exception.FimDaEntradaException;
import model.Sistema;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = Entrada.abrirEntrada();
        SistemaDAO dao = new SistemaDAO();

        Sistema s = Entrada.carregar(dao);
        boolean carregou = s != null;
        if (!carregou) s = new Sistema();

        try {
            int op = Entrada.menu(input);

            while (op != 0) {
                switch(op) {
                    case 1:
                        boolean alterou = Entrada.menu_cadastro(s, input);
                        if (alterou && carregou) Entrada.salvar(dao, s);
                        break;
                    case 2:
                        Entrada.menu_relatorios(s, input);
                        break;
                }
                op = Entrada.menu(input);
            }
        } catch (FimDaEntradaException e) {
            Entrada.avisarFimDaEntrada();
        }

        Entrada.fecharEntrada(input);
    }
}

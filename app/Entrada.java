package app;

import dao.SistemaDAO;
import exception.DadoInvalidoException;
import exception.EnadeException;
import exception.FimDaEntradaException;
import exception.PersistenciaException;
import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe com as rotinas de entrada e saída do projeto
 * @author Hilario Seibel Junior, Luan Otoni de Oliveira e Guilherme Henrique Omena Costa
 */
public class Entrada {

    /**
     * Se houver um arquivo input.txt na pasta em que o projeto está sendo executado,
     * define que o Scanner vai ler deste arquivo e não do teclado.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     * NÃO ALTERE O ESTE CODIGO!!!!
     * @return Um objeto da classe Scanner de onde a entrada será lida
     */
    public static Scanner abrirEntrada() {
        Scanner input = null;
        try {
            // Se houver um arquivo input.txt, o Scanner vai ler dele.
            input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            // Locale.US para que o Java sempre leia double com "pontos" ao invés de "vírgulas"
            input = new Scanner(System.in).useLocale(Locale.US);
        }
        finally {
            return input;
        }
    }

    /**
     * Fecha o Scanner antes de sair do programa.
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     */
    public static void fecharEntrada(Scanner input) {
        input.close();
    }

    /**
     * Faz a leitura de uma linha inteira
     * @param msg: Mensagem que será exibida ao usuário
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     * @return Uma String contendo a linha que foi lida
     * @throws FimDaEntradaException Se a entrada acabou
     */
    public static String lerLinha(String msg, Scanner input) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        if (!input.hasNextLine()) throw new FimDaEntradaException();
        String linha = input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     * @return O número digitado pelo usuário convertido para int
     * @throws DadoInvalidoException Se a linha não for um número inteiro
     */
    public static int lerInteiro(String msg, Scanner input) throws DadoInvalidoException {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = Entrada.lerLinha(msg, input);
        try {
            return Integer.parseInt(linha.trim());
        } catch (NumberFormatException e) {
            throw new DadoInvalidoException("\"" + linha + "\" não é um número inteiro válido.");
        }
    }

    /**
     * Faz a leitura de um double
     * @param msg: Mensagem que será exibida ao usuário
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     * @return O número digitado pelo usuário convertido para double
     * @throws DadoInvalidoException Se a linha não for um número
     */
    private static double lerDouble(String msg, Scanner input) throws DadoInvalidoException {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um double e retorna este double
        String linha = Entrada.lerLinha(msg, input);
        // parseDouble também aceita "NaN" e "Infinity", que não servem como nota
        try {
            double valor = Double.parseDouble(linha.trim());
            if (Double.isFinite(valor)) return valor;
        } catch (NumberFormatException e) {
            // Cai no erro abaixo, o mesmo dos valores que não são finitos.
        }
        throw new DadoInvalidoException("\"" + linha + "\" não é um número válido (use ponto nas casas decimais).");
    }

    /**
     * Imprime um menu e lê a opção escolhida pelo usuário
     * @param msg: Texto do menu
     * @param ultima: Maior opção existente no menu
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     * @return Inteiro entre 0 e a última opção
     * @throws DadoInvalidoException Se a opção não existir no menu
     */
    private static int lerOpcao(String msg, int ultima, Scanner input) throws DadoInvalidoException {
        int op = Entrada.lerInteiro(msg, input);
        if (op < 0 || op > ultima) throw new DadoInvalidoException("Opção inválida.");
        return op;
    }

    /**
     * Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
     * @return Inteiro contendo a opção escolhida pelo usuário
     */
    public static int menu(Scanner input) {
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastro\n" +
                "2) Relatórios\n" +
                "0) Sair\n";

        while (true) {
            try {
                return Entrada.lerOpcao(msg, 2, input);
            } catch (DadoInvalidoException e) {
                Entrada.imprimirErro(e);
            }
        }
    }

    /**
     * Executa uma operação do menu de cadastro. Em caso de erro, informa o problema e volta a este menu.
     * @return true se os dados do sistema foram alterados, false se o usuário escolheu voltar
     */
    public static boolean menu_cadastro(Sistema s, Scanner input) {
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar Área\n" +
                "2) Cadastrar Curso\n" +
                "3) Cadastrar Aluno Ingressante\n" +
                "4) Cadastrar Aluno Concluinte\n" +
                "5) Cadastrar Resposta de Questionário\n" +
                "6) Cadastrar Resultado de Prova\n" +
                "0) Voltar\n";

        while (true) {
            try {
                switch (Entrada.lerOpcao(msg, 6, input)) {
                    case 0:
                        return false;
                    case 1:
                        Entrada.cadArea(s, input);
                        break;
                    case 2:
                        Entrada.cadCurso(s, input);
                        break;
                    case 3:
                        Entrada.cadIngressante(s, input);
                        break;
                    case 4:
                        Entrada.cadConcluinte(s, input);
                        break;
                    case 5:
                        Entrada.cadResposta(s, input);
                        break;
                    case 6:
                        Entrada.cadProva(s, input);
                        break;
                }
                return true;
            } catch (EnadeException e) {
                Entrada.imprimirErro(e);
            }
        }
    }

    /**
     * Executa uma operação do menu de relatórios. Em caso de erro, informa o problema e volta a este menu.
     */
    public static void menu_relatorios(Sistema s, Scanner input) {
        String msg = "*********************\n" +
                "1) Listar Resultados por Conceito\n" +
                "2) Listar Resultados por Área\n" +
                "3) Listar Alunos Irregulares\n" +
                "0) Voltar\n";

        while (true) {
            try {
                switch (Entrada.lerOpcao(msg, 3, input)) {
                    case 1:
                        Entrada.listResultConceito(s, input);
                        break;
                    case 2:
                        Entrada.listResultArea(s, input);
                        break;
                    case 3:
                        Entrada.listAlunosIrreg(s);
                        break;
                }
                return;
            } catch (EnadeException e) {
                Entrada.imprimirErro(e);
            }
        }
    }

    /**
     * Carrega os dados da execução anterior.
     * @return O sistema carregado, ou null se os dados não puderam ser lidos
     */
    public static Sistema carregar(SistemaDAO dao) {
        try {
            return dao.carregar();
        } catch (PersistenciaException e) {
            Entrada.imprimirErro(e);
            System.out.println("O programa vai iniciar sem dados. Para não sobrescrever o arquivo, " +
                    "nada será gravado nesta execução.");
            return null;
        }
    }

    /** Grava os dados. Se não for possível, avisa o usuário e o programa continua. */
    public static void salvar(SistemaDAO dao, Sistema s) {
        try {
            dao.salvar(s);
        } catch (PersistenciaException e) {
            Entrada.imprimirErro(e);
            System.out.println("A operação foi realizada, mas ainda não está gravada no arquivo.");
        }
    }

    public static void avisarFimDaEntrada() {
        System.out.println("\nFim da entrada. Encerrando o programa.");
    }

    private static void imprimirErro(EnadeException e) {
        System.out.println("Erro: " + e.getMessage());
    }

    public static void cadArea(Sistema s, Scanner input) throws EnadeException {
        String nome = Entrada.lerLinha("Digite o nome da área: ", input);
        String codigo = Entrada.lerLinha("Digite o código da área: ", input);

        s.cadastrarArea(new Area(nome, codigo));
        System.out.println("Área cadastrada com sucesso!");
    }

    private static void cadCurso(Sistema s, Scanner input) throws EnadeException {
        String nome = Entrada.lerLinha("Digite o nome do curso: ", input);
        String codigoC = Entrada.lerLinha("Digite o código do curso: ", input);
        Entrada.imprimirAreas(s);

        String codigoA = Entrada.lerLinha("Digite o código da área do curso: ", input);
        Area area = s.buscarArea(codigoA);
        s.cadastrarCurso(new Curso(nome,codigoC), area);
        System.out.println("Curso cadastrado com sucesso!");
    }

    private static void cadIngressante(Sistema s, Scanner input) throws EnadeException {
        String nome = Entrada.lerLinha("Digite o nome do ingressante: ", input);
        String matricula = Entrada.lerLinha("Digite a matrícula do ingressante: ", input);
        int semestre = Entrada.lerInteiro("Digite o semestre de ingresso: ", input);

        Curso curso = Entrada.escolherCurso(s, input);
        s.cadastrarAluno(new Ingressante(nome, matricula, semestre), curso);
        System.out.println("Aluno ingressante cadastrado com sucesso!");
    }

    private static void cadConcluinte(Sistema s, Scanner input) throws EnadeException {
        String nome = Entrada.lerLinha("Digite o nome do concluinte: ", input);
        String matricula = Entrada.lerLinha("Digite a matrícula do concluinte: ", input);

        Curso curso = Entrada.escolherCurso(s, input);
        s.cadastrarAluno(new Concluinte(nome, matricula), curso);
        System.out.println("Aluno concluinte cadastrado com sucesso!");
    }

    private static void cadResposta(Sistema s, Scanner input) throws EnadeException {
        System.out.println("Alunos cadastrados:");
        for(Aluno a : s.alunosSemQuestionario()){
            System.out.println(a);
        }

        String matricula = Entrada.lerLinha("Digite a matrícula do aluno que respondeu ao questionário: ", input);
        Aluno aluno = s.buscarAluno(matricula);
        aluno.cadastrarResposta();
        System.out.println("Resposta cadastrada com sucesso!");
    }

    private static void cadProva(Sistema s, Scanner input) throws EnadeException {
        System.out.println("Concluintes cadastrados:");
        for(Concluinte c : s.concluintesSemProva()){
            System.out.println(c);
        }

        String matricula = Entrada.lerLinha("Digite a matrícula do aluno que realizou a prova: ", input);
        Concluinte concluinte = s.buscarConcluinte(matricula);

        double n1 = Entrada.lerDouble("Digite o valor da nota de formação geral: ", input);
        double n2 = Entrada.lerDouble("Digite o valor da nota de conhecimentos específicos: ", input);

        concluinte.cadastrarProva(n1, n2);
        System.out.println("Prova cadastrada com sucesso!");
    }

    private static void listResultConceito(Sistema s, Scanner input) throws EnadeException {
        int conceito = Entrada.lerInteiro("Digite o conceito desejado (1 a 5): ", input);
        List<Curso> cursos = s.listarResultados(conceito);

        System.out.println("Cursos com conceito " + conceito + ":");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso com conceito " + conceito + " foi encontrado.");
            return;
        }

        for(Curso c : cursos){
            Area a = s.getAreaDoCurso(c);
            System.out.println(Entrada.formatar(c) + " - Área: " + Entrada.formatar(a));
        }
    }

    private static void listResultArea(Sistema s, Scanner input) {
        Entrada.imprimirAreas(s);

        String nomeArea = Entrada.lerLinha("Digite o nome da área: ", input);
        List<Curso> cursos = s.listarResultados(nomeArea);

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso na área " + nomeArea + " foi encontrado.");
            return;
        }

        Area area = s.getAreaDoCurso(cursos.get(0));
        System.out.println("Conceitos dos cursos da área " + Entrada.formatar(area) + ":");
        for(Curso c : cursos){
            // Locale.US para que a média sempre saia com ponto
            System.out.printf(Locale.US, "%s - Média: %.2f - Conceito: %d%n",
                    Entrada.formatar(c), c.calcularMedia(), c.calcularConceito());
        }
    }

    private static void listAlunosIrreg(Sistema s) {
        System.out.println("Alunos irregulares com o ENADE:");
        for(Aluno a : s.listarAlunosIrregulares()){
            System.out.println(a + " - Curso: " + Entrada.formatar(s.getCursoDoAluno(a)));
        }
    }

    private static Curso escolherCurso(Sistema s, Scanner input) throws EnadeException {
        System.out.println("Cursos cadastrados:");
        for(Curso c : s.getCursos()){
            System.out.println(Entrada.formatar(c));
        }

        String codigo = Entrada.lerLinha("Digite o código do curso do aluno: ", input);
        return s.buscarCurso(codigo);
    }

    private static void imprimirAreas(Sistema s) {
        System.out.println("Áreas cadastradas:");
        for(Area a : s.getAreas()){
            System.out.println(Entrada.formatar(a));
        }
    }

    /** Formato "Nome (CÓDIGO)" usado nas telas do enunciado. */
    private static String formatar(Curso c) {
        return c == null ? "-" : c.getNome() + " (" + c.getCodigo() + ")";
    }

    private static String formatar(Area a) {
        return a == null ? "-" : a.getNome() + " (" + a.getCodigo() + ")";
    }

}

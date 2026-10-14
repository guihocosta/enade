import java.util.ArrayList;

public class Sistema {
    private ArrayList<Area> areas;
    private ArrayList<Curso> cursos;
    private ArrayList<Aluno> alunos;

    public Sistema() {
        this.areas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public void cadastrarArea(Area area) {
        this.areas.add(area);
    }

    public void cadastrarCurso(Curso c, Area a){
        return;
    }

    public void cadastrarAluno(Aluno a, Curso c){
        return;
    }

    /** Cursos com o conceito informado (lista vazia se nenhum). */
    public ArrayList<Curso> listarResultados(int conceito){
        return new ArrayList<>();
    }

    /** Cursos da área de nome informado (lista vazia se área não existe ou sem cursos). */
    public ArrayList<Curso> listarResultados(String nomeArea){
        return new ArrayList<>();
    }

    public ArrayList<Aluno> listarAlunosIrregulares(){
        return new ArrayList<>();
    }

    /** Retorna null se não encontrar. */
    public Area buscarArea(String codigo){
        return null;
    }

    /** Retorna null se não encontrar. */
    public Curso buscarCurso(String codigo){
        return null;
    }

    /** Retorna null se não encontrar. */
    public Aluno buscarAluno(String matricula){
        return null;
    }

    public ArrayList<Aluno> alunosSemQuestionario(){
        return new ArrayList<>();
    }

    public ArrayList<Concluinte> concluintesSemProva(){
        return new ArrayList<>();
    }

    /** Retorna null se não encontrar. */
    public Area getAreaDoCurso(Curso c){
        return null;
    }

    /** Retorna null se não encontrar. */
    public Curso getCursoDoAluno(Aluno a){
        return null;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }
}

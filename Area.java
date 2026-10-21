import java.util.ArrayList;

public class Area {
    private String nome;
    private String codigo;
    private ArrayList<Curso> cursos;

    public Area(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.cursos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.nome + " - " + this.codigo;
    }

    public void adicionarCurso(Curso c){
        this.cursos.add(c);
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

}

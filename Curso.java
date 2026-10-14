import java.util.ArrayList;

public class Curso {
    private String nome, codigo;
    private ArrayList<Aluno> alunos;

    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno a){
        return;
    }

    public double calcularMedia(){
        return 0;
    }

    public int calcularConceito(){
        return 0;
    }

    public ArrayList<Ingressante> ingressantes(){
        return new ArrayList<>();
    }

    public ArrayList<Ingressante> ingressantes(int semestre){
        return new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return this.nome + " - " + this.codigo;
    }
}

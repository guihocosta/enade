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
        this.alunos.add(a);
    }

    public double calcularMedia(){
        double soma = 0;
        int qtdConcluintes = 0;
        for (Aluno a : alunos){
            if (a instanceof Concluinte c){
                soma += c.calcularNota();
                qtdConcluintes++;
            }
        }
        if (qtdConcluintes == 0) return 0;
        return soma / qtdConcluintes;
    }

    public int calcularConceito(){
        double media = calcularMedia();
        if (media >= 8) return 5;
        if (media >= 6) return 4;
        if (media >= 4) return 3;
        if (media >= 2) return 2;
        return 1;

    }

    public ArrayList<Ingressante> ingressantes(){
        ArrayList<Ingressante> ingressantes = new ArrayList<>();
        for (Aluno a : alunos){
            if (a instanceof Ingressante i){
                ingressantes.add(i);
            }
        }
        return ingressantes;
    }

    public ArrayList<Ingressante> ingressantes(int semestre){
        ArrayList<Ingressante> ingressantes = new ArrayList<>();
        for (Aluno a : alunos){
            if (a instanceof Ingressante i && i.getSemIngresso() == semestre){
                ingressantes.add(i);
            }
        }
        return ingressantes;
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

public class Aluno {
    private String nome, matricula;
    private boolean questionarioRespondido;

    public Aluno(String nome, String matricula){
        this.nome = nome;
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return this.nome + " (" + this.matricula + ")";
    }

    public boolean regular(){
        return true;
    }

    public void cadastrarResposta(){
        return;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isQuestionarioRespondido() {
        return questionarioRespondido;
    }

    public void setQuestionarioRespondido(boolean questionarioRespondido) {
        this.questionarioRespondido = questionarioRespondido;
    }
}

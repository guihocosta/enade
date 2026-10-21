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
        return this.questionarioRespondido;
    }

    public void cadastrarResposta(){
        this.questionarioRespondido = true;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean isQuestionarioRespondido() {
        return questionarioRespondido;
    }

}

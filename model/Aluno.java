package model;

import exception.DadoInvalidoException;

public class Aluno implements Comparable<Aluno> {
    private String nome, matricula;
    private boolean questionarioRespondido;

    public Aluno(String nome, String matricula) throws DadoInvalidoException {
        this.nome = nome;
        this.matricula = matricula;
    }

    // TODO (Guilherme): nome e, no empate, matrícula
    @Override
    public int compareTo(Aluno outro) {
        return 0;
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

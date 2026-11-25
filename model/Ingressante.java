package model;

import exception.DadoInvalidoException;

public class Ingressante extends Aluno {
    private int semIngresso;

    public Ingressante(String nome, String matricula, int semIngresso) throws DadoInvalidoException {
        super(nome, matricula);
        this.semIngresso = semIngresso;
    }

    @Override
    public String toString() {
        return super.toString() + " - INGRESSANTE";
    }

    public int getSemIngresso() {
        return semIngresso;
    }
}

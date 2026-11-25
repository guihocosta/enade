package model;

import exception.DadoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Curso implements Comparable<Curso> {
    private String nome, codigo;
    private ArrayList<Aluno> alunos;

    public Curso(String nome, String codigo) throws DadoInvalidoException {
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

    public List<Ingressante> ingressantes(){
        ArrayList<Ingressante> ingressantes = new ArrayList<>();
        for (Aluno a : alunos){
            if (a instanceof Ingressante i){
                ingressantes.add(i);
            }
        }
        return ingressantes;
    }

    public List<Ingressante> ingressantes(int semestre){
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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    // TODO (Guilherme): nome e, no empate, código
    @Override
    public int compareTo(Curso outro) {
        return 0;
    }

    @Override
    public String toString() {
        return this.nome + " - " + this.codigo;
    }
}

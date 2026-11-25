package model;

import exception.DadoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private String nome;
    private String codigo;
    private ArrayList<Curso> cursos;

    public Area(String nome, String codigo) throws DadoInvalidoException {
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

    public List<Curso> getCursos() {
        return cursos;
    }

}

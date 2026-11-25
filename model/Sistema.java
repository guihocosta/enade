package model;

import exception.JaCadastradoException;
import exception.NaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private ArrayList<Area> areas;
    private ArrayList<Curso> cursos;
    private ArrayList<Aluno> alunos;

    public Sistema() {
        this.areas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public void cadastrarArea(Area area) throws JaCadastradoException {
        this.areas.add(area);
    }

    public void cadastrarCurso(Curso c, Area a) throws JaCadastradoException {
        this.cursos.add(c);
        a.adicionarCurso(c);
    }

    public void cadastrarAluno(Aluno a, Curso c) throws JaCadastradoException {
        alunos.add(a);
        c.adicionarAluno(a);
    }

    /** Cursos com o conceito informado (lista vazia se nenhum). */
    public List<Curso> listarResultados(int conceito){
        ArrayList<Curso> cursosConceito = new ArrayList<>();
        for (Curso c : cursos){
            if (c.calcularConceito() == conceito){
                cursosConceito.add(c);
            }
        }
        return cursosConceito;
    }

    /** Cursos da área de nome informado (lista vazia se área não existe ou sem cursos). */
    public List<Curso> listarResultados(String nomeArea){
        ArrayList<Curso> cursosArea = new ArrayList<>();

        for (Area a : areas){
            if (a.getNome().equals(nomeArea)){
                cursosArea = new ArrayList<>(a.getCursos());
                break;
            }
        }

        return cursosArea;
    }

    public List<Aluno> listarAlunosIrregulares(){
        ArrayList<Aluno> alunosIrregulares = new ArrayList<>();
        for (Aluno a : alunos){
            if (!a.regular()){
                alunosIrregulares.add(a);
            }
        }
        return alunosIrregulares;
    }

    /** Retorna null se não encontrar. */
    public Area buscarArea(String codigo) throws NaoEncontradoException {
        for (Area a : areas)
            if (a.getCodigo().equals(codigo))
                return a;

        return null;
    }

    /** Retorna null se não encontrar. */
    public Curso buscarCurso(String codigo) throws NaoEncontradoException {
        for (Curso c : cursos)
            if (c.getCodigo().equals(codigo))
                return c;

        return null;
    }

    /** Retorna null se não encontrar. */
    public Aluno buscarAluno(String matricula) throws NaoEncontradoException {
        for (Aluno a : alunos)
            if (a.getMatricula().equals(matricula))
                return a;

        return null;
    }

    public List<Aluno> alunosSemQuestionario(){
        ArrayList<Aluno> alunosSem = new ArrayList<>();
        for (Aluno a : alunos)
            if (!a.isQuestionarioRespondido())
                alunosSem.add(a);

        return alunosSem;
    }

    public List<Concluinte> concluintesSemProva(){
        ArrayList<Concluinte> concluintesSem = new ArrayList<>();
        for (Aluno a : alunos)
            if (a instanceof Concluinte c){
                if (!c.isPresente())
                    concluintesSem.add(c);
            }
        return concluintesSem;
    }

    /** Retorna null se não encontrar. */
    public Area getAreaDoCurso(Curso c){
        for (Area a : areas){
            if (a.getCursos().contains(c))
                return a;
        }
        return null;
    }

    /** Retorna null se não encontrar. */
    public Curso getCursoDoAluno(Aluno a){
        for (Curso c : cursos){
            if (c.getAlunos().contains(a))
                return c;
        }
        return null;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}

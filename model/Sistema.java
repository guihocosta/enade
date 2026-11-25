package model;

import exception.DadoInvalidoException;
import exception.JaCadastradoException;
import exception.NaoEncontradoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sistema {
    // Chave: código ou matrícula. LinkedHashMap mantém a ordem de cadastro nas telas.
    private Map<String, Area> areas;
    private Map<String, Curso> cursos;
    private Map<String, Aluno> alunos;

    public Sistema() {
        this.areas = new LinkedHashMap<>();
        this.cursos = new LinkedHashMap<>();
        this.alunos = new LinkedHashMap<>();
    }

    public void cadastrarArea(Area area) throws JaCadastradoException {
        if (areas.containsKey(area.getCodigo()))
            throw new JaCadastradoException("Já existe uma área com o código " + area.getCodigo() + ".");

        areas.put(area.getCodigo(), area);
    }

    public void cadastrarCurso(Curso c, Area a) throws JaCadastradoException {
        if (cursos.containsKey(c.getCodigo()))
            throw new JaCadastradoException("Já existe um curso com o código " + c.getCodigo() + ".");

        cursos.put(c.getCodigo(), c);
        a.adicionarCurso(c);
    }

    public void cadastrarAluno(Aluno a, Curso c) throws JaCadastradoException {
        if (alunos.containsKey(a.getMatricula()))
            throw new JaCadastradoException("Já existe um aluno com a matrícula " + a.getMatricula() + ".");

        alunos.put(a.getMatricula(), a);
        c.adicionarAluno(a);
    }

    /** Cursos com o conceito informado, em ordem alfabética (lista vazia se nenhum). */
    public List<Curso> listarResultados(int conceito) throws DadoInvalidoException {
        if (conceito < 1 || conceito > 5)
            throw new DadoInvalidoException("O conceito deve estar entre 1 e 5.");

        List<Curso> cursosConceito = new ArrayList<>();
        for (Curso c : cursos.values()){
            if (c.calcularConceito() == conceito){
                cursosConceito.add(c);
            }
        }
        Collections.sort(cursosConceito);
        return cursosConceito;
    }

    /** Cursos da área de nome informado, por desempenho (lista vazia se área não existe ou sem cursos). */
    public List<Curso> listarResultados(String nomeArea){
        List<Curso> cursosArea = new ArrayList<>();

        for (Area a : areas.values()){
            if (a.getNome().equals(nomeArea)){
                cursosArea = new ArrayList<>(a.getCursos());
                break;
            }
        }

        cursosArea.sort(new ComparadorDesempenho());
        return cursosArea;
    }

    /** Alunos irregulares, em ordem alfabética. */
    public List<Aluno> listarAlunosIrregulares(){
        List<Aluno> alunosIrregulares = new ArrayList<>();
        for (Aluno a : alunos.values()){
            if (!a.regular()){
                alunosIrregulares.add(a);
            }
        }
        Collections.sort(alunosIrregulares);
        return alunosIrregulares;
    }

    public Area buscarArea(String codigo) throws NaoEncontradoException {
        Area area = areas.get(codigo);
        if (area == null)
            throw new NaoEncontradoException("Área não encontrada.");

        return area;
    }

    public Curso buscarCurso(String codigo) throws NaoEncontradoException {
        Curso curso = cursos.get(codigo);
        if (curso == null)
            throw new NaoEncontradoException("Curso não encontrado.");

        return curso;
    }

    public Aluno buscarAluno(String matricula) throws NaoEncontradoException {
        Aluno aluno = alunos.get(matricula);
        if (aluno == null)
            throw new NaoEncontradoException("Aluno não encontrado.");

        return aluno;
    }

    public Concluinte buscarConcluinte(String matricula) throws NaoEncontradoException {
        if (alunos.get(matricula) instanceof Concluinte concluinte)
            return concluinte;

        throw new NaoEncontradoException("Concluinte não encontrado.");
    }

    public List<Aluno> alunosSemQuestionario(){
        List<Aluno> alunosSem = new ArrayList<>();
        for (Aluno a : alunos.values())
            if (!a.isQuestionarioRespondido())
                alunosSem.add(a);

        return alunosSem;
    }

    public List<Concluinte> concluintesSemProva(){
        List<Concluinte> concluintesSem = new ArrayList<>();
        for (Aluno a : alunos.values())
            if (a instanceof Concluinte c){
                if (!c.isPresente())
                    concluintesSem.add(c);
            }
        return concluintesSem;
    }

    /** Retorna null se não encontrar. */
    public Area getAreaDoCurso(Curso c){
        for (Area a : areas.values()){
            if (a.getCursos().contains(c))
                return a;
        }
        return null;
    }

    /** Retorna null se não encontrar. */
    public Curso getCursoDoAluno(Aluno a){
        for (Curso c : cursos.values()){
            if (c.getAlunos().contains(a))
                return c;
        }
        return null;
    }

    public List<Area> getAreas() {
        return new ArrayList<>(areas.values());
    }

    public List<Curso> getCursos() {
        return new ArrayList<>(cursos.values());
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos.values());
    }
}

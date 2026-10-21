public class Ingressante extends Aluno {
    private int semIngresso;

    public Ingressante(String nome, String matricula, int semIngresso) {
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

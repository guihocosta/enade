public class Concluinte extends Aluno{
    private double notaFormacaoGeral, notaComponenteEspecifico;
    private boolean presente;

    public Concluinte(String nome, String matricula) {
        super(nome, matricula);
    }

    @Override
    public String toString() {
        return super.toString() + " - CONCLUINTE";
    }

    public void cadastrarProva(double n1, double n2){
        return;
    }

    public double calcularNota(){
        return 0;
    }

    public boolean regular(){
        return true;
    }

    public double getNotaFormacaoGeral() {
        return notaFormacaoGeral;
    }

    public void setNotaFormacaoGeral(double notaFormacaoGeral) {
        this.notaFormacaoGeral = notaFormacaoGeral;
    }

    public double getNotaComponenteEspecifico() {
        return notaComponenteEspecifico;
    }

    public void setNotaComponenteEspecifico(double notaComponenteEspecifico) {
        this.notaComponenteEspecifico = notaComponenteEspecifico;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}

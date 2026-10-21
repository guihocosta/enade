public class Concluinte extends Aluno{
    private double notaFormacaoGeral, notaComponenteEspecifico;
    private boolean presente;

    public Concluinte(String nome, String matricula) {
        super(nome, matricula);
        this.presente = false;
    }

    @Override
    public String toString() {
        return super.toString() + " - CONCLUINTE";
    }

    public void cadastrarProva(double n1, double n2){
        this.presente = true;
        this.notaFormacaoGeral = n1;
        this.notaComponenteEspecifico = n2;
    }

    public double calcularNota(){
        return (this.notaFormacaoGeral * 3 + this.notaComponenteEspecifico * 7) / 10;
    }

    @Override
    public boolean regular(){
        return super.regular() && this.presente;
    }

    public double getNotaFormacaoGeral() {
        return notaFormacaoGeral;
    }

    public double getNotaComponenteEspecifico() {
        return notaComponenteEspecifico;
    }

    public boolean isPresente() {
        return presente;
    }
}

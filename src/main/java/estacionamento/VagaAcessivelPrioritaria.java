package estacionamento;

public class VagaAcessivelPrioritaria implements PoliticaAlocacao {

    public Vaga alocar(Estacionamento estacionamento) {
        for (Vaga vaga : estacionamento) {
            if (!vaga.isOcupada() && vaga.isAcessivel()) {
                return vaga;
            }
        }
        for (Vaga vaga : estacionamento) {
            if (!vaga.isOcupada()) {
                return vaga;
            }
        }
        throw new IllegalArgumentException("Estacionamento lotado");
    }
}

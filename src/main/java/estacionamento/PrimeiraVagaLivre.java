package estacionamento;

public class PrimeiraVagaLivre implements PoliticaAlocacao {

    public Vaga alocar(Estacionamento estacionamento) {
        for (Vaga vaga : estacionamento) {
            if (!vaga.isOcupada()) {
                return vaga;
            }
        }
        throw new IllegalArgumentException("Estacionamento lotado");
    }
}

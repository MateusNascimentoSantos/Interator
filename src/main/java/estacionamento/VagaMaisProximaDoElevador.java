package estacionamento;

public class VagaMaisProximaDoElevador implements PoliticaAlocacao {

    public Vaga alocar(Estacionamento estacionamento) {
        Vaga escolhida = null;
        for (Vaga vaga : estacionamento) {
            if (!vaga.isOcupada()) {
                if (escolhida == null || vaga.getDistanciaElevador() < escolhida.getDistanciaElevador()) {
                    escolhida = vaga;
                }
            }
        }
        if (escolhida == null) {
            throw new IllegalArgumentException("Estacionamento lotado");
        }
        return escolhida;
    }
}

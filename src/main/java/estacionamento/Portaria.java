package estacionamento;

public class Portaria {

    private PoliticaAlocacao politicaAlocacao;

    public void setPoliticaAlocacao(PoliticaAlocacao politicaAlocacao) {
        this.politicaAlocacao = politicaAlocacao;
    }

    public String estacionar(Estacionamento estacionamento) {
        Vaga vaga = politicaAlocacao.alocar(estacionamento);
        vaga.ocupar();
        return "Vaga " + vaga.getIdentificacao();
    }

    public String relatorio(Estacionamento estacionamento) {
        int livres = 0;
        int total = 0;
        for (Vaga vaga : estacionamento) {
            total++;
            if (!vaga.isOcupada()) {
                livres++;
            }
        }
        return livres + " livres de " + total;
    }

}

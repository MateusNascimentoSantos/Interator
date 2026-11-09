package estacionamento;

public class Vaga {

    private int andar;
    private int numero;
    private int distanciaElevador;
    private boolean acessivel;
    private boolean ocupada;

    public Vaga(int andar, int numero, int distanciaElevador, boolean acessivel) {
        this.andar = andar;
        this.numero = numero;
        this.distanciaElevador = distanciaElevador;
        this.acessivel = acessivel;
    }

    public int getAndar() {
        return andar;
    }

    public int getNumero() {
        return numero;
    }

    public int getDistanciaElevador() {
        return distanciaElevador;
    }

    public boolean isAcessivel() {
        return acessivel;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar() {
        this.ocupada = true;
    }

    public void liberar() {
        this.ocupada = false;
    }

    public String getIdentificacao() {
        return andar + "-" + numero;
    }
}

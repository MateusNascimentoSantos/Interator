package estacionamento;

import java.util.Iterator;

public class Estacionamento implements Iterable<Vaga> {

    private Vaga[][] vagas;

    public Estacionamento(Vaga[]... vagas) {
        this.vagas = vagas;
    }

    @Override
    public Iterator<Vaga> iterator() {
        return new VagaIterator(vagas);
    }

}

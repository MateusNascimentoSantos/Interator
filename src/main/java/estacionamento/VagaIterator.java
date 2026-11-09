package estacionamento;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VagaIterator implements Iterator<Vaga> {

    private Vaga[][] vagas;
    private int andar = 0;
    private int numero = 0;

    public VagaIterator(Vaga[][] vagas) {
        this.vagas = vagas;
    }

    @Override
    public boolean hasNext() {
        while (andar < vagas.length && numero >= vagas[andar].length) {
            andar++;
            numero = 0;
        }
        return andar < vagas.length;
    }

    @Override
    public Vaga next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Nao ha mais vagas");
        }
        return vagas[andar][numero++];
    }

}

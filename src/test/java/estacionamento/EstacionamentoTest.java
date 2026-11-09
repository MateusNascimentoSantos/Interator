package estacionamento;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EstacionamentoTest {

    @Test
    void devePercorrerTodasAsVagasAtravessandoOsAndares() {
        Estacionamento estacionamento = new Estacionamento(
                new Vaga[]{new Vaga(0, 1, 40, false), new Vaga(0, 2, 25, true), new Vaga(0, 3, 10, false)},
                new Vaga[]{new Vaga(1, 1, 30, true), new Vaga(1, 2, 5, false)}
        );

        Portaria portaria = new Portaria();
        assertEquals("5 livres de 5", portaria.relatorio(estacionamento));
    }

    @Test
    void deveAlocarPrimeiraVagaLivre() {
        Estacionamento estacionamento = new Estacionamento(
                new Vaga[]{new Vaga(0, 1, 40, false), new Vaga(0, 2, 25, true), new Vaga(0, 3, 10, false)},
                new Vaga[]{new Vaga(1, 1, 30, true), new Vaga(1, 2, 5, false)}
        );

        Portaria portaria = new Portaria();
        portaria.setPoliticaAlocacao(new PrimeiraVagaLivre());
        assertEquals("Vaga 0-1", portaria.estacionar(estacionamento));
    }

    @Test
    void deveAlocarVagaMaisProximaDoElevador() {
        Estacionamento estacionamento = new Estacionamento(
                new Vaga[]{new Vaga(0, 1, 40, false), new Vaga(0, 2, 25, true), new Vaga(0, 3, 10, false)},
                new Vaga[]{new Vaga(1, 1, 30, true), new Vaga(1, 2, 5, false)}
        );

        Portaria portaria = new Portaria();
        portaria.setPoliticaAlocacao(new VagaMaisProximaDoElevador());
        assertEquals("Vaga 1-2", portaria.estacionar(estacionamento));
    }

    @Test
    void deveAlocarVagaAcessivelPrioritaria() {
        Estacionamento estacionamento = new Estacionamento(
                new Vaga[]{new Vaga(0, 1, 40, false), new Vaga(0, 2, 25, true), new Vaga(0, 3, 10, false)},
                new Vaga[]{new Vaga(1, 1, 30, true), new Vaga(1, 2, 5, false)}
        );

        Portaria portaria = new Portaria();
        portaria.setPoliticaAlocacao(new VagaAcessivelPrioritaria());
        assertEquals("Vaga 0-2", portaria.estacionar(estacionamento));
    }

    @Test
    void deveAlocarVagaComumQuandoNaoHaAcessivelLivre() {
        Estacionamento estacionamento = new Estacionamento(
                new Vaga[]{new Vaga(0, 1, 40, false), new Vaga(0, 2, 25, true), new Vaga(0, 3, 10, false)},
                new Vaga[]{new Vaga(1, 1, 30, true), new Vaga(1, 2, 5, false)}
        );
        for (Vaga vaga : estacionamento) {
            if (vaga.isAcessivel()) {
                vaga.ocupar();
            }
        }

        Portaria portaria = new Portaria();
        portaria.setPoliticaAlocacao(new VagaAcessivelPrioritaria());
        assertEquals("Vaga 0-1", portaria.estacionar(estacionamento));
    }

    @Test
    void deveAtualizarRelatorioAposOcuparUmaVaga() {
        Estacionamento estacionamento = new Estacionamento(
                new Vaga[]{new Vaga(0, 1, 40, false), new Vaga(0, 2, 25, true), new Vaga(0, 3, 10, false)},
                new Vaga[]{new Vaga(1, 1, 30, true), new Vaga(1, 2, 5, false)}
        );

        Portaria portaria = new Portaria();
        portaria.setPoliticaAlocacao(new PrimeiraVagaLivre());
        portaria.estacionar(estacionamento);

        assertEquals("4 livres de 5", portaria.relatorio(estacionamento));
    }

    @Test
    void naoDeveAlocarComEstacionamentoLotado() {
        try {
            Estacionamento estacionamento = new Estacionamento(
                    new Vaga[]{new Vaga(0, 1, 40, false)},
                    new Vaga[]{new Vaga(1, 1, 30, true)}
            );
            for (Vaga vaga : estacionamento) {
                vaga.ocupar();
            }

            Portaria portaria = new Portaria();
            portaria.setPoliticaAlocacao(new PrimeiraVagaLivre());
            portaria.estacionar(estacionamento);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Estacionamento lotado", e.getMessage());
        }
    }

    @Test
    void devePularAndarSemVagas() {
        Estacionamento estacionamento = new Estacionamento(
                new Vaga[]{new Vaga(0, 1, 10, false)},
                new Vaga[]{},
                new Vaga[]{new Vaga(2, 1, 20, false)}
        );

        Portaria portaria = new Portaria();
        portaria.setPoliticaAlocacao(new PrimeiraVagaLivre());

        assertEquals("2 livres de 2", portaria.relatorio(estacionamento));
        assertEquals("Vaga 0-1", portaria.estacionar(estacionamento));
        assertEquals("Vaga 2-1", portaria.estacionar(estacionamento));
    }

    @Test
    void naoDevePercorrerAlemDaUltimaVaga() {
        try {
            Estacionamento estacionamento = new Estacionamento(new Vaga[]{});
            Iterator<Vaga> iterator = estacionamento.iterator();
            iterator.next();
            fail();
        } catch (NoSuchElementException e) {
            assertEquals("Nao ha mais vagas", e.getMessage());
        }
    }

}

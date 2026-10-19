package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


class RegistroStabilimentoTest {

    @Test
    void idOmbrelloniFilaInesistenteMinore() {
        RegistroStabilimento registro = new RegistroStabilimento();
        List<Long> lista = new ArrayList<>();

        lista = registro.idOmbrelloniFila(0);
        int esito = lista.size();

        Assertions.assertEquals(0, esito);
    }

    @Test
    void idOmbrelloniFilaInesistenteMaggiore() {
        RegistroStabilimento registro = new RegistroStabilimento();
        List<Long> lista = new ArrayList<>();

        lista = registro.idOmbrelloniFila(4);
        int esito = lista.size();

        Assertions.assertEquals(0, esito);
    }

    @Test
    void idOmbrelloniFilaEsistente() {
        RegistroStabilimento registro = new RegistroStabilimento();
        List<Long> lista = new ArrayList<>();

        lista = registro.idOmbrelloniFila(1);
        int esito = lista.size();

        Assertions.assertNotEquals(0, esito);
    }



    @Test
    void ultimoPostoPerFila() {
    }
}
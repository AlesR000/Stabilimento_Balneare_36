package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlStabilimentoTest {

    @Test
    void salvaOmbrelloneFilaInesistente() {
        ControlStabilimento control = new ControlStabilimento();

        boolean esito = control.salvaOmbrellone("0",10L);

        assertFalse(esito);
    }

    @Test
    void salvaOmbrelloneFilaStringa(){
        ControlStabilimento control = new ControlStabilimento();

        boolean esito = control.salvaOmbrellone("abcdefghi",10L);

        assertFalse(esito);
    }

    @Test
    void salvaServizioPrezzoVuoto() {
        ControlStabilimento control = new ControlStabilimento();

        boolean esito = control.salvaServizio("Servizio", 10, "0", "Descrizione");

        Assertions.assertFalse(esito);
    }

    @Test
    void salvaServizioPrezzoStringa(){
        ControlStabilimento control = new ControlStabilimento();

        boolean esito = control.salvaServizio("Servizio", 10, "STRINGA", "Descrizione");

        assertFalse(esito);
    }

    @Test
    void rimuoviOmbrelloneFilaZero() {
        ControlStabilimento control = new ControlStabilimento();

        boolean esito = control.rimuoviOmbrellone("0");

        assertFalse(esito);
    }

    @Test
    void rimuoviOmbrelloneFilaStringa() {
        ControlStabilimento control = new ControlStabilimento();
        boolean esito = control.rimuoviOmbrellone("STRINGA");
        assertFalse(esito);
    }

    @Test
    void rimuoviOmbrelloneFilaPrima(){
        ControlStabilimento control = new ControlStabilimento();
        boolean esito = control.rimuoviOmbrellone("prima fila");
        assertTrue(esito);
    }

}
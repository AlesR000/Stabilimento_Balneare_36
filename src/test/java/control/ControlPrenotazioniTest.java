package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControlPrenotazioniTest {

    @Test
    void eliminaPrenotazioneTardiTest() {
        ControlPrenotazioni control = new ControlPrenotazioni();
        LocalDate date = LocalDate.now().plusWeeks(1);
        Long mockId = (Long) 1L;

        boolean esito = control.eliminaPrenotazione(date , mockId);

        Assertions.assertFalse(esito);
    }

    @Test
    void distaDueSettimaneFalso() {
        ControlPrenotazioni control = new ControlPrenotazioni();
        LocalDate date = LocalDate.now().plusWeeks(3);

        boolean esito = control.distaDueSettimane(date);

        Assertions.assertFalse(esito);
    }

    @Test
    void distaDueSettimaneVero() {
        ControlPrenotazioni control = new ControlPrenotazioni();
        LocalDate date = LocalDate.now().plusWeeks(1);

        boolean esito = control.distaDueSettimane(date);

        Assertions.assertTrue(esito);
    }

    @Test
    void dataPassataFalso() {
        ControlPrenotazioni control = new ControlPrenotazioni();
        LocalDate date = LocalDate.now().plusWeeks(1);

        boolean esito = control.dataPassata(date);
        Assertions.assertFalse(esito);
    }

    @Test
    void dataPassataVero() {
        ControlPrenotazioni control = new ControlPrenotazioni();
        LocalDate date = LocalDate.now().minusWeeks(1);

        boolean esito = control.dataPassata(date);
        Assertions.assertTrue(esito);
    }
}
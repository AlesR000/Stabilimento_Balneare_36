package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistroPrenotazioneTest {

    @Test
    void cercaPrenotazioniClienteInesistente() {
        RegistroPrenotazione reg = new RegistroPrenotazione();

        List<Prenotazione> prenotazioni = reg.cercaPrenotazioniCliente(10222L);
        boolean esito;

        if (prenotazioni.isEmpty()) {
             esito = false;
        } else {
            esito = true;
        }

        assertFalse(esito);
    }

    @Test
    void cercaPrenotazioniPerDataSenzaPrenotazioni() {
        RegistroPrenotazione reg = new RegistroPrenotazione();
        LocalDate data = LocalDate.now().minusYears(1);

        List<Prenotazione> prenotazioni = reg.cercaPrenotazioniPerData(data);
        boolean esito;

        if (prenotazioni.isEmpty()) {
            esito = false;
        } else {
            esito = true;
        }

        assertFalse(esito);

    }
}
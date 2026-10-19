package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistroUtenteTest {

    @Test
    void registroCliente() {
        RegistroUtente registroUtente = new RegistroUtente();

        Cliente cliente = new Cliente();

        boolean esito = registroUtente.registroCliente(cliente);
        assertTrue(esito);

        registroUtente.eliminarUtente(cliente);
    }

    @Test
    void registroGestore() {
        RegistroUtente registroUtente = new RegistroUtente();

        Gestore gestore = new Gestore();

        boolean esito = registroUtente.registroGestore(gestore);
        assertTrue(esito);

        registroUtente.eliminarUtente(gestore);
    }

}
package entity;

import database.GestorePersistenza;

import java.util.Map;

public class RegistroUtente {

    private GestorePersistenza gestorePersistenza;
    public RegistroUtente() {gestorePersistenza = new GestorePersistenza();}

    //Metodi Registra
    public boolean registroUtente (Utente utente) {
        return gestorePersistenza.salva(utente);
    }

    public boolean registroCliente (Cliente cliente) {
        return registroUtente(cliente);
    }

    public boolean registroGestore (Gestore gestore) {
        return registroUtente(gestore);
    }

    public Utente cercaUtente(
            String email,
            String password,
            String classificazione) {

        Map<String, Object> campi = Map.of(
                "email", email
        );

        if ("Cliente".equals(classificazione)) {
            return gestorePersistenza.cercaPrimoPerCampi(
                    Cliente.class, campi);
        } else if ("Gestore".equals(classificazione)) {
            return gestorePersistenza.cercaPrimoPerCampi(
                    Gestore.class, campi);
        }

        return null;
    }

    //Metodi Elimina
    public boolean eliminarUtente(Utente utente) {
        Long idUtente = utente.getId();

        return gestorePersistenza.elimina(Utente.class, idUtente);
    }

}

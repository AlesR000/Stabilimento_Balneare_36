package entity;

import database.GestorePersistenza;

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

    //Metodi Elimina
    public boolean eliminarUtente(Utente utente) {
        Long idUtente = utente.getId();

        return gestorePersistenza.elimina(Utente.class, idUtente);
    }

}

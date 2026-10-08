package entity;

import database.GestorePersistenza;

import java.time.LocalDate;
import java.util.List;

public class RegistroPrenotazione {


    private GestorePersistenza gestorePersistenza;
    public RegistroPrenotazione(){
        gestorePersistenza = new GestorePersistenza();
    }

    //Metodi Registra
    public boolean RegistraPrenotazione(
            LocalDate data,
            long prezzo,
            Cliente cliente,
            Ombrellone ombrellone,
            List<ServizioAggiuntivo> servizi
    ){

        //Crea oggetto prenotazione
        Prenotazione prenotazione = new Prenotazione(data, prezzo, cliente, ombrellone, servizi);

        boolean esito = gestorePersistenza.salva(prenotazione);

        return esito;

    }

    //Metodi Ricerca
    public List<Prenotazione> cercaPrenotazioni(){
        return gestorePersistenza.cercaPerClasse(Prenotazione.class);
    }

    public List<Prenotazione> cercaPrenotazioniData(LocalDate data){
        return gestorePersistenza.cercaPerCampo(Prenotazione.class, "dataPrenotazione", data);
    }

    //Metodi Elimina
    public boolean eliminaPrenotazione(Prenotazione prenotazione) {
        Long idPrenotazione = prenotazione.getId();

        return gestorePersistenza.elimina(Prenotazione.class, idPrenotazione);
    }



}

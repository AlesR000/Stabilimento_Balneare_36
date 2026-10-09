package control;

import entity.Cliente;
import entity.Ombrellone;
import entity.RegistroPrenotazione;
import entity.RegistroStabilimento;
import entity.ServizioAggiuntivo;

import java.time.LocalDate;
import java.util.List;

public class ControlPrenotazioni {

    //Funzioni di salvataggio prenotazione
    public boolean salvaPrenotazione( LocalDate data,
                                      long prezzo,
                                      Cliente cliente,
                                      Ombrellone ombrellone,
                                      List<ServizioAggiuntivo> servizi){

        if(dataPassata(data)){
            System.out.println("Impossibile prenotare per una data passata");
            return false;
        }

        RegistroPrenotazione registroPrenotazione = new RegistroPrenotazione();

        registroPrenotazione.RegistraPrenotazione(data,prezzo,cliente,ombrellone,servizi);


    }

    //Funzioni di Ricerca prenotazione

    //Funzioni di Elimina prenotazione


    //Funzioni ausiliari per controlli vari
    public boolean distaDueSettimane (LocalDate dataPrenotazione){

        LocalDate oggi = LocalDate.now();
        return !dataPrenotazione.isBefore(oggi.plusWeeks(2));

    }

    public boolean dataPassata (LocalDate dataPrenotazione){
        LocalDate oggi = LocalDate.now();
        return !oggi.isBefore(dataPrenotazione);
    }

    public long prezzoPrenotazione (Ombrellone ombrellone, List<ServizioAggiuntivo> servizi){



    }

}

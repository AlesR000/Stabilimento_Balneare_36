package entity;

import database.GestorePersistenza;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Classe intermedia per registrare elementi nello stabilimento
public class RegistroStabilimento {

    GestorePersistenza gestorePersistenza;
    public RegistroStabilimento(){gestorePersistenza = new GestorePersistenza();}

    //Metodi Registra
    public boolean RegistraOmbrellone (long prezzoStandard, int fila, int posto){

        if(gestorePersistenza.cercaPerClasse(Ombrellone.class) != null)
        prezzoStandard = gestorePersistenza.cercaPerClasse(Ombrellone.class).getFirst().getPrezzoStandard();

        Ombrellone ombrellone = new Ombrellone(prezzoStandard,fila,posto);
        return gestorePersistenza.salva(ombrellone);

    }

    public boolean RegistraServizio (String nome, long prezzoStandard, int quantita, String descrizione){

        ServizioAggiuntivo servizioAggiuntivo = new ServizioAggiuntivo(nome,prezzoStandard,quantita,descrizione);
        return gestorePersistenza.salva(servizioAggiuntivo);
    }

    public boolean RegistraTariffaOmbrellone (long prezzo,
                                              LocalDate dataInizio,
                                              LocalDate dataFine,
                                              int fila){

        List<Ombrellone> ombrelloni = gestorePersistenza.cercaPerCampo(Ombrellone.class, "fila", fila);

        Tariffa tariffa = new Tariffa(prezzo, dataInizio, dataFine, ombrelloni);
        return gestorePersistenza.salva(tariffa);

    }

    public boolean RegistraTariffaServizio (long prezzo,
                                            LocalDate dataInizio,
                                            LocalDate dataFine,
                                            ServizioAggiuntivo servizioAggiuntivo){

        Tariffa tariffa = new Tariffa(prezzo, dataInizio, dataFine, servizioAggiuntivo);
        return gestorePersistenza.salva(tariffa);

    }


    //Metodi Elimina
    public boolean eliminaOmbrellone(int fila){
        //Elimina l'ultimo ombrellone della fila
        List<Ombrellone> ombrelloni = gestorePersistenza.cercaPerCampoOrdinato(
                Ombrellone.class,
                "fila",
                fila, "posto" ,
                false);

        Long idOmbrellone = ombrelloni.get(0).getId();

        return gestorePersistenza.elimina(Ombrellone.class, idOmbrellone);
    }

    public boolean eliminaServizio (ServizioAggiuntivo servizioAggiuntivo){
        Long idServizio = servizioAggiuntivo.getId();
        return gestorePersistenza.elimina(ServizioAggiuntivo.class, idServizio);
    }

    public boolean eliminaTariffa(Tariffa tariffa){
        Long idTariffa = tariffa.getId();
        return gestorePersistenza.elimina(Tariffa.class, idTariffa);
    }

    //Metodi Ricerca
    public List<Tariffa> tariffe(){

    }

    public long tariffaOmbrellonePerData(Ombrellone ombrellone, LocalDate data){

    }

    public long tariffaServizioPerData(ServizioAggiuntivo servizioAggiuntivo, LocalDate data){

    }

    //Metodi Aggiorna
    public void aggiornaServizio (String nome, long prezzoStandard, int quantita, String descrizione){

        ServizioAggiuntivo servizioAggiuntivo = new ServizioAggiuntivo(nome,prezzoStandard,quantita,descrizione);
        gestorePersistenza.aggiorna(servizioAggiuntivo);

    }

    public void aggiornaTariffa (Tariffa tariffa){
        gestorePersistenza.aggiorna(tariffa);
    }

}

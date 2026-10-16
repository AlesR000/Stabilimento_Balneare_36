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
    public boolean registraOmbrellone(long prezzoStandard, int fila, int posto){

        if(ultimoPostoPerFila(fila) > 0) {
            prezzoStandard = gestorePersistenza.cercaPerCampoOrdinato(Ombrellone.class,
                            "fila", fila, "posto", false)
                    .getFirst()
                    .getPrezzoStandard();
        }
        Ombrellone ombrellone = new Ombrellone(prezzoStandard,fila,posto);
        return gestorePersistenza.salva(ombrellone);

    }

    public boolean registraServizio(String nome, long prezzoStandard, int quantita, String descrizione){

        ServizioAggiuntivo servizioAggiuntivo = new ServizioAggiuntivo(nome,prezzoStandard,quantita,descrizione);
        return gestorePersistenza.salva(servizioAggiuntivo);
    }

    public boolean registraTariffaOmbrellone(long prezzo,
                                             LocalDate dataInizio,
                                             LocalDate dataFine,
                                             int fila){

        List<Ombrellone> ombrelloni = gestorePersistenza.cercaPerCampo(Ombrellone.class, "fila", fila);

        Tariffa tariffa = new Tariffa(prezzo, dataInizio, dataFine, ombrelloni);
        return gestorePersistenza.salva(tariffa);

    }

    public boolean registraTariffaServizio(long prezzo,
                                           LocalDate dataInizio,
                                           LocalDate dataFine,
                                           ServizioAggiuntivo servizioAggiuntivo){

        Tariffa tariffa = new Tariffa(prezzo, dataInizio, dataFine, servizioAggiuntivo);
        return gestorePersistenza.salva(tariffa);

    }


    //Metodi Elimina
    public boolean eliminaOmbrellone(int fila){

        //Elimina l'ultimo ombrellone della fila
        if(ultimoPostoPerFila(fila) > 0) {
            Long idOmbrellone = gestorePersistenza.cercaPerCampoOrdinato(Ombrellone.class,
                            "fila", fila, "posto", false)
                    .getFirst()
                    .getId();

            return gestorePersistenza.elimina(Ombrellone.class, idOmbrellone);
        }else{
            System.out.print("non sono presenti ombrelloni");
            return false;
        }

    }

    public boolean eliminaServizio (Long id){

        return gestorePersistenza.elimina(ServizioAggiuntivo.class, id);

    }

    public boolean eliminaTariffa(Tariffa tariffa){
        Long idTariffa = tariffa.getId();
        return gestorePersistenza.elimina(Tariffa.class, idTariffa);
    }

    //Metodi Ricerca
    public List<Long> idOmbrelloniFila(int fila){
        List<Ombrellone> ombrelloni = gestorePersistenza.cercaPerCampoOrdinato(Ombrellone.class,"fila",fila,"posto",true);
        List<Long> idOmbrelloniFila = new ArrayList<>();

        for (Ombrellone ombrellone: ombrelloni){
            idOmbrelloniFila.add(ombrellone.getId());
        }

        return idOmbrelloniFila;
    }

    public List<ServizioAggiuntivo> ricercaServizi(){
        return gestorePersistenza.cercaPerClasse(ServizioAggiuntivo.class);
    }

    public List<Tariffa> ricercaTariffe(){
        return gestorePersistenza.cercaPerClasse(Tariffa.class);
    }
/*
    public long tariffaOmbrellonePerData(Ombrellone ombrellone, LocalDate data){

    }

    public long tariffaServizioPerData(ServizioAggiuntivo servizioAggiuntivo, LocalDate data){

    }


     */
    //Metodi Aggiorna
    public ServizioAggiuntivo aggiornaServizio (Long id, String nome, long prezzoStandard, int quantita, String descrizione){

        ServizioAggiuntivo servizioAggiornato = gestorePersistenza.trovaPerId(ServizioAggiuntivo.class, id);

        servizioAggiornato.setNome(nome);
        servizioAggiornato.setPrezzoStandard(prezzoStandard);
        servizioAggiornato.setQuantita(quantita);
        servizioAggiornato.setDescrizione(descrizione);

        return gestorePersistenza.aggiorna(servizioAggiornato);

    }

    public void aggiornaTariffa (Tariffa tariffa){
        gestorePersistenza.aggiorna(tariffa);
    }

    //Metodi ausiliari
    public int ultimoPostoPerFila(int fila){
        List<Ombrellone> ombrelloni = gestorePersistenza.cercaPerCampoOrdinato(
                Ombrellone.class,
                "fila",
                fila,
                "posto",
                false);

        if(ombrelloni.size() == 0){
            return 0;
        }

        return ombrelloni.getFirst().getPosto();

    }

}

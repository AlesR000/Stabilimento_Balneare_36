package entity;

import database.GestorePersistenza;
import database.JpaUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistroPrenotazione {


    private GestorePersistenza gestorePersistenza;
    public RegistroPrenotazione(){
        gestorePersistenza = new GestorePersistenza();
    }

    //Metodi Registra
    public boolean RegistraPrenotazione(
            LocalDate data,
            long prezzo,
            Long idCliente,
            Long idOmbrellone,
            Map<Long, Integer> idServizi
    ){
        //Crea oggetto prenotazione
        Ombrellone ombrellone = gestorePersistenza.trovaPerId(Ombrellone.class, idOmbrellone);
        Cliente cliente = gestorePersistenza.trovaPerId(Cliente.class, idCliente);
        Prenotazione prenotazione = new Prenotazione(data, prezzo, cliente, ombrellone);

        if(idServizi.isEmpty()){

            return gestorePersistenza.salva(prenotazione);
        }

        idServizi.forEach((idServizio, quantita) -> {
            ServizioAggiuntivo servizio = gestorePersistenza.trovaPerId(ServizioAggiuntivo.class, idServizio);
            PrenotazioneServizio prenotazioneServizio = new PrenotazioneServizio(prenotazione, servizio, quantita);

            prenotazione.aggiungiPrenotazioneServizio(prenotazioneServizio);
        });

        return gestorePersistenza.salva(prenotazione);
    }

    //Metodi Ricerca
    public List<Prenotazione> cercaPrenotazioni(){
        return gestorePersistenza.cercaPerClasse(Prenotazione.class);
    }

    public List<Prenotazione> cercaPrenotazioniCliente(Long idCliente){
        EntityManager em = JpaUtil.getInstance().getEntityManager();

        try {
            return em.createQuery(
                            "SELECT DISTINCT p FROM Prenotazione p " +
                                    "LEFT JOIN FETCH p.ombrellone " +
                                    "LEFT JOIN FETCH p.prenotazioniConServizio ps " +
                                    "LEFT JOIN FETCH ps.servizio " +
                                    "WHERE p.cliente.id = :idCliente",
                            Prenotazione.class
                    )
                    .setParameter("idCliente", idCliente)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Prenotazione> cercaPrenotazioniPerData(LocalDate data) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();

        try {
            return em.createQuery(
                            "SELECT DISTINCT p FROM Prenotazione p " +
                                    "LEFT JOIN FETCH p.ombrellone " +
                                    "LEFT JOIN FETCH p.prenotazioniConServizio ps " +
                                    "LEFT JOIN FETCH ps.servizio " +
                                    "WHERE p.dataPrenotazione = :data",
                            Prenotazione.class
                    )
                    .setParameter("data", data)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    /*
    public List<ServizioAggiuntivo> cercaServiziPrenotatiPerData(LocalDate data) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();

        try {
            return em.createQuery(
                            "SELECT ps.servizio, ps.quantita " +
                                    "FROM PrenotazioneServizio ps " +
                                    "JOIN ps.prenotazione p " +
                                    "WHERE p.dataPrenotazione = :data",
                            ServizioAggiuntivo.class
                    )
                    .setParameter("data", data)
                    .getResultList();

        } finally {
            em.close();
        }
    }

     */

    //Metodi Elimina
    public boolean eliminaPrenotazione(Long idPrenotazione) {

        return gestorePersistenza.elimina(Prenotazione.class, idPrenotazione);
    }

    //Metodi ausiliari
    private boolean tariffaValida(Tariffa tariffa, LocalDate data) {
        return !data.isBefore(tariffa.getDataInizio())
                && !data.isAfter(tariffa.getDataFine());
    }


}

package control;

import entity.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ControlPrenotazioni {

    //Funzioni di salvataggio prenotazione
    public boolean salvaPrenotazione( LocalDate data,
                                      long prezzo,
                                      Long cliente,
                                      Long ombrellone,
                                      Map<Long, Integer> servizi) {

        if(dataPassata(data)){
            System.out.println("Impossibile prenotare per una data passata");
            return false;
        }

        RegistroPrenotazione registroPrenotazione = new RegistroPrenotazione();

        return registroPrenotazione.RegistraPrenotazione(data,prezzo,cliente,ombrellone,servizi);

    }




    //Funzioni di Ricerca
    public List<PrenotazioneDTO> prenotazioniCliente(Long idCliente){
        RegistroPrenotazione registroPrenotazione = new RegistroPrenotazione();

        List<PrenotazioneDTO> prenotazioniCliente = new ArrayList<>();
        List<Prenotazione> prenotazioni = registroPrenotazione.cercaPrenotazioniCliente(idCliente);
        if(prenotazioni.isEmpty()){
            return null;
        }
        for (Prenotazione p : prenotazioni){
            Long id = p.getId();
            String cliente = p.getCliente().getNome() + " " + p.getCliente().getCognome();
            LocalDate data = p.getDataPrenotazione();
            int posto = p.getOmbrellone().getPosto();
            int fila = p.getOmbrellone().getFila();

            PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO(id,cliente,data,posto,fila);
            prenotazioniCliente.add(prenotazioneDTO);
        }

        return prenotazioniCliente;

    }

    public List<PrenotazioneDTO> ricercaPrenotazioni(){
        RegistroPrenotazione registroPrenotazione = new RegistroPrenotazione();

        List<PrenotazioneDTO> prenotazioniCliente = new ArrayList<>();
        List<Prenotazione> prenotazioni = registroPrenotazione.cercaPrenotazioni();
        if(prenotazioni.isEmpty()){
            return null;
        }
        for (Prenotazione p : prenotazioni){
            Long id = p.getId();
            String cliente = p.getCliente().getNome() + " " + p.getCliente().getCognome();
            LocalDate data = p.getDataPrenotazione();
            int posto = p.getOmbrellone().getPosto();
            int fila = p.getOmbrellone().getFila();

            PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO(id,cliente,data,posto,fila);
            prenotazioneDTO.setRecapito(p.getCliente().getRecapitoTelefonico());
            prenotazioniCliente.add(prenotazioneDTO);
        }

        return prenotazioniCliente;


    }

    public List<OmbrelloneDTO> creaOmbrelloniDisponibili(LocalDate data) {
        RegistroPrenotazione registroPrenotazione = new RegistroPrenotazione();
        RegistroStabilimento registroStabilimento = new RegistroStabilimento();


        List<Prenotazione> prenotazioni =
                registroPrenotazione.cercaPrenotazioniPerData(data);

        List<Ombrellone> ombrelloni =
                registroStabilimento.tariffaOmbrellone();

        Set<Long> ombrelloniPrenotati =
                trovaOmbrelloniPrenotati(prenotazioni);

        List<OmbrelloneDTO> risultato = new ArrayList<>();

        for (Ombrellone o : ombrelloni) {
            if (ombrelloniPrenotati.contains(o.getId())) {
                continue;
            }

            OmbrelloneDTO dto = new OmbrelloneDTO(o.getId(), o.getPrezzoStandard(), o.getFila(), o.getPosto());

            trovaTariffaValida(o.getTariffe(), data)
                    .ifPresent(t -> dto.setPrezzo(t.getPrezzo()));

            risultato.add(dto);
        }

        return risultato;
    }

    public List<ServizioDTO> creaServiziDisponibili(LocalDate data) {
        RegistroPrenotazione registroPrenotazione = new RegistroPrenotazione();
        RegistroStabilimento registroStabilimento = new RegistroStabilimento();

        List<Prenotazione> prenotazioni = registroPrenotazione.cercaPrenotazioniPerData(data);

        List<ServizioAggiuntivo> servizi = registroStabilimento.tariffaServizio();

        Map<Long, Integer> quantitaPrenotata = calcolaQuantitaServiziPrenotati(prenotazioni);

        List<ServizioDTO> risultato = new ArrayList<>();

        for (ServizioAggiuntivo s : servizi) {
            int prenotati = quantitaPrenotata.getOrDefault(
                    s.getId(), 0
            );

            int rimanenti = s.getQuantita() - prenotati;

            if (rimanenti <= 0) {
                continue;
            }

            ServizioDTO dto = new ServizioDTO(s.getId(), s.getNome(), s.getPrezzoStandard(), s.getQuantita(), s.getDescrizione());
            dto.setRimanenti(rimanenti);

            trovaTariffaValida(s.getTariffe(), data)
                    .ifPresent(t -> dto.setPrezzo(t.getPrezzo()));

            risultato.add(dto);
        }

        return risultato;
    }

    /*
    public DisponibilitaDTO disponibilitaInData(LocalDate data){

        RegistroPrenotazione registroPrenotazione = new RegistroPrenotazione();

        List<Prenotazione> prenotazioni = registroPrenotazione.cercaPrenotazioniPerData(data);
    }



     */

    public static List<ServizioDTO> listaServiziData(LocalDate data){
        RegistroStabilimento registro = new RegistroStabilimento();
        List<ServizioAggiuntivo> serviziAggiuntivi = registro.ricercaServizi();

        if(serviziAggiuntivi.isEmpty()){
            return null;
        }
        else {
            List<ServizioDTO> listaServiziDTO = new ArrayList<>();

            for (int i = 0; i < serviziAggiuntivi.size(); i++) {
                ServizioAggiuntivo sAgg = serviziAggiuntivi.get(i);
                ServizioDTO servizioDTO = new ServizioDTO(
                        sAgg.getId(),
                        sAgg.getNome(),
                        sAgg.getPrezzoStandard(),
                        sAgg.getQuantita(),
                        sAgg.getDescrizione()
                );

                listaServiziDTO.add(servizioDTO);
            }

            return listaServiziDTO;
        }
    }
    /*
    public static List<PrenotazioneDTO> prenotazioniPerData(LocalDate data){

    }

     */



    //Funzioni di Elimina prenotazione
    public boolean eliminaPrenotazione(LocalDate data, Long idPrenotazione){
        RegistroPrenotazione registro = new RegistroPrenotazione();

        if(distaDueSettimane(data)){
            System.out.println("non è possibile annullare una prenotazione a meno di due settimane dalla data prenotata");
            return false;
        }

        return registro.eliminaPrenotazione(idPrenotazione);
    }

    //Funzioni ausiliari per controlli vari
    private Optional<Tariffa> trovaTariffaValida(
            List<Tariffa> tariffe, LocalDate data) {

        return tariffe.stream()
                .filter(t ->
                        !data.isBefore(t.getDataInizio())
                                && !data.isAfter(t.getDataFine()))
                .findFirst();
    }

    private Set<Long> trovaOmbrelloniPrenotati(
            List<Prenotazione> prenotazioni) {

        return prenotazioni.stream()
                .map(p -> p.getOmbrellone().getId())
                .collect(Collectors.toSet());
    }

    private Map<Long, Integer> calcolaQuantitaServiziPrenotati(
            List<Prenotazione> prenotazioni) {

        Map<Long, Integer> quantitaPrenotata = new HashMap<>();

        for (Prenotazione prenotazione : prenotazioni) {
            for (PrenotazioneServizio ps :
                    prenotazione.getPrenotazioniConServizio()) {

                Long idServizio = ps.getServizio().getId();

                quantitaPrenotata.merge(
                        idServizio,
                        ps.getQuantita(),
                        Integer::sum
                );
            }
        }

        return quantitaPrenotata;
    }

    private boolean tariffaValida(Tariffa tariffa, LocalDate data) {
        return !data.isBefore(tariffa.getDataInizio())
                && !data.isAfter(tariffa.getDataFine());
    }

    public boolean distaDueSettimane (LocalDate dataPrenotazione){

        LocalDate oggi = LocalDate.now();
        return dataPrenotazione.isBefore(oggi.plusWeeks(2));

    }

    public boolean dataPassata (LocalDate dataPrenotazione){
        LocalDate oggi = LocalDate.now();
        return !oggi.isBefore(dataPrenotazione);
    }
}

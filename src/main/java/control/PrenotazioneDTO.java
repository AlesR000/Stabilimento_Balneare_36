package control;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PrenotazioneDTO {

    Long idPrenotazione;
    String cliente;
    LocalDate data;
    int posto;
    int fila;
    String recapito;

    Map<String, Integer> servizi = new HashMap<>();

    public PrenotazioneDTO(Long idPrenotazione, String cliente, LocalDate data, int posto, int fila) {
        this.idPrenotazione = idPrenotazione;
        this.cliente = cliente;
        this.data = data;
        this.posto = posto;
        this.fila = fila;
    }

    public PrenotazioneDTO(Long idPrenotazione, String cliente, LocalDate data, int posto, int fila, Map<String, Integer> servizi) {
        this.idPrenotazione = idPrenotazione;
        this.cliente = cliente;
        this.data = data;
        this.posto = posto;
        this.fila = fila;
        this.servizi = servizi;
    }

    public Long getIdPrenotazione() {
        return idPrenotazione;
    }
    public String getCliente() {return cliente;}
    public LocalDate getData() {return data;}
    public int getPosto() {return posto;}
    public int getFila() {return fila;}
    public Map<String, Integer> getServizi() {return servizi;}
    public String getRecapito() {return recapito;}


    public void setRecapito(String recapito) {this.recapito = recapito;}
    public void addServizio(String servizio, int qunatita){
        servizi.put(servizio, qunatita);
    }
}

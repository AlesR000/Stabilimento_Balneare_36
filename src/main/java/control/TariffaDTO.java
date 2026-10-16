package control;

import java.time.LocalDate;

public class TariffaDTO {
    private Long id;
    private long prezzo;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    //Indica la fila o il servizio associato
    private String associazione;

    public TariffaDTO(long id, long prezzo, LocalDate dataInizio, LocalDate dataFine, String associazione) {
        this.id = id;
        this.prezzo = prezzo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.associazione = associazione;
    }

    public Long getId() {return id;}
    public long getPrezzo() {return prezzo;}
    public LocalDate getDataInizio() {return dataInizio;}
    public LocalDate getDataFine() {return dataFine;}
    public String getAssociazione() {return associazione;}

}

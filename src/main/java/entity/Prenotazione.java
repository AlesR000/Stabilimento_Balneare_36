package entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataPrenotazione;
    private long prezzo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ombrellone_id", nullable = false)
    private Ombrellone ombrellone;

    @OneToMany(mappedBy = "prenotazione", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrenotazioneServizio> prenotazioniConServizio = new ArrayList<>();



    //Costruttore
    public Prenotazione() {}

    public Prenotazione(LocalDate dataPrenotazione, long prezzo, Cliente cliente, Ombrellone ombrellone) {
        this.dataPrenotazione = dataPrenotazione;
        this.prezzo = prezzo;
        this.cliente = cliente;
        this.ombrellone = ombrellone;
    }

    public Prenotazione(LocalDate dataPrenotazione, long prezzo, Cliente cliente, Ombrellone ombrellone, List<PrenotazioneServizio> prenotazioneSer) {
        this.dataPrenotazione = dataPrenotazione;
        this.prezzo = prezzo;
        this.cliente = cliente;
        this.ombrellone = ombrellone;

        for (int i = 0; i < prenotazioneSer.size(); i++) {
            aggiungiPrenotazioneServizio(prenotazioneSer.get(i));
        }

    }

    //Getter e Setter
    public Long getId() {return id;}

    public void aggiungiPrenotazioneServizio(PrenotazioneServizio prenotazioneServizio) {
        prenotazioniConServizio.add(prenotazioneServizio);
    }

    public Ombrellone getOmbrellone() {return ombrellone;}
    public void setOmbrellone(Ombrellone ombrellone) {this.ombrellone = ombrellone;}

    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public LocalDate getDataPrenotazione() {return dataPrenotazione;}

    public List<PrenotazioneServizio> getPrenotazioniConServizio() {return prenotazioniConServizio;}
    public void setPrenotazioniConServizio(List<PrenotazioneServizio> prenotazioniConServizio){this.prenotazioniConServizio = prenotazioniConServizio;}


}

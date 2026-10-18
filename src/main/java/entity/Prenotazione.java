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
    private List<PrenotazioneServizio> servizi = new ArrayList<>();


    //Costruttore
    public Prenotazione() {}

    public Prenotazione(LocalDate dataPrenotazione, long prezzo, Cliente cliente, Ombrellone ombrellone) {
        this.dataPrenotazione = dataPrenotazione;
        this.prezzo = prezzo;
        this.cliente = cliente;
        this.ombrellone = ombrellone;
    }

    public Prenotazione(LocalDate dataPrenotazione, long prezzo, Cliente cliente, Ombrellone ombrellone, List<ServizioAggiuntivo> servizi, List<Integer> quantita) {
        this.dataPrenotazione = dataPrenotazione;
        this.prezzo = prezzo;
        this.cliente = cliente;
        this.ombrellone = ombrellone;

        for (int i = 0; i < servizi.size(); i++) {
            aggiungiServizio(servizi.get(i), quantita.get(i));
        }

    }

    //Getter e Setter
    public Long getId() {return id;}

    public void aggiungiServizio(ServizioAggiuntivo servizio, int quantita) {
        servizi.add(new PrenotazioneServizio(this, servizio, quantita));
    }

}

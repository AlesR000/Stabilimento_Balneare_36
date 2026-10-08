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

    @ManyToMany
    @JoinTable(
            name = "prenotazione_servizio",
            joinColumns = @JoinColumn(name = "prenotazione_id"),
            inverseJoinColumns = @JoinColumn(name = "servizio_id")
    )
    private List<ServizioAggiuntivo> servizi = new ArrayList<>();


    //Costruttore
    public Prenotazione() {}

    public Prenotazione(LocalDate dataPrenotazione, long prezzo, Cliente cliente, Ombrellone ombrellone) {
        this.dataPrenotazione = dataPrenotazione;
        this.prezzo = prezzo;
        this.cliente = cliente;
        this.ombrellone = ombrellone;
    }

    public Prenotazione(LocalDate dataPrenotazione, long prezzo, Cliente cliente, Ombrellone ombrellone, List<ServizioAggiuntivo> servizi) {
        this.dataPrenotazione = dataPrenotazione;
        this.prezzo = prezzo;
        this.cliente = cliente;
        this.ombrellone = ombrellone;
        this.servizi = servizi;
    }

    //Getter e Setter
    public Long getId() {return id;}

}

package entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tariffa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long prezzo;

    private LocalDate dataInizio;
    private LocalDate dataFine;

    @ManyToOne
    @JoinColumn(name = "servizio_id")
    private ServizioAggiuntivo servizio;

    @ManyToMany(mappedBy = "tariffe")
    private List<Ombrellone> ombrelloni = new ArrayList<>();

    //Costruttore
    public Tariffa() {}

    public Tariffa(long prezzo, LocalDate dataInizio, LocalDate dataFine, ServizioAggiuntivo servizio) {
        this.prezzo = prezzo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.servizio = servizio;
    }

    public Tariffa(long prezzo, LocalDate dataInizio, LocalDate dataFine, List<Ombrellone> ombrelloni) {
        this.prezzo = prezzo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.ombrelloni = ombrelloni;
    }

    //Getter e setter
    public long getId() {return id;}

    public long getPrezzo() {return prezzo;}
    public void setPrezzo(long prezzo) {this.prezzo = prezzo;}

    public LocalDate getDataInizio() {return dataInizio;}
    public void setDataInizio(LocalDate dataInizio) {this.dataInizio = dataInizio;}
    public LocalDate getDataFine() {return dataFine;}
    public void setDataFine(LocalDate dataFine) {this.dataFine = dataFine;}

    public List<Ombrellone> getOmbrelloni() {return ombrelloni;}
    public void addOmbrellone(Ombrellone ombrellone) {ombrelloni.add(ombrellone);}
    public int getFila(){return ombrelloni.getFirst().getFila();}

    public ServizioAggiuntivo getServizio() {return servizio;}
    public void setServizio(ServizioAggiuntivo servizio) {this.servizio = servizio;}

}

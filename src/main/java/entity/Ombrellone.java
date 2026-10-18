package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ombrellone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long prezzoStandard;
    private int fila;
    private int posto;


    @OneToMany
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    @ManyToMany (mappedBy = "ombrelloni")
    private List<Tariffa> tariffe = new ArrayList<>();


    //Costruttore
    public Ombrellone() {}

    public Ombrellone(long prezzoStandard, int fila, int posto){
        this.prezzoStandard = prezzoStandard;
        this.fila = fila;
        this.posto = posto;
    }

    //Getter e Setter
    public Long getId() {return id;}

    public long getPrezzoStandard() {return prezzoStandard;}
    public void setPrezzoStandard(long prezzoStandard) {}

    public int getFila() {return fila;}
    public void setFila(int fila) {this.fila = fila;}

    public int getPosto() {return posto;}
    public void setPosto(int posto) {this.posto = posto;}

    public void aggiungiPrenotazione(Prenotazione prenotazione) {
        prenotazioni.add(prenotazione);
    }
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void aggiungiTariffa(Tariffa tariffa) {
        tariffe.add(tariffa);
    }
    public List<Tariffa> getTariffe() {return tariffe;}


}

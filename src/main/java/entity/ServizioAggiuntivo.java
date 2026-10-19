package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ServizioAggiuntivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private long prezzoStandard;
    private int quantita;
    private String descrizione;

    @OneToMany
    private List<Tariffa> tariffe = new ArrayList<>();

    @OneToMany(mappedBy = "servizio")
    private List<PrenotazioneServizio> prenotazioni = new ArrayList<>();



    //Costruttori
    public ServizioAggiuntivo() {
        this.prezzoStandard = 0;
        this.quantita = 1;
    }

    public ServizioAggiuntivo(String nome, long prezzoStandard, int quantita, String descrizione) {
        this.nome = nome;
        this.prezzoStandard = prezzoStandard;
        this.quantita = quantita;
        this.descrizione = descrizione;
    }

    //Getter e setter
    public long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public long getPrezzoStandard() {return prezzoStandard;}
    public void setPrezzoStandard(long prezzoStandard) {this.prezzoStandard = prezzoStandard;}

    public int getQuantita() {return quantita;}
    public void setQuantita(int quantita) {this.quantita = quantita;}

    public String getDescrizione() {return descrizione;}
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public List<Tariffa> getTariffe() {return tariffe;}
    public void aggiungiTariffa(Tariffa tariffa) {tariffe.add(tariffa);}
}

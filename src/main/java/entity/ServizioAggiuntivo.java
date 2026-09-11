package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServizioAggiuntivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private long prezzoStandard;
    private int quantita;
    private String descrizione;


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
}

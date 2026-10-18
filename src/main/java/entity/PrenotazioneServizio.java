package entity;

import jakarta.persistence.*;

@Entity
public class PrenotazioneServizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prenotazione_id")
    private Prenotazione prenotazione;

    @ManyToOne(optional = false)
    @JoinColumn(name = "servizio_id")
    private ServizioAggiuntivo servizio;

    @Column(nullable = false)
    private int quantita;

    public PrenotazioneServizio() {
    }

    public PrenotazioneServizio(Prenotazione prenotazione, ServizioAggiuntivo servizio, int quantita) {
        this.prenotazione = prenotazione;
        this.servizio = servizio;
        this.quantita = quantita;
    }

    //Getter e Setter
    public Prenotazione getPrenotazione() {return prenotazione;}
    public void setPrenotazione(Prenotazione prenotazione) {this.prenotazione = prenotazione;}

    public void aggiungiServizio(ServizioAggiuntivo servizio, int quantita) {
        this.servizio = servizio;
        this.quantita = quantita;
    }

    public ServizioAggiuntivo getServizio() {return servizio;}
    public int getQuantita() {return quantita;}


}
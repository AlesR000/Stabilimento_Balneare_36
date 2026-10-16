package control;

//Classe data transfer object da tramite con la boundary
public class ServizioDTO {
    private Long id;
    private String nome;
    private long prezzo;
    private int quantita;
    private String descrizione;

    public ServizioDTO(Long id, String nome, long prezzo, int quantita, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.descrizione = descrizione;
    }

    public Long getId() {return id;}
    public String getNome() {return nome;}
    public long getPrezzo() {return prezzo;}
    public int getQuantita() {return quantita;}
    public String getDescrizione() {return descrizione;}

}

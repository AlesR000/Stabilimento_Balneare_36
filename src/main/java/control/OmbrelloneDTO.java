package control;

public class OmbrelloneDTO {

    Long id;
    long prezzo;

    int fila;
    int posto;

    public OmbrelloneDTO(Long id, long prezzo, int fila, int posto){
        this.id = id;
        this.prezzo = prezzo;
        this.fila = fila;
        this.posto = posto;
    }

    public Long getId() {return id;}

    public long getPrezzo() {return prezzo;}
    public void setPrezzo(long prezzo) {
        this.prezzo = prezzo;
    }

    public int getFila() {return fila;}
    public void setFila(int fila) {
        this.fila = fila;
    }
    public int getPosto() {return posto;}
    public void setPosto(int posto) {
        this.posto = posto;
    }

}

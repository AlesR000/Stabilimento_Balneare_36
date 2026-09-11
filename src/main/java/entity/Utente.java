package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String cognome;
    private String email;
    private String passwordHash;
    private String recapitoTelefonico;

    public Utente() {}

    public Utente(String nome, String cognome, String email, String passwordHash, String recapitoTelefonico) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.passwordHash = passwordHash;
        this.recapitoTelefonico = recapitoTelefonico;
    }

    //Getter e setter
    public long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}
    public void setCognome(String cognome) {this.cognome = cognome;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPasswordHash() {return passwordHash;}
    public void setPasswordHash(String passwordHash) {this.passwordHash = passwordHash;}

    public String getRecapitoTelefonico() {return recapitoTelefonico;}
    public void setRecapitoTelefonico(String recapitoTelefonico){this.recapitoTelefonico = recapitoTelefonico;}

}

package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private String passwordHash;
    private int recapitoTelefonico;

    //Costruttore
    public Utente() {}

    public Utente(String nome, String cognome, String email, String passwordHash, int recapitoTelefonico) {
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

    public int getRecapitoTelefonico() {return recapitoTelefonico;}
    public void setRecapitoTelefonico(int recapitoTelefonico){this.recapitoTelefonico = recapitoTelefonico;}

}

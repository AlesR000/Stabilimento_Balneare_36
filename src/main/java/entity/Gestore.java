package entity;


import jakarta.persistence.*;

@Entity
public class Gestore extends Utente{

    public Gestore() {
    }

    public Gestore(String nome,
                   String cognome,
                   String email,
                   String passwordHash,
                   int recapitoTelefonico) {

        super(nome, cognome, email, passwordHash, recapitoTelefonico);
    }
}

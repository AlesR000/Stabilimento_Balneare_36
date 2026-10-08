package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Utente{

    @OneToMany
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome,
                    String cognome,
                    String email,
                    String passwordHash,
                    int recapitoTelefonico) {

        super(nome, cognome, email, passwordHash, recapitoTelefonico);
    }



}

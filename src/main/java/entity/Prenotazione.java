package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataPrenotazione;
    private long prezzo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ombrellone_id", nullable = false)
    private Ombrellone ombrellone;


}

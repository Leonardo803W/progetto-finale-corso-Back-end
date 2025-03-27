package epicode.it.progetto_finale_corso_epicode.viaggi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private String stato;
    private String regione;
    private String provincia;
    private String citta;
    private String titolo;
    private String descrizione;
    private double prezzo;
}

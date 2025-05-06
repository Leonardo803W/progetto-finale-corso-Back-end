package epicode.it.progetto_finale_corso_epicode.viaggi;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descrizione;
    private String titolo;
    private String stato;
    private String provincia;
    private String citta;
    private String image;
    private Integer adulti;
    private Integer bambini;
    @Column(name = "check_in")
    private LocalDate checkIn;
    @Column(name = "check_out")
    private LocalDate checkOut;
    private Double prezzo;
}
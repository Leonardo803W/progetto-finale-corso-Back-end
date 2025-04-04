package epicode.it.progetto_finale_corso_epicode.viaggi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioDettaglioResponse {

    private String stato;
    private String regione;
    private String provincia;
    private String citta;
    private String titolo;
    private String descrizione;
    private String image;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer  adulti;
    private Integer  bambini;
    private double prezzo;
}
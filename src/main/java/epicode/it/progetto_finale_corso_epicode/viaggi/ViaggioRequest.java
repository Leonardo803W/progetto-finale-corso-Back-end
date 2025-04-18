package epicode.it.progetto_finale_corso_epicode.viaggi;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequest {

    private String stato;
    private String regione;
    private String provincia;
    private String citta;
    private String image;
    private Integer  adulti;
    private Integer  bambini;

    private LocalDate checkIn;
    private LocalDate checkOut;


    private String titolo;

    private String descrizione;


    private Double prezzo;
}
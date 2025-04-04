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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkIn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkOut;

    @NotBlank
    private String titolo;

    @NotBlank
    private String descrizione;

    @NotNull
    private Double prezzo;
}
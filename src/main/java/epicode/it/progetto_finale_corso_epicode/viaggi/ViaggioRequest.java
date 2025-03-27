package epicode.it.progetto_finale_corso_epicode.viaggi;

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

    @NotBlank
    private String titolo;

    @NotBlank
    private String descrizione;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double prezzo;
}
package epicode.it.progetto_finale_corso_epicode.viaggi;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequestModify {

    // controllare sempre se nel databese creato con postgresSQL non vi sia Not NULL
    // in caso si, bisogna aggiungere @NotNull(message = "Il campo non puo essere vuoto")
    // per avere un essaggio piu chiaro nel terminale in caso di errore

    @NotBlank(message = "La descrizione è obbligatorio")
    private String descrizione;
    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;
    @NotBlank(message = "Lo stato è obbligatorio")
    private String stato;
    @NotBlank(message = "La citta è obbligatorio")
    private String citta;

    //private String provincia;
    //private String image;
    //private Integer adulti;
    //private Integer bambini;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //private LocalDate checkIn;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //private LocalDate checkOut;
    //private Double prezzo;
}

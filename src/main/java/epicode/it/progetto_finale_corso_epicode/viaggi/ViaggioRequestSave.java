package epicode.it.progetto_finale_corso_epicode.viaggi;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequestSave {

    // controllare sempre se nel databese creato con postgresSQL non vi sia Not NULL
    // in caso si, bisogna aggiungere @NotNull(message = "Il campo non puo essere vuoto")
    // per avere un essaggio piu chiaro nel terminale in caso di errore

    private String descrizione;
    private String titolo;
    private String stato;
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

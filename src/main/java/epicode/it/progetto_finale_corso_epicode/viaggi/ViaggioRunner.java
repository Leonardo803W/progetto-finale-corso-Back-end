package epicode.it.progetto_finale_corso_epicode.viaggi;

/*
import epicode.it.progetto_finale_corso_epicode.Config.FakerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViaggioRunner implements CommandLineRunner {

    private final FakerConfig fakerConfig;
    private final ViaggioRepository viaggioRepository;

    public void run(String... args) throws Exception {

        for (int i = 0; i < 10; i++) {
            Viaggio v = new Viaggio();
            v.setCitta(fakerConfig.faker().country().capital());
            v.setStato(fakerConfig.faker().country().name());
            v.setPrezzo(fakerConfig.faker().number().randomDouble(2, 100, 1000));
            v.setDescrizione("Parti in spiaggia da perdere la testa!");
            v.setTitolo("Bar Beach");

            viaggioRepository.save(v);
        }
    }
}

 */
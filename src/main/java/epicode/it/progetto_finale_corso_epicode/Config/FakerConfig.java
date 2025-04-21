package epicode.it.progetto_finale_corso_epicode.Config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class FakerConfig {

    @Bean
    public Faker faker () {

        return new Faker(); //per dati globali
        //return new Faker(new Locale("it-IT")); //per dati locali specifici
    }
}

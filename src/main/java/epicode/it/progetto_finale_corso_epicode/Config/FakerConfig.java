package epicode.it.progetto_finale_corso_epicode.Config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfig {

    @Bean
    public Faker faker () {

        return new Faker();
    }
}

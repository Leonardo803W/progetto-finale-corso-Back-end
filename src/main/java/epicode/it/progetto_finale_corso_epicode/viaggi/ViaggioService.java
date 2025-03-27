package epicode.it.progetto_finale_corso_epicode.viaggi;

import epicode.it.progetto_finale_corso_epicode.GeneralResponseWithMessage;
import epicode.it.progetto_finale_corso_epicode.email.EmailService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ViaggioService {

    /*
    @Value("${messages.new.viaggio.to}")
    private String newViaggioTo;

    @Value("${messages.new.viaggio.subject}")
    private String newViaggioSubject;

    @Value("${messages.new.viaggio.body}")
    private String newViaggioBody;

     */

    private final EmailService emailService;
    private final ViaggioRepository viaggioRepository;

    // Metodo per inserire un viaggio
    public GeneralResponseWithMessage<Viaggio> save(ViaggioRequest viaggioRequest) {

        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(viaggioRequest, viaggio);
        viaggioRepository.save(viaggio);

        /*
        try {

            emailService.sendEmail(
                    newViaggioTo,
                    newViaggioSubject,
                    newViaggioBody);

        } catch (MessagingException e) {

            System.out.println("Errore durante l'invio dell'email: " + e.getMessage());
        }

         */

        return new GeneralResponseWithMessage<>(viaggio, "Viaggio salvato con successo.");
    }

    // Metodo per modificare un viaggio
    public GeneralResponseWithMessage<Viaggio> modifyById(Long id, ViaggioRequest viaggioRequest) {

        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato con ID: " + id));

        BeanUtils.copyProperties(viaggioRequest, viaggio);
        viaggioRepository.save(viaggio);

        return new GeneralResponseWithMessage<>(viaggio, "Viaggio modificato con successo.");
    }

    // Metodo per trovare un viaggio per id
    public GeneralResponseWithMessage<Viaggio> findById(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato con ID: " + id));

        return new GeneralResponseWithMessage<>(viaggio, "Viaggio trovato con successo.");
    }

    // Metodo per cancellare un viaggio
    public GeneralResponseWithMessage<String> deleteById(Long id) {

        viaggioRepository.deleteById(id);
        return new GeneralResponseWithMessage<>(null, "Viaggio cancellato con ID: " + id);
    }

    /*
    // Metodo per trovare tutti i viaggi
    public GeneralResponseWithMessage<Page<Viaggio>> findAll(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Viaggio> viaggi = viaggioRepository.findAll(pageable);

        return new GeneralResponseWithMessage<>(viaggi, "Viaggi recuperati con successo.");
    }

     */

    // Metodo per trovare tutti i viaggi
    public GeneralResponseWithMessage<List<Viaggio>> findAll() {

        List<Viaggio> viaggi = viaggioRepository.findAll();
        return new GeneralResponseWithMessage<>(viaggi, "Viaggi recuperati con successo.");
    }
}
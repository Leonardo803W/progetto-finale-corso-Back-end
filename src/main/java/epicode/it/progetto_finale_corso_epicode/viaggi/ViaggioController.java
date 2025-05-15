package epicode.it.progetto_finale_corso_epicode.viaggi;

import epicode.it.progetto_finale_corso_epicode.GeneralResponseWithMessage;
import epicode.it.progetto_finale_corso_epicode.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@RequiredArgsConstructor
public class ViaggioController {

    @Value("${messages.new.viaggio.to}")
    private String newViaggioTo;

    @Value("${messages.new.viaggio.subject}")
    private String newViaggioSubject;

    @Value("${messages.new.viaggio.body}")
    private String newViaggioBody;

    private final ViaggioService viaggioService;
    private final EmailService emailService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Viaggio> save(@RequestBody @Validated ViaggioRequestSave body) {

        /*
        
        // Invio dell'email di conferma del salvataggio di un nuovo viaggio direttamente all'admin
        try {

            // Invio dell'email di conferma
            emailService.sendEmail(newViaggioTo, newViaggioSubject, newViaggioBody);
            System.out.println("Email inviata con successo a " + newViaggioTo);

        } catch (MessagingException e) {

            System.out.println("Errore nell'invio dell'email di conferma: " + e.getMessage());
        }

         */

        return viaggioService.save(body);
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Viaggio> findById(@PathVariable Long id) {

        return viaggioService.findById(id);
    }

    @PutMapping("/modifyById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Viaggio> modifyById(@PathVariable Long id, @RequestBody ViaggioRequestModify request) {

        return viaggioService.modifyById(id, request);
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<String> deleteById(@PathVariable Long id) {

        return viaggioService.deleteById(id);
    }


    @GetMapping("/fetchall")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Page<Viaggio>> findAll(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "id") String sortBy) {

        return viaggioService.findAll(page, size, sortBy);
    }
}
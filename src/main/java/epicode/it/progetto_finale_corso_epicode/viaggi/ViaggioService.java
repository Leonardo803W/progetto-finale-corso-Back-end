package epicode.it.progetto_finale_corso_epicode.viaggi;

import epicode.it.progetto_finale_corso_epicode.GeneralResponseWithMessage;
import epicode.it.progetto_finale_corso_epicode.email.EmailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class ViaggioService {

    private final EmailService emailService;
    private final ViaggioRepository viaggioRepository;

    // Metodo per inserire un viaggio
    public GeneralResponseWithMessage<Viaggio> save(ViaggioRequest viaggioRequest) {

        Viaggio viaggio = new Viaggio();
        viaggioRepository.save(viaggio);
        BeanUtils.copyProperties(viaggioRequest, viaggio);

        return new GeneralResponseWithMessage<>(viaggio, "Viaggio salvato con successo.");
    }

    // Metodo per modificare un viaggio
    public GeneralResponseWithMessage<Viaggio> modifyById(Long id, ViaggioRequest viaggioRequest) {

        Viaggio viaggio = viaggioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato con ID: " + id));

        BeanUtils.copyProperties(viaggioRequest, viaggio);
        viaggioRepository.save(viaggio);

        return new GeneralResponseWithMessage<>(viaggio, "Viaggio modificato con successo.");
    }

    // Metodo per trovare un viaggio per id
    public GeneralResponseWithMessage<Viaggio> findById(Long id) {

        Viaggio viaggio = viaggioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato con ID: " + id));

        return new GeneralResponseWithMessage<>(viaggio, "Viaggio trovato con successo.");
    }

    // Metodo per cancellare un viaggio
    public GeneralResponseWithMessage<String> deleteById(Long id) {

        if (!viaggioRepository.existsById(id)) {

            throw new EntityNotFoundException("Viaggio non trovato con ID: " + id);
        }

        viaggioRepository.deleteById(id);

        return new GeneralResponseWithMessage<>(null, "Viaggio cancellato con ID: " + id);
    }

    // Metodo per trovare tutti i viaggi
    public GeneralResponseWithMessage<Page<Viaggio>> findAll(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Viaggio> viaggi = viaggioRepository.findAll(pageable);

        return new GeneralResponseWithMessage<>(viaggi, "Viaggi recuperati con successo.");
    }
}
package epicode.it.progetto_finale_corso_epicode.viaggi;

import epicode.it.progetto_finale_corso_epicode.GeneralResponseWithMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@RequiredArgsConstructor
public class ViaggioController {

    private final ViaggioService viaggioService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Viaggio> save(@RequestBody @Validated ViaggioRequest body) {

        return viaggioService.save(body);
    }

    @GetMapping("findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Viaggio> findById(@PathVariable Long id) {

        return viaggioService.findById(id);
    }

    @PutMapping("modifyById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Viaggio> modifyById(@PathVariable Long id, @RequestBody ViaggioRequest request) {
        return viaggioService.modifyById(id, request);
    }

    @DeleteMapping("deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<String> deleteById(@PathVariable Long id) {

        return viaggioService.deleteById(id);
    }

    /*
    @GetMapping("/fetchall")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<Page<Viaggio>> findAll(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "id") String sortBy) {

        return viaggioService.findAll(page, size, sortBy);
    }

     */


    @GetMapping("/fetchall")
    @ResponseStatus(HttpStatus.OK)
    public GeneralResponseWithMessage<List<Viaggio>> findAll() {

        return viaggioService.findAll();
    }
}
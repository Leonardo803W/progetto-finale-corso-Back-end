package epicode.it.progetto_finale_corso_epicode.security;

import epicode.it.progetto_finale_corso_epicode.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final EmailService emailService;

    //metodi per lo user e per l'admin

    //endpoint per la registrazione con ruolo user
    @PostMapping("/user/register")
    public ResponseEntity<String> userRegister(@RequestBody RegisterRequest userRegisterRequest) {

        // Registrazione dell'utente
        appUserService.registerUser(
                userRegisterRequest.getUsername(),
                userRegisterRequest.getPassword(),
                Set.of(Role.ROLE_USER),
                userRegisterRequest.getEmail()
        );

        try {
            String subject = "Registrazione al sito 'Viaggi di Passione'";
            String body = "Ciao " + userRegisterRequest.getUsername() + ",\n  Registrazione avvenuta con successo. In caso volessi disattivare la tua email, vai nelle impostazioni del sito citato.";

            // Invio dell'email di conferma
            emailService.sendEmail(userRegisterRequest.getEmail(), subject, body);
            System.out.println("Email inviata con successo a " + userRegisterRequest.getEmail());

        } catch (MessagingException e) {

            System.out.println("Errore nell'invio dell'email di conferma per l'utente " + userRegisterRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Errore nell'invio dell'email di conferma.");
        }

        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    //endpoint per la registrazione con ruolo admin
    @PostMapping("/admin/register")
    public ResponseEntity<String> adminRegister(@RequestBody RegisterRequest adminRegisterRequest) {

        // Registrazione dell'admin
        appUserService.registerUser(
                adminRegisterRequest.getUsername(),
                adminRegisterRequest.getPassword(),
                Set.of(Role.ROLE_ADMIN),
                adminRegisterRequest.getEmail()
        );

        try {
            String subject = "Registrazione al sito 'Viaggi di Passione'";
            String body = "Ciao " + adminRegisterRequest.getUsername() + ",\n  Registrazione avvenuta con successo. In caso volessi disattivare la tua email, vai nelle impostazioni del sito citato.";

            // Invio dell'email di conferma
            emailService.sendEmail(adminRegisterRequest.getEmail(), subject, body);
            System.out.println("Email inviata con successo a " + adminRegisterRequest.getEmail());

        } catch (MessagingException e) {

            System.out.println("Errore nell'invio dell'email di conferma per l'utente " + adminRegisterRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Errore nell'invio dell'email di conferma.");
        }

        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    //endpoint per l'autenticazione dello user o dell'admin
    @PostMapping("/account/login")
    public ResponseEntity<AuthResponse> UserLogin(@RequestBody LoginRequest userLoginRequest) {

        String token = appUserService.authenticateUser(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        );

        AppUser user = appUserService.findUserBy(String.valueOf(userLoginRequest.getUsername()), "username");

        System.out.println("Login avvenuto con successo");
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // Endpoint per ottenere i dettagli dell'utente o dell'admin
    @GetMapping("/account/details")
    public ResponseEntity<AppUser> getUserDetails(@RequestParam String identifier, @RequestParam(required = false, defaultValue = "username") String type) {

        String searchType = type.toLowerCase();

        Optional<AppUser> user;

        if ("email".equals(searchType)) {
            user = Optional.ofNullable(appUserService.findUserBy(identifier, "email"));

        } else if ("username".equals(searchType)) {

            user = Optional.ofNullable(appUserService.findUserBy(identifier, "username"));
        } else {

            // Caso in cui il tipo di ricerca non è riconosciuto
            return ResponseEntity.badRequest().body(null);
        }

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint per modificare i dettagli dell'utente o dell'admin
    @PutMapping("/account/update")
    public ResponseEntity<String> updateUser(@RequestParam String identifier, @RequestParam(required = false, defaultValue = "username") String type, @RequestBody UpdateRequest UpdateRequest) {

        String searchType = type.toLowerCase();

        Optional<AppUser> updateUser;

        if ("email".equals(searchType)) {
            updateUser = Optional.ofNullable(appUserService.findUserBy(identifier, "email"));

        } else if ("username".equals(searchType)) {

            updateUser = Optional.ofNullable(appUserService.findUserBy(identifier, "username"));
        } else {

            // Caso in cui il tipo di ricerca non è riconosciuto
            return ResponseEntity.badRequest().body(null);
        }

        appUserService.updateUser(searchType, UpdateRequest);

        try {
            String subject = "Vi sono stati dei cambiamenti nelle impostazioni del tuo profilo";
            String body = "Admin " + UpdateRequest.getUsername() + ",\n  le modifiche sono state effettuate.";

            // Invio dell'email di conferma
            emailService.sendEmail(UpdateRequest.getEmail(), subject, body);
            System.out.println("Email inviata con successo a " + UpdateRequest.getEmail());

        } catch (MessagingException e) {

            System.out.println("Errore nell'invio dell'email di conferma per l'utente " + UpdateRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Errore nell'invio dell'email di conferma.");
        }

        return ResponseEntity.ok("Utente aggiornato con successo: " + updateUser.get().getUsername());
    }

    /*
    @DeleteMapping("/account/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String identifier, @RequestParam(required = false, defaultValue = "username") String type, RegisterRequest RegisterRequest) {

        String searchType = type.toLowerCase();

        Optional<AppUser> user;

        if ("email".equals(searchType)) {
            user = Optional.ofNullable(appUserService.findUserBy(identifier, "email"));

        } else if ("username".equals(searchType)) {

            user = Optional.ofNullable(appUserService.findUserBy(identifier, "username"));
        } else {

            // Caso in cui il tipo di ricerca non è riconosciuto
            return ResponseEntity.badRequest().body(null);
        }

        appUserService.deleteUser(identifier);

        try {
            String subject = "Cancellazione al sito 'Viaggi di Passione'";
            String body = "Ciao " + RegisterRequest.getUsername() + ",\n  La cancellazione dei tuoi dati e avvenuta con successo.";

            // Invio dell'email di conferma
            emailService.sendEmail(RegisterRequest.getEmail(), subject, body);
            System.out.println("Email inviata con successo a " + RegisterRequest.getEmail());

        } catch (MessagingException e) {

            System.out.println("Errore nell'invio dell'email di conferma per l'utente " + RegisterRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Errore nell'invio dell'email di conferma.");
        }

        return ResponseEntity.ok("Utente eliminato con successo.");
    }

     */
}

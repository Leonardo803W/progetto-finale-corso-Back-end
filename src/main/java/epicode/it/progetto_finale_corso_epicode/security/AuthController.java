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

    //metodi per lo user


    //endpoint per la registrazione con ruolo user
    @PostMapping("/user-register")
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
    // Endpoint per ottenere i dettagli dell'utente
    @GetMapping("/user-details")
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

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //endpoint per l'autenticazione
    @PostMapping("/user-login")
    public ResponseEntity<AuthResponse> UserLogin(@RequestBody LoginRequest userLoginRequest) {

        String token = appUserService.authenticateUser(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        );

        AppUser user = appUserService.findUserBy(String.valueOf(userLoginRequest.getUsername()), "username");

        System.out.println("Login avvenuto con successo");
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // Endpoint per modificare i dettagli dell'utente
    @PutMapping("/user-update/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody UpdateRequest userUpdateRequest) {

        AppUser updatedUser = appUserService.updateUser(username, userUpdateRequest);

        try {
            String subject = "Vi sono stati dei cambiamenti nelle impostazioni del tuo profilo";
            String body = "Admin " + userUpdateRequest.getUsername() + ",\n  le modifiche sono state effettuate.";

            // Invio dell'email di conferma
            emailService.sendEmail(userUpdateRequest.getEmail(), subject, body);
            System.out.println("Email inviata con successo a " + userUpdateRequest.getEmail());

        } catch (MessagingException e) {

            System.out.println("Errore nell'invio dell'email di conferma per l'utente " + userUpdateRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Errore nell'invio dell'email di conferma.");
        }

        return ResponseEntity.ok("Utente aggiornato con successo: " + updatedUser.getUsername());
    }

    //endpoint per la registrazione con ruolo admin
    @PostMapping("/admin-register")
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

    // Endpoint per ottenere i dettagli dell'admin
    @GetMapping("/admin-details/{username}")
    public ResponseEntity<AppUser> getAdminDetails(@PathVariable String username) {

        Optional<AppUser> admin = Optional.ofNullable(appUserService.findUserBy(username, "username"));

        return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //endponit per l'autenticazione
    @PostMapping("/admin-login")
    public ResponseEntity<AuthResponse> adminLogin(@RequestBody LoginRequest adminLoginRequest) {

        String token = appUserService.authenticateUser(
                adminLoginRequest.getUsername(),
                adminLoginRequest.getPassword()
        );

        AppUser user = appUserService.findUserBy(String.valueOf(adminLoginRequest.getUsername()), "username");

        System.out.println("Admin login avvenuto con successo");
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // Endpoint per modificare i dettagli dell'admin
    @PutMapping("/admin-update/{username}")
    public ResponseEntity<String> updateAdmin(@PathVariable String username, @RequestBody UpdateRequest adminUpdateRequest) {

        AppUser updatedAdmin = appUserService.updateUser(username, adminUpdateRequest);

        try {
            String subject = "Vi sono stati dei cambiamenti nelle impostazioni del tuo profilo";
            String body = "Ciao " + adminUpdateRequest.getUsername() + ",\n  ti avvisiamo che le modifiche sono state effettuate.";

            // Invio dell'email di conferma
            emailService.sendEmail(adminUpdateRequest.getEmail(), subject, body);
            System.out.println("Email inviata con successo a " + adminUpdateRequest.getEmail());

        } catch (MessagingException e) {

            System.out.println("Errore nell'invio dell'email di conferma per l'utente " + adminUpdateRequest.getUsername() + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Errore nell'invio dell'email di conferma.");
        }

        return ResponseEntity.ok("Admin aggiornato con successo: " + updatedAdmin.getUsername());
    }

}

package epicode.it.progetto_finale_corso_epicode.security;

import epicode.it.progetto_finale_corso_epicode.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class); // Creazione del logger

    private final AppUserService appUserService;
    private final EmailService emailService;

    //metodi per lo user

    // Endpoint per ottenere i dettagli dell'utente
    @GetMapping("/user-details/{username}")
    public ResponseEntity<AppUser> getUserDetails(@PathVariable String username) {

        Optional<AppUser> user = appUserService.findByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user-register")
    public ResponseEntity<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
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

    @PostMapping("/user-login")
    public ResponseEntity<AuthResponse> UserLogin(@RequestBody UserLoginRequest userLoginRequest) {

        String token = appUserService.authenticateUser(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        );

        Optional<AppUser> user = appUserService.findByUsername(userLoginRequest.getUsername());

        System.out.println("Login avvenuto con successo");
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // Endpoint per modificare i dettagli dell'utente
    @PutMapping("/user-update/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody UserUpdateRequest userUpdateRequest) {

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

    // Endpoint per ottenere i dettagli dell'admin
    @GetMapping("/admin-details/{username}")
    public ResponseEntity<AppUser> getAdminDetails(@PathVariable String username) {

        Optional<AppUser> admin = appUserService.findByUsername(username);
        return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/admin-register")
    public ResponseEntity<String> adminRegister(@RequestBody AdminRegisterRequest adminRegisterRequest) {
        // Controlla se il codice per admin sia corretto
        if (!"leo".equals(adminRegisterRequest.getAdminCode())) {
            return ResponseEntity.status(403).body(null);
        }

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

    @PostMapping("/admin-login")
    public ResponseEntity<AuthResponse> adminLogin(@RequestBody AdminLoginRequest adminLoginRequest) {

        //controlla se il code per admin sia corretto
        if (!"leo".equals(adminLoginRequest.getAdminCode())) {

            return ResponseEntity.status(403).body(null);
        }

        String token = appUserService.authenticateUser(
                adminLoginRequest.getUsername(),
                adminLoginRequest.getPassword()
        );

        Optional<AppUser> user = appUserService.findByUsername(adminLoginRequest.getUsername());

        System.out.println("Admin login avvenuto con successo");
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // Endpoint per modificare i dettagli dell'admin
    @PutMapping("/admin-update/{username}")
    public ResponseEntity<String> updateAdmin(@PathVariable String username, @RequestBody AdminUpdateRequest adminUpdateRequest) {

        AppUser updatedAdmin = appUserService.updateAdmin(username, adminUpdateRequest);

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

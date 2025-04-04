package epicode.it.progetto_finale_corso_epicode.security;

import epicode.it.progetto_finale_corso_epicode.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final EmailService emailService;

    //metodi per lo user

    @PostMapping("/user-register")
    public ResponseEntity<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        appUserService.registerUser(
                userRegisterRequest.getUsername(),
                userRegisterRequest.getPassword(),
                Set.of(Role.ROLE_USER),
                userRegisterRequest.getEmail()

        );

        try {
            String subject = "Registrazione al sito 'Viaggi di Passione'";
            String body = "Ciao " + userRegisterRequest.getUsername() + ",\n  Avvenuta con successo, in caso volesse togliere la sua email vada in impostazioni del sito citato.";

            emailService.sendEmail(userRegisterRequest.getEmail(), subject, body);
            emailService.sendEmail(userRegisterRequest.getEmail(), subject);

            System.out.println("Email inviati con successo");

        } catch (MessagingException e) {

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

    //metodi per l'admin

    @PostMapping("/admin-register")
    public ResponseEntity<String> adminRegister(@RequestBody AdminRegisterRequest adminRegisterRequest) {

        //controlla se il code per admin sia corretto
        if (!"leo".equals(adminRegisterRequest.getAdminCode())) {

            return ResponseEntity.status(403).body(null);
        }

        appUserService.registerUser(
                adminRegisterRequest.getUsername(),
                adminRegisterRequest.getPassword(),
                Set.of(Role.ROLE_ADMIN),
                adminRegisterRequest.getEmail()

        );

        try {
            String subject = "Registrazione al sito 'Viaggi di Passione'";
            String body = "Salve Admin,\n  Avvenuta con successo, in caso volesse togliere la sua email vada in impostazioni del sito citato.";

            emailService.sendEmail(adminRegisterRequest.getEmail(), subject, body);
            emailService.sendEmail(adminRegisterRequest.getEmail(), subject);

        } catch (MessagingException e) {

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
}

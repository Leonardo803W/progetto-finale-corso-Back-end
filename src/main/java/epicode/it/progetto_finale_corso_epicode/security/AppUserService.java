package epicode.it.progetto_finale_corso_epicode.security;

import epicode.it.progetto_finale_corso_epicode.GeneralResponseWithMessage;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //metodo per la registrazione dell'utente
    public AppUser registerUser(String username, String password, Set<Role> roles, String email) {

        if (appUserRepository.existsByUsername(username)) {

            throw new EntityExistsException("Username già in uso");
        }

        if (appUserRepository.existsByEmail(email)) {

            throw new EntityExistsException("Email già in uso");
        }

        if (appUserRepository.existsByPassword(password)) {

            throw new EntityExistsException("Password già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRoles(roles);
        appUser.setEmail(email);
        return appUserRepository.save(appUser);
    }

    //metodo per l'autenticazione dell'utente
    public String authenticateUser(String username, String password)  {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);

        } catch (AuthenticationException e) {

            throw new SecurityException("Credenziali non valide", e);
        }
    }

    //metodo per la ricerca dell'utente
    public AppUser findUserBy(String value, String searchType) {

        switch (searchType.toLowerCase()) {

            case "email":
                return appUserRepository.findByEmail(value);

            case "username":
                return appUserRepository.findByUsername(value);

            default:
                throw new IllegalArgumentException("Tipo di ricerca non supportato: " + searchType);
        }
    }

    /*
    public AppUser deleteUser(String identifier) {

        if (!appUserRepository.existsByEmail(identifier)) {

            throw new UsernameNotFoundException("Utente non trovato con l'identificatore: " + identifier);
        }

        appUserRepository.deleteByidentifier(identifier);

        System.out.println("Utente cancellato con: " + identifier);
        return null;
    }

     */


    //metodo per l'aggiornamento dell'utente
    public AppUser updateUser(String identifier, UpdateRequest userUpdateRequest) {

        AppUser appUser;

        if (Objects.equals(identifier, userUpdateRequest.getEmail())) {

            appUser = findUserBy(identifier, "email");

        } else {

            throw new UsernameNotFoundException("Utente non trovato con l'identificatore: " + identifier);
        }

        if (Objects.equals(identifier, userUpdateRequest.getUsername())) {

            appUser = findUserBy(identifier, "username");

        } else {

            throw new UsernameNotFoundException("Utente non trovato con l'identificatore: " + identifier);
        }

        if (userUpdateRequest.getEmail() != null) {

            appUser.setEmail(userUpdateRequest.getEmail());
        }

        if (userUpdateRequest.getPassword() != null) {

            appUser.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }

        if (userUpdateRequest.getUsername() != null) {

            appUser.setUsername(userUpdateRequest.getUsername());
        }

        if (userUpdateRequest.getAvatar() != null) {

            appUser.setAvatar(userUpdateRequest.getAvatar());
        }

        return appUserRepository.save(appUser);
    }
}

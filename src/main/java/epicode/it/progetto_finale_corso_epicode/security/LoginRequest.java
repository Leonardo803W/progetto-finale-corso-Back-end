package epicode.it.progetto_finale_corso_epicode.security;


import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}

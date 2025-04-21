package epicode.it.progetto_finale_corso_epicode.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Avatar is required")
    private String avatar;

    @NotBlank(message = "Password is required")
    private String password;

}
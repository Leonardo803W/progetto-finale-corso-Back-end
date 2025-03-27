package epicode.it.progetto_finale_corso_epicode.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorsPayload {

    private String message;
    private LocalDateTime timestamp;
}


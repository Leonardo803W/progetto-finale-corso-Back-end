package epicode.it.progetto_finale_corso_epicode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponseWithMessage<T> {

    private T data;
    private String message;
}

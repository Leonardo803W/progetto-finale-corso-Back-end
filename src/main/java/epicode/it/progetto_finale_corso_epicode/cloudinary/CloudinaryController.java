package epicode.it.progetto_finale_corso_epicode.cloudinary;


import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class CloudinaryController {
    private final Cloudinary cloudinary;

    @PostMapping(path="/uploadme", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestPart("file")MultipartFile file) {

        try {

            Map result = cloudinary.uploader().upload(file.getBytes(),  Cloudinary.asMap("folder", "FS0824", "public_id", file.getOriginalFilename()));

            String url = result.get("secure_url").toString();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

}
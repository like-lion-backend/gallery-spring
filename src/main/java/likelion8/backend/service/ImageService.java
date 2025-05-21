package likelion8.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final Path imageDir = Paths.get("images").toAbsolutePath();

    public String saveImageGetUrl(MultipartFile image) throws IOException{
        String originalFilename = image.getOriginalFilename();
        String extension = getExtension(originalFilename);
        String savedFilename = UUID.randomUUID().toString() + "." + extension;

        Path savedPath = imageDir.resolve(savedFilename);
        image.transferTo(savedPath.toFile());

        return "/images/" + savedFilename;
    }

    private String getExtension(String filename) throws IOException{
        if (filename == null || filename.isBlank() || !filename.contains(".")) {
            throw new IOException("확장자가 없는 이미지 파일입니다.");
        }
        return filename.substring(filename.lastIndexOf(".")+1);
    }
}

package likelion8.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    // 이미지를 저장할 폴더의 위치를 미리 상수로 정의
    private final Path imageDir = Paths.get("images").toAbsolutePath();

    // 이미지를 저장하고 접근할 수 있는 url을 반환하는 메소드
    public String saveImageGetUrl(MultipartFile image) throws IOException{
        String originalFilename = image.getOriginalFilename(); // 이미지 파일의 원본 이름
        String extension = getExtension(originalFilename); // 확장자만 추출 (.png .jpg)

        // 저장되는 파일의 이름은 "랜덤 문자열.png" 형식
        String savedFilename = UUID.randomUUID().toString() + "." + extension;

        // .../imageDir/savedFilename 으로 경로를 설정하는 메소드 (.../images/e2dsf2dgn.png) 이런 식의 경로가 됩니다.
        Path savedPath = imageDir.resolve(savedFilename);
        image.transferTo(savedPath.toFile()); // 실제 이미지 파일을 저장

        return "/images/" + savedFilename; // 저장한 이미지를 조회할 수 있는 url 반환 (앞서 WebConfig에서 매핑 해줬음)
    }

    // 이미지 파일의 확장자만 추출하는 메소드
    private String getExtension(String filename) throws IOException{
        if (filename == null || filename.isBlank() || !filename.contains(".")) {
            throw new IOException("확장자가 없는 이미지 파일입니다.");
        }
        return filename.substring(filename.lastIndexOf(".")+1);
    }
}

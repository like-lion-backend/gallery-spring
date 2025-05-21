package likelion8.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GalleryRequestDto {
    // 클라이언트 -> 서버로 보내는 요청의 형태를 정의 (이미지는 json이 아닌 파일 형태로 받기 때문에 제외)
    private Long id;
    private String title;
    private String description;
}

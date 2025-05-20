package likelion8.backend.dto;

import likelion8.backend.domain.Gallery;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GalleryResponseDto {
    // GET /api/galleries 응답 필드들을 정의
    // (엔티티를 그대로 전달하지 않고, 전달할 필드들만 선택해서 응답 가능)

    private Long id;
    private String image;
    private String title;
    private String description;

    // Gallery 인스턴스를 받아서 편하게 dto로 변환해주는 생성자 정의 (개인취향 반영됨)
    public GalleryResponseDto(Gallery gallery) {
        this.id = gallery.getId();
        this.image = gallery.getImage();
        this.title = gallery.getTitle();
        this.description = gallery.getDescription();
    }
}

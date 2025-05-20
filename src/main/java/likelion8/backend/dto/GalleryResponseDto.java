package likelion8.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GalleryResponseDto {
    private Long id;
    private String image;
    private String title;
    private String description;
}

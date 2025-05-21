package likelion8.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GalleryRequestDto {
    private Long id;
    private String title;
    private String description;
}

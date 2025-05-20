package likelion8.backend.service;

import likelion8.backend.domain.Gallery;
import likelion8.backend.dto.GalleryResponseDto;
import likelion8.backend.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 서비스 클래스임을 나타내는 어노테이션
@RequiredArgsConstructor //아래 final로 선언된 필드들만 골라서 생성자를 자동으로 생성!
public class GalleryService {

    private final GalleryRepository galleryRepository; // (컨트롤러 -> 서비스 -> 레포지토리) 서비스에서는 레포지토리를 사용해서 로직 작성

    public List<GalleryResponseDto> getAllGalleries() {

        // JpaRepository에 findAll()은 기본적으로 정의되어있음 (모든 인스턴스 조회)
        List<Gallery> galleries = galleryRepository.findAll();

        // Gallery 인스턴스들을 하나씩 순회
        // Dto에서 정의해줬던 생성자로 dto로 변환
        return galleries.stream().map(
                gallery -> new GalleryResponseDto(gallery)
        ).toList(); // 마지막으로 리스트 타입으로 변환 (최종 형태는 List<GalleryResponseDto>)
    }
}

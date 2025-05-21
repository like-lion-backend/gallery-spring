package likelion8.backend.service;

import likelion8.backend.domain.Gallery;
import likelion8.backend.dto.GalleryRequestDto;
import likelion8.backend.dto.GalleryResponseDto;
import likelion8.backend.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 클래스임을 나타내는 어노테이션
@RequiredArgsConstructor //아래 final로 선언된 필드들만 골라서 생성자를 자동으로 생성!
public class GalleryService {

    private final GalleryRepository galleryRepository; // (컨트롤러 -> 서비스 -> 레포지토리) 서비스에서는 레포지토리를 사용해서 로직 작성

    @Transactional(readOnly = true)
    public List<GalleryResponseDto> getAllGalleries() {

        // JpaRepository에 findAll()은 기본적으로 정의되어있음 (모든 인스턴스 조회)
        List<Gallery> galleries = galleryRepository.findAll();

        // Gallery 인스턴스들을 하나씩 순회
        // Dto에서 정의해줬던 생성자로 dto로 변환
        return galleries.stream().map(
                gallery -> new GalleryResponseDto(gallery)
        ).toList(); // 마지막으로 리스트 타입으로 변환 (최종 형태는 List<GalleryResponseDto>)
    }

    // 프론트에서 데이터 보냃 때, 주의!!
    @Transactional
    public void createGallery(GalleryRequestDto dto, String imageUrl) {
        Gallery gallery = new Gallery(dto); // 전달받은 json 데이터로 Gallery 인스턴스 생성
        gallery.setImage(imageUrl); // 이미지 업로드 과정을 거쳐서 받은 url을 인스턴스에 추가로 설정
        galleryRepository.save(gallery); // DB에 저장 (save 메소드는 JpaRepository에서 기본으로 제공)
    }

    @Transactional
    public GalleryResponseDto updateGallery(GalleryRequestDto dto, Long galleryId) {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new RuntimeException("해당 id를 가진 갤러리가 존재하지 않습니다: " + galleryId));
        gallery.update(dto);
        return new GalleryResponseDto(gallery);
    }

    @Transactional
    public void deleteGallery(Long galleryId) {
        if (!galleryRepository.existsById(galleryId)) {
            throw new RuntimeException("해당 갤러리가 존재하지 않습니다: " + galleryId);
        }
        galleryRepository.deleteById(galleryId);
    }
}

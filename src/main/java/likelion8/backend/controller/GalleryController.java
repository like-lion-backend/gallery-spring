package likelion8.backend.controller;

import likelion8.backend.dto.GalleryRequestDto;
import likelion8.backend.dto.GalleryResponseDto;
import likelion8.backend.service.GalleryService;
import likelion8.backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController //RESTfull 웹 서비스를 만들 때 사용하는 어노테이션. html페이지가 아니라 api 응답을 주고받는 컨트롤러
@RequestMapping("/api/galleries") //클래스에 붙일 경우 공통 URL 역할. 아래 메소드들은 이 경로로 시작되는 요청들을 받음
@RequiredArgsConstructor //아래 final로 선언된 필드들만 골라서 생성자를 자동으로 생성!
public class GalleryController {

    private final GalleryService galleryService; // (컨트롤러 -> 서비스 -> 레포지토리) 컨트롤러에서는 서비스를 사용해서 로직
    private final ImageService imageService; // post 요청에서 사용할 이미지 서비스

    @GetMapping // GET 요청을 받는다는 것을 명시 (GET /api/galleries)
    public ResponseEntity<List<GalleryResponseDto>> getGalleries() {

        // 서비스에 정의해줬던 메소드를 사용해서 컨트롤러 로직 작성
        List<GalleryResponseDto> galleries = galleryService.getAllGalleries();
        return ResponseEntity.ok(galleries); // code: 200 OK, response body에는 gallereis가 들어감
    }

    @PostMapping // POST 요청을 받는다는 것을 명시 (POST /api/galleries)
    public ResponseEntity<Void> postGalleries(
            @RequestPart("image") MultipartFile image, // 파일 형식의 데이터
            @RequestPart("data") GalleryRequestDto data // json 형식의 데이터
    ) {
        String imageUrl = "";
        try {
            imageUrl = imageService.saveImageGetUrl(image); // 이미지를 저장하고 url을 받아오는 메소드
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 과정에서 문제가 생겼습니다.");
        }

        galleryService.createGallery(data, imageUrl); // 직접 정의한 Gallery 생성 메소드 사용

        return ResponseEntity.status(HttpStatus.CREATED).build(); // 상태코드: 201 Created, response body는 없습니다!
    }

    @PutMapping("/{galleryId}")
    public ResponseEntity<GalleryResponseDto> postGallery(
            @RequestBody GalleryRequestDto dto,
            @PathVariable Long galleryId
    ) {
        GalleryResponseDto response = galleryService.updateGallery(dto, galleryId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // Lombok의 RequiredArgsConstructor가 아래와 같은 생성자를 만들어 줌
//    public GalleryController(GalleryService galleryService) {
//        this.galleryService = galleryService;
//    }
}

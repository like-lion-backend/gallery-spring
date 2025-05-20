package likelion8.backend.controller;

import likelion8.backend.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //RESTfull 웹 서비스를 만들 때 사용하는 어노테이션. html페이지가 아니라 api 응답을 주고받는 컨트롤러
@RequestMapping("/api/galleries") //클래스에 붙일 경우 공통 URL 역할. 아래 메소드들은 이 경로로 시작되는 요청들을 받음
@RequiredArgsConstructor //아래 final로 선언된 필드들만 골라서 생성자를 자동으로 생성!
public class GalleryController {

    private final GalleryService galleryService; // (컨트롤러 -> 서비스 -> 레포지토리) 컨트롤러에서는 서비스를 사용해서 로직 작성

    // Lombok의 RequiredArgsConstructor가 아래와 같은 생성자를 만들어 줌
//    public GalleryController(GalleryService galleryService) {
//        this.galleryService = galleryService;
//    }
}

package likelion8.backend.repository;

import likelion8.backend.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

// "엔티티를 DB와 주고받는 역할을 담당하는 계층이다." "데이터베이스와 소통하는 창구"
// JpaRepository를 상속받는 이유? -> 직접 구현하지 않아도 Spring Data JPA가 내부에서 구현체를 만들어서 자동으로 Bean으로 등록
// 어떤 엔티티를 다루는지, 엔티티의 pk 데이터타입이 뭔지 전달해주면 됨
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}

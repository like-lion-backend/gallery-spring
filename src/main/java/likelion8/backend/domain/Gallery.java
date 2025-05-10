package likelion8.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Gallery {

    /*
    * @Id: id라는 컬럼을 기본키(primary key)로 설정
    * @GeneratedValue: 인스턴스 생성 때, 기본키를 자동으로 생성
    * GenerationType.IDENTITY: 기본키 생성을 데이터베이스에 위임
    * (다른 정책으로는 SEQUENCE, TABLE 등등...) JPA 기본키 생성 전략에 찾아보시길 추천!
    */
    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY)//인스턴스 생성 때, 기본키를 자동으로 생성
    private Long id; // Integer보다 큰 범위를 저장할 수 있는 타입

    private String image; // 이미지 파일을 직접 저장하지 않고, 접근할 수 있는 URL만 저장(가장 기본적인 방식)

    private String title;

    private String description;

    private LocalDateTime lastUpdate; // 최근 수정 시간을 "2025-05-10 16:32:56" 처럼 날짜와 시간을 저장
}

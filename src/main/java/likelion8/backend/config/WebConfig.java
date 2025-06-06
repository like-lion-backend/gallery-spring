package likelion8.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // CORS를 허용할 서버의 엔드포인트
                        .allowedOrigins("http://localhost:3000") // 허용할 클라이언트 URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
                        .allowCredentials(true); // 쿠키 등 인증 정보 허용
            }
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            // "./images" 이렇게 상대 경로로 폴더 위치를 가져옴
            // 이후 "/Users/you/project/images" 처럼 절대 경로로 변환
            Path imageDir = Paths.get("images").toAbsolutePath();

            // 폴더가 존재하지 않으면 생성
            if (Files.notExists(imageDir)) {
                Files.createDirectories(imageDir);
            }

            // "/images"로 시작하는 url에 "images" 폴더 안에 있는 사진들을 매핑
            registry.addResourceHandler("/images/**")
                    .addResourceLocations(imageDir.toUri().toString());

        } catch (IOException e) {
            throw new RuntimeException("이미지 디렉토리를 생성하거나 설정하는 중 오류 발생", e);
        }
    }
}
package event;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 스프링의 비동기 실행 기능 활성화
public class MainApplication {

    public static void main(String[] args) {
        MainApplication.run(MainApplication.class, args);
    }

    private static void run(Class<MainApplication> mainApplicationClass, String[] args) {

    }
}

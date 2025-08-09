package io.github.Twebubble.musicure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.github.Twebubble.musicure.mapper")
public class MusicureApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicureApplication.class, args);
    }

}

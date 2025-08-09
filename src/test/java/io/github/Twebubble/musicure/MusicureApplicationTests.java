package io.github.Twebubble.musicure;

import io.github.Twebubble.musicure.model.domain.Consumer;
import io.github.Twebubble.musicure.service.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MusicureApplicationTests {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @Test
    void contextLoads() {
        Consumer consumer = new Consumer();
        consumer.setUsername("haha");
        consumer.setPassword("789");
//        consumer.setSex(new Byte("1"));
        consumer.setSex(Byte.parseByte("1"));
        consumer.setPhoneNum("15067954413");
        consumer.setEmail("753951@pp.com");
        consumer.setBirth(new Date());
        consumer.setIntroduction("这个人什么也没说~");
        consumer.setLocation("To the Moon");
        consumer.setAvator("/img/haha.jpg");
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());
        consumerService.addUser(consumer);
    }

}

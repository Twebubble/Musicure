package io.github.Twebubble.musicure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class MusicureApplicationTests {
	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() throws Exception {
		System.out.println(dataSource.getConnection());
	}

}

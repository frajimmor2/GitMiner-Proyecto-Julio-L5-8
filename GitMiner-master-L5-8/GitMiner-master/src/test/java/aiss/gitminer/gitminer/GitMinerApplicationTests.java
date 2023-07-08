package aiss.gitminer.gitminer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("aiss.gitminer.repository")
@ComponentScan("aiss.gitminer")
@SpringBootTest
class GitMinerApplicationTests {

	@Test
	void contextLoads() {
	}

}

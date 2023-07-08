package AISS.AISS;

import AISS.AISS.model.Project;
import AISS.AISS.service.GitLabService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AissApplicationTests {

	@Autowired
	GitLabService service;

	@Test
	void findProject() {
		Project project = service.findOne("4207231", Optional.of(2), Optional.of(20), Optional.of(20));
		assertTrue(project != null, "Could not find the project");
		System.out.println(project);
	}
}

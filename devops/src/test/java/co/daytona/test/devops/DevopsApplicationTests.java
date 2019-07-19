package co.daytona.test.devops;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DevopsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSkills() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/devops/skills").contentType(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
					.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)))
					.andExpect(MockMvcResultMatchers.jsonPath("$",
							Matchers.containsInAnyOrder("Jenkins", "Maven", "Docker", "Kubernetes", "AWS")));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}

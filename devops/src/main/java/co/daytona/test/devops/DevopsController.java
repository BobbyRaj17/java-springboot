package co.daytona.test.devops;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevopsController {

	@GetMapping(path = "/devops/skills", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getSkills() {
		List<String> skills = new ArrayList<String>();
		skills.add("Jenkins");
		skills.add("Maven");
		skills.add("Docker");
		skills.add("Kubernetes");
		skills.add("AWS");
		return new ResponseEntity<List<String>>(skills, HttpStatus.OK);
	}
}

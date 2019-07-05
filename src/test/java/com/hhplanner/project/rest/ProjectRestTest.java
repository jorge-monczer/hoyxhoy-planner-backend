package com.hhplanner.project.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hhplanner.entities.model.Project;
import com.hhplanner.mockups.MockupsToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectRestTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void getProjects_ReturnsAllProject() {
		ResponseEntity<List<Project>> response = this.testRestTemplate.exchange(
				"/api/projects", HttpMethod.GET, null,  new ParameterizedTypeReference<List<Project>>(){});
		List<Project> projects = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(Objects.deepEquals(projects, MockupsToTest.createProjectListToTest()));
		//assertThat(peopleList.getBody()).containsOnly(japan, america);
	}
	
	@Test
	public void getProject_WithId_ReturnsProject() {
		ResponseEntity<Project> responseEntity = this.testRestTemplate.getForEntity("/api/project/{id}", Project.class,1);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(Objects.equals(project, MockupsToTest.createProjectTLMK()));
	}

	@Test
	public void getProject_WithCode_ReturnsProject() {
		ResponseEntity<Project> responseEntity = this.testRestTemplate.getForEntity("/api/project/code/{code}", Project.class,"TLMK");
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(Objects.equals(project, MockupsToTest.createProjectTLMK()));
	}
	
	@Test
	public void postProject_ReturnsProject() {
		ResponseEntity<Project> responseEntity = this.testRestTemplate.postForEntity("/api/project",MockupsToTest.createProjectTLMK4ToSave(), Project.class);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(Objects.equals(project, MockupsToTest.createProjectTLMK()));
	}

	@Test
	public void deleteProject_WithId() {
		ResponseEntity<Void> response = this.testRestTemplate.exchange(
				"/api/project/{id}", HttpMethod.DELETE,HttpEntity.EMPTY,Void.class,1);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void deleteProjects() {
		ResponseEntity<Void> response = this.testRestTemplate.exchange(
				"/api/projects", HttpMethod.DELETE,HttpEntity.EMPTY,Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}

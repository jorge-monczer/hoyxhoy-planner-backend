package com.hhplanner.entities.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.mockups.MockupProjectsToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectRestTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean
	private ProjectService projectService;	
	
	@Test
	public void getProject_WithId_ReturnsProject() {
		when(this.projectService.getProjectById(anyInt())).thenReturn(MockupProjectsToTest.createProjectTLMK(1));
		ResponseEntity<Project> responseEntity = this.testRestTemplate.getForEntity("/api/projects/{id}", Project.class,1);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(1));
	}

	@Test
	public void getProyect_WithId_ReturnNotFound() {
		when(this.projectService.getProjectById(anyInt())).thenThrow(new EntityModelNotFoundException());
		ResponseEntity<Project> responseEntity = this.testRestTemplate.getForEntity("/api/projects/{id}", Project.class,1);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(project).isEqualToComparingFieldByField(new Project());		
	}

	@Test
	public void getProject_WithCode_ReturnsProject() {
		when(this.projectService.getProjectByCode(anyString())).thenReturn(MockupProjectsToTest.createProjectTLMK(1));
		ResponseEntity<Project> responseEntity = this.testRestTemplate.getForEntity("/api/projects/code/{code}", Project.class,"TLMK");
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(1));
	}
	
	@Test
	public void getProyect_WithCode_RetrunNotFound() throws Exception {
		when(this.projectService.getProjectByCode(anyString())).thenThrow(new EntityModelNotFoundException());
		ResponseEntity<Project> responseEntity = this.testRestTemplate.getForEntity("/api/projects/code/{code}", Project.class,"TLMK");
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(project).isEqualToComparingFieldByField(new Project());		
	}

	@Test
	public void getProjects_ReturnsAllProject() {
		List<Project> projectList = MockupProjectsToTest.createProjectListToTest();
		when(this.projectService.getProjects()).thenReturn(projectList);
		ResponseEntity<List<Project>> response = this.testRestTemplate.exchange(
				"/api/projects", HttpMethod.GET, null,  new ParameterizedTypeReference<List<Project>>(){});
		List<Project> projects = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		for (int i = 0; i < projects.size(); i++) {
			assertThat(projects.get(i)).isEqualToComparingFieldByField(projectList.get(i));			
		}
	}

	@Test
	public void getProjects_ReturnsNoContent() {
		when(this.projectService.getProjects()).thenReturn(new ArrayList<Project>());
		ResponseEntity<List<Project>> response = this.testRestTemplate.exchange(
				"/api/projects", HttpMethod.GET, null,  new ParameterizedTypeReference<List<Project>>(){});
		List<Project> projects = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(projects).isNull();
	}
	
	@Test
	public void postProject_ReturnsProject() {
		when(this.projectService.save(any(Project.class))).thenReturn(MockupProjectsToTest.createProjectTLMK(1));
		ResponseEntity<Project> responseEntity = this.testRestTemplate.postForEntity("/api/projects",MockupProjectsToTest.createProjectTLMK(0), Project.class);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(1));
	}

	@Test
	public void postProject_WithDuplicateCode() {
		when(this.projectService.save(any(Project.class))).thenThrow(EntityModelDuplicatedException.getInstance("msg"));
		ResponseEntity<Project> responseEntity = this.testRestTemplate.postForEntity("/api/projects",MockupProjectsToTest.createProjectTLMK(0), Project.class);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertThat(project).isEqualToComparingFieldByField(new Project());
	}
	
	@Test
	public void putProject_whithId_ReturnsProject() {
		Project updatedProjectTLMK = MockupProjectsToTest.createProjectTLMK2(1);
		when(this.projectService.update(anyInt(),any(Project.class))).thenReturn(updatedProjectTLMK);
		ResponseEntity<Project> responseEntity = this.testRestTemplate.exchange(
				"/api/projects/{id}", HttpMethod.PUT,new HttpEntity<Project>(MockupProjectsToTest.createProjectTLMK(1)),Project.class,1);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(project).isEqualToComparingFieldByField(updatedProjectTLMK);
	}

	@Test
	public void putProject_whithId_NotFound() {
		when(this.projectService.update(anyInt(),any(Project.class))).thenThrow(new EntityModelNotFoundException());
		ResponseEntity<Project> responseEntity = this.testRestTemplate.exchange(
				"/api/projects/{id}", HttpMethod.PUT,new HttpEntity<Project>(MockupProjectsToTest.createProjectTLMK(1)),Project.class,1);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(project).isEqualToComparingFieldByField(new Project());
	}

	@Test
	public void putProject_whithId_DuplicatedCode() {
		when(this.projectService.update(anyInt(),any(Project.class))).thenThrow(EntityModelDuplicatedException.getInstance("msg"));
		ResponseEntity<Project> responseEntity = this.testRestTemplate.exchange(
				"/api/projects/{id}", HttpMethod.PUT,new HttpEntity<Project>(MockupProjectsToTest.createProjectTLMK(1)),Project.class,1);
		Project project = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertThat(project).isEqualToComparingFieldByField(new Project());
	}
	
	@Test
	public void deleteProject_WithId() {
		ResponseEntity<Void> response = this.testRestTemplate.exchange(
				"/api/projects/{id}", HttpMethod.DELETE,HttpEntity.EMPTY,Void.class,1);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void deleteProjects() {
		ResponseEntity<Void> response = this.testRestTemplate.exchange(
				"/api/projects", HttpMethod.DELETE,HttpEntity.EMPTY,Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}

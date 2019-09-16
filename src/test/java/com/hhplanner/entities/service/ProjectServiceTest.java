package com.hhplanner.entities.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.repo.ProjectRepository;
import com.hhplanner.mockups.MockupProjectsToTest;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {
	@Mock
	private ProjectRepository projectRepository;

	private ProjectService projectService;

	@Before
	public void setUp() throws Exception {
		this.projectService = new ProjectService(projectRepository);
	}

	@Test
	public void getProjectById_returnsProject() {
		given(this.projectRepository.findById(anyInt())).willReturn(Optional.of(MockupProjectsToTest.createProjectTLMK(1)));
		Project project = projectService.getProjectById(1);
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(1));
	}

	@Test(expected = EntityModelNotFoundException.class)
	public void getProyect_WithId_NotFoundException() throws Exception {
		given(this.projectRepository.findById(anyInt())).willReturn(Optional.empty());
		this.projectService.getProjectById(1);
	}
	
	@Test
	public void getProjectByCode_returnsProject() {
		given(this.projectRepository.findByCode(anyString())).willReturn(Optional.of(MockupProjectsToTest.createProjectTLMK(1)));
		Project project = this.projectService.getProjectByCode("TLMK");
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(1));
	}
	
	@Test(expected = EntityModelNotFoundException.class)
	public void getProyect_WithCode_NotFoundException() throws Exception {
		given(this.projectRepository.findByCode(anyString())).willReturn(Optional.empty());
		this.projectService.getProjectByCode("TLMK");
	}

	@Test
	public void getProjects() {
		List<Project> projectList = MockupProjectsToTest.createProjectListToTest();
		given(this.projectRepository.findAll()).willReturn(projectList);
		Iterable<Project> projects = this.projectService.getProjects();
		int i = 0;
		for (Project project : projects) {
			assertThat(project).isEqualToComparingFieldByField(projectList.get(i++));			
		}
	}

	@Test
	public void saveProject_ReturnsProject() {
		given(this.projectRepository.save(any(Project.class))).willReturn(MockupProjectsToTest.createProjectTLMK(1));
		Project project = this.projectService.save(MockupProjectsToTest.createProjectTLMK(0));		
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(1));
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void saveProject_DuplicatedCode() {
		given(this.projectRepository.save(any(Project.class))).willThrow(new DataIntegrityViolationException("msg"));
		Project project = this.projectService.save(MockupProjectsToTest.createProjectTLMK(0));		
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(1));
	}
	
	@Test
	public void updateProject_ReturnsProject() {
		given(this.projectRepository.existsById(anyInt())).willReturn(true);
		given(this.projectRepository.save(any(Project.class))).willReturn(MockupProjectsToTest.createProjectTLMK2(1));
		Project project = this.projectService.update(1,MockupProjectsToTest.createProjectTLMK2(1));		
		assertThat(project).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK2(1));
	}

	@Test(expected = EntityModelNotFoundException.class)
	public void updateProject_IDNotFound() {
		given(this.projectRepository.existsById(anyInt())).willReturn(false);
		Project project = this.projectService.update(3,MockupProjectsToTest.createProjectTLMK2(1));		
		assertThat(project).isEqualToComparingFieldByField(new Project());
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void updateProject_DuplicateCode() {
		given(this.projectRepository.existsById(anyInt())).willReturn(true);
		given(this.projectRepository.save(any(Project.class))).willThrow(new DataIntegrityViolationException("msg"));
		Project project = this.projectService.update(3,MockupProjectsToTest.createProjectTLMK2(1));		
		assertThat(project).isEqualToComparingFieldByField(new Project());
	}
	@Test
	public void deleteProject_WithId() {
		this.projectService.delete(1);
	}

	@Test
	public void deleteProjects() throws Exception {
		this.projectService.deleteAll();
	}
	
}

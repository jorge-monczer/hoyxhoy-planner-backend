package com.hhplanner.entities.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.assertj.core.util.IterableUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.hhplanner.entities.model.Project;
import com.hhplanner.mockups.MockupProjectsToTest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private EntityManager em;

	@Test
	public void findById_ReturnsProject() throws Exception {
		Project savedProject = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		Optional<Project> project = this.repository.findById(savedProject.getId());
		assertThat(project.isPresent());
		assertThat(savedProject).isEqualToComparingFieldByField(project.get());
	}
	
	@Test
	public void findByCode_ReturnsProject() throws Exception {
		Project savedProject = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		Optional<Project> project = this.repository.findByCode(savedProject.getCode());
		assertThat(project.isPresent());
		assertThat(savedProject).isEqualToComparingFieldByField(project.get());
	}
	
	@Test
	public void findAll_ReturnsAllProject() throws Exception {
		List<Project> projectListToSave = MockupProjectsToTest.createProjectListToSaveTest();
		int sizeProjectToSave = projectListToSave.size();
		List<Project> savedProjects = new ArrayList<>();
		for (Project projectToSave : projectListToSave) {
			savedProjects.add(testEntityManager.persistFlushFind(projectToSave));
		}
		Iterable<Project> projects = this.repository.findAll();
		assertThat(IterableUtil.sizeOf(projects)).isEqualTo(sizeProjectToSave);
		int i = 0;
		for (Project project : projects) {
			assertThat(project).isEqualToComparingFieldByField(savedProjects.get(i++));			
		}
	}

	@Test
	public void saveProject_ReturnsProject() throws Exception {
		Project projectSaved = this.repository.save(MockupProjectsToTest.createProjectTLMK(0));
		em.flush();
		assertThat(projectSaved).isNotNull();
		assertThat(projectSaved.getId()).isGreaterThan(0);
		assertThat(projectSaved).isEqualToComparingFieldByField(MockupProjectsToTest.createProjectTLMK(projectSaved.getId()));
		assertTrue(this.repository.existsById(projectSaved.getId()));
	}

	@Test
	public void saveProject_WithNoExistingId_ReturnsProject() throws Exception {
		Project project = MockupProjectsToTest.createProjectTLMK(1);
		Project projectSaved = this.repository.save(project);
		em.flush();
		assertThat(projectSaved).isNotNull();
		assertThat(projectSaved.getId()).isNotEqualTo(project.getId());
		project.setId(projectSaved.getId());
		assertThat(projectSaved).isEqualToComparingFieldByField(project);
		assertTrue(this.repository.existsById(projectSaved.getId()));
	}
	
	@Test(expected = PersistenceException.class)
	public void saveProject_WithExistingCode_PersistenceException() throws Exception {
		Project projectInDB = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		Project projectSameCode = MockupProjectsToTest.createProjectTLMK2(0);
		projectSameCode.setCode(projectInDB.getCode());
		this.repository.save(projectSameCode);
		em.flush();
	}

	@Test(expected = PersistenceException.class)
	public void saveProject_WithExistingName_PersistenceException() throws Exception {
		Project projectInDB = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		Project projectSameName = MockupProjectsToTest.createProjectTLMK2(0);
		projectSameName.setName(projectInDB.getName());
		this.repository.save(projectSameName);
		em.flush();
	}

	@Test
	public void updateProject_ReturnsProject() throws Exception {
		Project projectInDB = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		Project projectInDBUpdated = MockupProjectsToTest.createProjectTLMK2(projectInDB.getId());
		Project projectSaved = this.repository.save(projectInDBUpdated);
		em.flush();
		assertThat(projectSaved).isNotNull();
		assertThat(projectSaved).isEqualToComparingFieldByField(projectInDBUpdated);
		Optional<Project> projectRetrieved = this.repository.findByCode(projectSaved.getCode());
		assertThat(projectRetrieved.isPresent());
		assertThat(projectSaved).isEqualToComparingFieldByField(projectRetrieved.get());
	}

	@Test(expected = PersistenceException.class)
	public void updateProject_WithExistingCode_PersistenceException() throws Exception {
		testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK2(0));
		Project projectInDB = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		Project projectInDBUpdated = MockupProjectsToTest.createProjectTLMK2(projectInDB.getId());
		this.repository.save(projectInDBUpdated);
		em.flush();
	}

	
	@Test
	public void findByCode_DeleteById() throws Exception {
		Project savedProject = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		this.repository.deleteById(savedProject.getId());
		Optional<Project> project2 = this.repository.findById(savedProject.getId());
		assertThat(!project2.isPresent());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteById_NotFound() throws Exception {
		Project savedProject = testEntityManager.persistFlushFind(MockupProjectsToTest.createProjectTLMK(0));
		this.repository.deleteById(savedProject.getId()+100);
	}

	@Test
	public void deleteAll() throws Exception {
		List<Project> projectListToSave = MockupProjectsToTest.createProjectListToSaveTest();
		int sizeProjectToSave = projectListToSave.size();
		for (Project projectToSave : projectListToSave) {
			testEntityManager.persistFlushFind(projectToSave);
		}
		this.repository.deleteAll();
		Iterable<Project> projects = this.repository.findAll();
		assertThat(sizeProjectToSave).isGreaterThan(0);
		assertThat(IterableUtil.sizeOf(projects)).isEqualTo(0);
	}
	
}

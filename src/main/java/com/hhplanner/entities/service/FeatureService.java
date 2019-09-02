package com.hhplanner.entities.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.repo.FeatureRepository;

@Service
public class FeatureService {

	private FeatureRepository featureRepository;
	private ProjectService projectService;
	
	public FeatureService(FeatureRepository featureRepository, ProjectService projectService) {
		this.featureRepository = featureRepository;
		this.projectService = projectService;
	}

	public Feature getFeatureById(int id) {
		Optional<Feature> feature = this.featureRepository.findById(id);
		if (!feature.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return feature.get();
	}

	public Feature getFeatureByCodeAndProjectId( String code, int projectId) {
		Optional<Feature> feature = this.featureRepository.findByCodeAndProjectId(code, projectId);
		if (!feature.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return feature.get();
	}

	public Iterable<Feature> getFeaturesByProjectId(int projectId) {
		return this.featureRepository.findByProjectId(projectId);
	}

	public Feature save(Feature feature, int projectId) {
		Project project = this.projectService.getProjectById(projectId);
		try {
			feature.setProject(project);
			Feature save = this.featureRepository.save(feature);
//			this.springRepository.flush();
			return save;
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} catch (Exception e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		}
	}

	public Feature saveAndFlush(Feature feature, int projectId) {
		Feature save = save(feature,projectId);
		this.featureRepository.flush();
		return save;
	}
	
	public Feature update(int id,Feature feature, int projectId) {
		if (!this.featureRepository.existsById(id)) {
			throw new EntityModelNotFoundException();
		}
		Project project = this.projectService.getProjectById(projectId);
		try {
			feature.setId(id);
			feature.setProject(project);
			return this.featureRepository.save(feature);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} 
	}

	public void delete(int id) {
		this.featureRepository.deleteById(id);
	}

	public void deleteAllByProjectId(int projectId) {
		this.featureRepository.deleteByProjectId(projectId);
	}

	public void deleteAll() {
		this.featureRepository.deleteAll();
	}
	
}

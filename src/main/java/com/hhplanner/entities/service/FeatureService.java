package com.hhplanner.entities.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.repo.AsignmentRepository;
import com.hhplanner.entities.repo.FeatureRepository;

@Service
@Transactional
public class FeatureService {

	private FeatureRepository featureRepository;
	private AsignmentRepository asignmentRepository;
	private ProjectService projectService;
	private SpringService springService;
	
	public FeatureService(FeatureRepository featureRepository, AsignmentRepository asignmentRepository, ProjectService projectService, SpringService springService) {
		this.featureRepository = featureRepository;
		this.asignmentRepository = asignmentRepository;
		this.projectService = projectService;
		this.springService = springService;
	}

	public Feature getFeatureById(int id) {
		Optional<Feature> feature = this.featureRepository.findById(id);
		if (!feature.isPresent()) {
			throw BusinessExceptionFactory.featureNotFoundException();
		}
		return feature.get();
	}

	public Feature getFeatureByCodeAndProjectId( String code, int projectId) {
		Optional<Feature> feature = this.featureRepository.findByCodeAndProjectId(code, projectId);
		if (!feature.isPresent()) {
			throw BusinessExceptionFactory.featureNotFoundException();
		}
		return feature.get();
	}

	public Iterable<Feature> getFeaturesByProjectId(int projectId) {
		return this.featureRepository.findByProjectId(projectId);
	}

	public Iterable<Feature> getFeaturesToAsignByProjectId(int projectId) {
		return this.featureRepository.findToAsignByProjectId(projectId);
	}

	public Feature save(Feature feature, int projectId) {
		Project project = this.projectService.getProjectById(projectId);
		feature.setProject(project);
		Feature save = this.featureRepository.save(feature);
		return save;
	}

	public Feature saveAndFlush(Feature feature, int projectId) {
		Feature save = save(feature,projectId);
		this.featureRepository.flush();
		return save;
	}
	
	@Transactional(rollbackOn = BusinessException.class)
	public Feature update(int id,Feature feature, int projectId) {
		if (!this.featureRepository.existsById(id)) {
			throw BusinessExceptionFactory.featureNotFoundException();
		}
		Project project = this.projectService.getProjectById(projectId);
		feature.setId(id);
		feature.setProject(project);
		Feature save = this.featureRepository.save(feature);
		Optional<Asignment> asignment = this.asignmentRepository.findByFeature(feature);
		if (asignment.isPresent() && !this.springService.validateEnoughSpringDays(asignment.get().getSpring())) {
			throw BusinessExceptionFactory.userCapacityInsufficientException();
		}
		return save;
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

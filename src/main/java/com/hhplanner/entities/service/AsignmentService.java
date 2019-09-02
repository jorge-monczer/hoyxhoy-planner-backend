package com.hhplanner.entities.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.repo.AsignmentRepository;

@Service
public class AsignmentService {

	private AsignmentRepository asignmentRepository;
	private SpringService springService;
	
	public AsignmentService(AsignmentRepository asignmentRepository, SpringService springService ) {
		this.asignmentRepository = asignmentRepository;
		this.springService = springService;
	}

	public Asignment getAsignmentById(int id) {
		Optional<Asignment> asignment = this.asignmentRepository.findById(id);
		if (!asignment.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return asignment.get();
	}

	public Asignment getAsignmentByFeature( Feature feature) {
		Optional<Asignment> asignment = this.asignmentRepository.findByFeature(feature);
		if (!asignment.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return asignment.get();
	}

	public Asignment getAsignmentByFeatureId( int fid) {
		Optional<Asignment> asignment = this.asignmentRepository.findByFeatureId(fid);
		if (!asignment.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return asignment.get();
	}
	
	public Iterable<Asignment> getAsignmentsBySpringId(int springId) {
		return this.asignmentRepository.findBySpringId(springId);
	}

	public Asignment save(Asignment asignment, int springId) {
		Spring spring = this.springService.getSpringById(springId);
		try {
			asignment.setSpring(spring);
			Asignment save = this.asignmentRepository.save(asignment);
//			this.springRepository.flush();
			return save;
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} catch (Exception e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		}
	}

	public Asignment saveAndFlush(Asignment asignment,int springId) {
		Asignment save = save(asignment,springId);
		this.asignmentRepository.flush();
		return save;
	}
	
	public Asignment update(int id,Asignment asignment, int springId) {
		if (!this.asignmentRepository.existsById(id)) {
			throw new EntityModelNotFoundException();
		}
		Spring spring = this.springService.getSpringById(springId);
		try {
			asignment.setId(id);
			asignment.setSpring(spring);
			return this.asignmentRepository.save(asignment);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} 
	}

	public void delete(int id) {
		this.asignmentRepository.deleteById(id);
	}

	public void deleteAllBySpringId(int springId) {
		this.asignmentRepository.deleteBySpringId(springId);
	}

	public void deleteAll() {
		this.asignmentRepository.deleteAll();
	}
	
}

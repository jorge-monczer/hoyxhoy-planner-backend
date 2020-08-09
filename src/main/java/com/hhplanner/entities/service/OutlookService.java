package com.hhplanner.entities.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.query.Q_OutlookAdvanceSummary;
import com.hhplanner.entities.repo.FeatureRepository;
import com.hhplanner.entities.repo.FeatureRepository.Q_EstimatedProject;
import com.hhplanner.entities.repo.SpendingRepository;
import com.hhplanner.entities.repo.SpendingRepository.Q_SpentSpringSummary;

@Service
@Transactional
public class OutlookService {

	private SpendingRepository spendingRepository;
	private FeatureRepository featureRepository;

	public OutlookService(SpendingRepository spendingRepository, FeatureRepository featureRepository ) {
		this.spendingRepository = spendingRepository;
		this.featureRepository = featureRepository;
	}
	
	public List<Q_OutlookAdvanceSummary> getSpentGroupBySpring(int projectId) {
		List<Q_SpentSpringSummary> query = this.spendingRepository.sumSpentGroupBySpring(projectId);
		Q_EstimatedProject sumEstimatedProject = this.featureRepository.sumEstimatedProject(projectId);
		double sumSpent = 0.0;
		List<Q_OutlookAdvanceSummary> q_oulook_summary = new ArrayList<>();
		String nameTotal = "Total hours estimated for " + sumEstimatedProject.getProjectCode() + "-" + sumEstimatedProject.getProjectName();
		q_oulook_summary.add(new Q_OutlookAdvanceSummary(nameTotal,sumEstimatedProject.getEstimated(),null));
		String name = "Hours consumed in ";
		for (Q_SpentSpringSummary q : query) {
			sumSpent =+ q.getSpent();
			String nameConsumed = name + q.getCode() + "-" + q.getName();
			q_oulook_summary.add(new Q_OutlookAdvanceSummary(nameConsumed,q.getSpent(),Math.round(sumSpent/sumEstimatedProject.getEstimated()*10000d)/10000d));
		}
		
		return q_oulook_summary;
	}
	
}

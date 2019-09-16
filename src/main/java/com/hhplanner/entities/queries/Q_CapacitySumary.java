package com.hhplanner.entities.queries;

public class Q_CapacitySumary {
	private String username;
	private int availableHours;
	private int estimatedHours;

	public Q_CapacitySumary(String username, int availableHours, int estimatedHours) {
		this.username = username;
		this.availableHours = availableHours;
		this.estimatedHours = estimatedHours;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAvailableHours() {
		return availableHours;
	}
	public void setAvailableHours(int availableHours) {
		this.availableHours = availableHours;
	}
	public int getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	
	public String toString() {
		return new StringBuilder().append("<Q_CapacitySumary> {")
				.append("'username':").append("'").append(this.getUsername()).append("'").append(",")
				.append("'availableHours':").append(this.getAvailableHours()).append(",")
				.append("'estimatedHours':").append(this.getEstimatedHours()).append("}").toString();
	}
	
}

package com.hhplanner.entities.query;

import java.util.Objects;

public class Q_OutlookAdvanceSummary {
	private final String name;
	private final Double spent;
	private final Double advance;

	public Q_OutlookAdvanceSummary(String name, Double spent, Double advance) {
		this.name = name;
		this.spent = spent;
		this.advance = advance;
	}
	
	public String getName() {
		return this.name;
	}

	public Double getSpent() {
		return this.spent;
	}

	public Double getAdvance() {
		return this.advance;
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Q_OutlookAdvanceSummary)) return false;
        Q_OutlookAdvanceSummary that = (Q_OutlookAdvanceSummary) o;
        return getName().equals(that.getName());
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(getName(),getSpent(),getAdvance());
    }

}

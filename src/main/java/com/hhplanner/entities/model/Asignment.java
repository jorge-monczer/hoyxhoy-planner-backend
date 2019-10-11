package com.hhplanner.entities.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Asignment implements Serializable {
	
	private static final long serialVersionUID = -2397327912148291872L;

	@Id
	@GeneratedValue
	private int id;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(unique=true, name = "feature_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Feature feature;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "spring_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
	private Spring spring;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "username", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User1 user;

	@Formula(value = "(select c.available_hours * s.spring_days - f.estimated_hours from capacity c, spring s, feature f where f.id = feature_id and s.id = spring_id and c.spring_id = spring_id and c.username = username)")
	private double remaining;

	@Formula(value = "(select c.available_hours from capacity c where c.spring_id = spring_id and c.username = username)")
	private int capacity;
	
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "id.asignment", orphanRemoval=true)
    @OrderBy("id")
    @JsonIgnore
    private Set<Spending> spendings = new HashSet<>();	

    @Transient
    private List<Integer> spendingsInt = new ArrayList<>();	
    
   public Asignment() {
		super();
	}
    
    public Asignment(int id, Feature feature, User1 user) {
		super();
		this.id = id;
		this.feature = feature;
		this.user = user;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public Spring getSpring() {
		return spring;
	}
	public void setSpring(Spring spring) {
		this.spring = spring;
	}
	public User1 getUser() {
		return user;
	}
	public void setUser(User1 user) {
		this.user = user;
	}
	public double getRemaining() {
		return remaining;
	}
	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Set<Spending> getSpendings() {
		return spendings;
	}
	public void setSpendings(Set<Spending> spendings) {
		this.spendings = spendings;
	}
   public void addSpending(int numDay, int spent) {
        this.spendings.add(new Spending(this,numDay,spent));
    }
	public List<Integer> getSpendingsInt() {
		return spendingsInt;
	}
	public void setSpendingsInt(List<Integer> spendingsInt) {
		this.spendingsInt = spendingsInt;
	}
	public void buildSpendingsFromInt() {
		int numDay = 1;
		this.getSpendings().clear();
		for (Integer spent : this.getSpendingsInt()) {
			if (spent != null) {
				this.addSpending(numDay,spent);
			}
			numDay++;
		}
	}
	public void buildIntFromSpendings() {
//		this.setSpendingsInt(this.getSpendings().stream().mapToInt(s -> s.getSpent()).boxed().collect(Collectors.toList()));
		this.getSpendingsInt().clear();
		int idx = 0;
		for (Spending spending : this.getSpendings()) {
			while (idx < spending.getId().getNumDay()-1) {
				this.getSpendingsInt().add(null);					
				idx++;
			}
			this.getSpendingsInt().add(spending.getSpent());
			idx++;
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append("<Asignment> {")
				.append("'id':").append(this.id).append(",")
				.append("'feature':").append("'").append(this.feature.getCode()).append("'").append(",")
				.append("'user':").append("'").append(this.user.getUsername()).append("'").append(",")
				.append("'spendingInt':").append(this.spendingsInt).append("}").toString();
	}
	
}

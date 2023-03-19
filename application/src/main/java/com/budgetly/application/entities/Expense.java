package com.budgetly.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name="expenses")
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="amount")
	private double amount;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "budget_id")
	private Budget budget;

	public Expense() {
	}
	
	public Expense(String name, double amount, Budget budget) {
		super();
		this.name = name;
		this.amount = amount;
		this.budget = budget;
	}
	
	public Expense(Budget budget) {
		super();
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", amount=" + amount + ", budget=" + budget.getId() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	
}

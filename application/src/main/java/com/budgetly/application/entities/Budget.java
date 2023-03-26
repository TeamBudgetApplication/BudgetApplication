package com.budgetly.application.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="budgets")
public class Budget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "budget_id")
	private int id;
	
	@Column(name= "budget_amount")
	private double amount;
	
	@Column(name= "budget_name")
	private String name;
	
	@Column(name= "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	
	// Default constructor
	public Budget() {
		
	}

	public Budget(int id, double amount, String name, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	//one-to-many connection with Expenses table
	@OneToMany(mappedBy = "budget")
	@JsonIgnore
	private List<Expense> expenses = new ArrayList<Expense>();
	
//	public List<Expense> getExpenses() {
//		return expenses;
//	}
//
//	public void setExpenses(List<Expense> expenses) {
//		this.expenses = expenses;
//	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "Budget [id=" + id + ", amount=" + amount + ", name=" + name + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
	
}

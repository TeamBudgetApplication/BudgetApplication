package com.budgetly.application.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="budgets")
public class Budget {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="amount")
	private double amount;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public Budget() {
	}

	public Budget(String name, Date startDate, Date endDate, double amount, Customer customer) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.customer = customer;
	}
	
	public Budget(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", customer=" + customer.getId() + "]";
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	//one-to-many connection with Expenses table
	@JsonIgnore
	@OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Expense> expenses = new ArrayList<Expense>();
	
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
}

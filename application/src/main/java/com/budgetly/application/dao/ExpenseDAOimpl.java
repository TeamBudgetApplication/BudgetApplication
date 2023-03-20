package com.budgetly.application.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Expense;

@Repository
public class ExpenseDAOimpl implements ExpenseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Expense> getExpenses() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Expense> query = session.createQuery("from Expense",
				Expense.class);		
		return query.getResultList();
	}

	//get all expenses of one specific budget
	@Override
	public List<Expense> getExpenses(int budgetId) {
		
		Session session = sessionFactory.getCurrentSession();
		Budget budget = session.get(Budget.class, budgetId);
		Query<Expense> query = session.createQuery("from Expense e where e.budget = :budget",
				Expense.class);
		query.setParameter("budget", budget);
		List<Expense> expenses = query.getResultList();
		return expenses;

	}
	
	public Expense getExpense(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Expense> query = session.createQuery("from Expense e where e.id = :expenseId",
				Expense.class);
		query.setParameter("expenseId", id);
		Expense expense = query.getSingleResult();
		
		return expense;
		
	}

	public void onlySaveExpense(Expense expense) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(expense);
	}
	
	public Expense saveExpense(Expense expense) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(expense);
		return expense;
	}
	
	@Override
	public void deleteExpense(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Expense expense = session.get(Expense.class, id);
		session.remove(expense);
		
	}

}

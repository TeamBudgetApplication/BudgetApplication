package com.budgetly.application.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Customer;


@Repository
public class BudgetDAOimpl implements BudgetDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Budget> getBudgets() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Budget> query = session.createQuery("from Budget",
				Budget.class);		
		return query.getResultList();
	}

	//get all budgets of one specific customer
	@Override
	public List<Budget> getBudgets(int customerId) {
		
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, customerId);
		Query<Budget> query = session.createQuery("from Budget b where b.customer = :customer",
				Budget.class);
		query.setParameter("customer", customer);
		List<Budget> budgets = query.getResultList();
		return budgets;

	}
	
	public Budget getBudget(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Budget> query = session.createQuery("from Budget c where c.id = :budgetId",
				Budget.class);
		query.setParameter("budgetId", id);
		Budget budget = query.getSingleResult();
		
		return budget;
		
	}

	public void saveBudget(Budget budget) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(budget);
	}
	
	@Override
	public void deleteBudget(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Budget budget = session.get(Budget.class, id);
		session.remove(budget);
		
	}

}

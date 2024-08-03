package com.example.tobyspringsix.data;

import com.example.tobyspringsix.order.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OrderRepository {

	private final EntityManagerFactory emf;

	public OrderRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void save(Order order) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			em.persist(order);
			em.flush();

			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive())
				transaction.rollback();
			throw e;
		} finally {
			if (em.isOpen())
				em.close();
		}
	}
}

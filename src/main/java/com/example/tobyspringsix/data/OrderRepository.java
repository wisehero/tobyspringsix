package com.example.tobyspringsix.data;

import com.example.tobyspringsix.order.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class OrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Order order) {
		entityManager.persist(order);
	}
}

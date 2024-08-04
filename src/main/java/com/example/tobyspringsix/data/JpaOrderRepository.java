package com.example.tobyspringsix.data;

import com.example.tobyspringsix.order.Order;
import com.example.tobyspringsix.order.OrderRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository implements OrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Order order) {
		entityManager.persist(order);
	}
}

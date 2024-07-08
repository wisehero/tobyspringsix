package com.example.tobyspringsix;

import java.math.BigDecimal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.tobyspringsix.order.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class DataClient {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			DataConfig.class);
		EntityManagerFactory emf = ac.getBean(EntityManagerFactory.class);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Order order = new Order("100", BigDecimal.TEN);

		em.persist(order);

		System.out.println(order);

		em.getTransaction().commit();
		em.clear();
	}
}

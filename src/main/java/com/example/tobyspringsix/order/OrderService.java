package com.example.tobyspringsix.order;

import java.math.BigDecimal;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 현재 아래의 애플리케이션 서비스는 JPA에 의존하고 있음
 * JPA를 사용하는 Repository 클래스에 의존하고 있음
 * JPA Transaction Manager에 의존하고 있음
 *
 */
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final JpaTransactionManager transactionManager;

	public OrderService(OrderRepository orderRepository, JpaTransactionManager transactionManager) {
		this.orderRepository = orderRepository;
		this.transactionManager = transactionManager;
	}

	public Order createOrder(String no, BigDecimal total) {
		Order order = new Order(no, total);

		return new TransactionTemplate(transactionManager).execute(status -> {
			this.orderRepository.save(order);
			return order;
		});
	}
}

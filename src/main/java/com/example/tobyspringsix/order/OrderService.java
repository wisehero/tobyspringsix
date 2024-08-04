package com.example.tobyspringsix.order;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 현재 아래의 애플리케이션 서비스는 JPA에 의존하고 있음 -> 해결.
 * JPA를 사용하는 Repository 클래스에 의존하고 있음 -> 해결. OrderRepository 도입
 * JPA Transaction Manager에 의존하고 있음 -> PlatformTransactionManager를 도입하여 해결.
 *
 */
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final PlatformTransactionManager transactionManager;

	public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
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

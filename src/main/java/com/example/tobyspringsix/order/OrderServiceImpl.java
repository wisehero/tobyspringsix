package com.example.tobyspringsix.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 현재 아래의 애플리케이션 서비스는 JPA에 의존하고 있음 -> 해결.
 * JPA를 사용하는 Repository 클래스에 의존하고 있음 -> 해결. OrderRepository 도입
 * JPA Transaction Manager에 의존하고 있음 -> PlatformTransactionManager를 도입하여 해결.
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order createOrder(String no, BigDecimal total) {
		Order order = new Order(no, total);

		this.orderRepository.save(order);
		return order;
	}

	@Override
	public List<Order> createOrders(List<OrderRequest> request) {
		return request.stream().map(req -> createOrder(req.no(), req.total())).toList();
	}

}

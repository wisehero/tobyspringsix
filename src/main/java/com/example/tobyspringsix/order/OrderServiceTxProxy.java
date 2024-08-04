package com.example.tobyspringsix.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class OrderServiceTxProxy implements OrderService {

	private OrderService target;
	private PlatformTransactionManager transactionManager;

	public OrderServiceTxProxy(OrderService target, PlatformTransactionManager transactionManager) {
		this.target = target;
		this.transactionManager = transactionManager;
	}

	@Override
	public Order createOrder(String no, BigDecimal total) {
		return target.createOrder(no, total);
	}

	@Override
	public List<Order> createOrders(List<OrderRequest> request) {
		return new TransactionTemplate(transactionManager).execute(status ->
			target.createOrders(request)
		);
	}
}

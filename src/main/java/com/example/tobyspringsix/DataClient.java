package com.example.tobyspringsix;

import java.math.BigDecimal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.tobyspringsix.data.OrderRepository;
import com.example.tobyspringsix.order.Order;

public class DataClient {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			DataConfig.class);
		OrderRepository repository = ac.getBean(OrderRepository.class);

		Order order = new Order("100", BigDecimal.TEN);
		repository.save(order);

		System.out.println(order);

		Order order2 = new Order("200", BigDecimal.ONE);
		repository.save(order2);
	}
}

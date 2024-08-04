package com.example.tobyspringsix;

import java.math.BigDecimal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.tobyspringsix.order.Order;
import com.example.tobyspringsix.order.OrderConfig;
import com.example.tobyspringsix.order.OrderService;

public class OrderClient {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			OrderConfig.class);
		OrderService service = ac.getBean(OrderService.class);

		Order order = service.createOrder("100", BigDecimal.TEN);
		System.out.println(order);
	}
}

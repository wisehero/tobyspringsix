package com.example.tobyspringsix.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.example.tobyspringsix.DataConfig;
import com.example.tobyspringsix.data.JpaOrderRepository;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

	@Bean
	public OrderRepository orderRepository() {
		return new JpaOrderRepository();
	}

	@Bean
	public OrderService orderService(JpaTransactionManager transactionManager) {
		return new OrderService(orderRepository(), transactionManager);
	}
}

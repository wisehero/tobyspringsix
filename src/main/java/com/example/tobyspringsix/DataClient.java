package com.example.tobyspringsix;

import java.math.BigDecimal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.tobyspringsix.data.OrderRepository;
import com.example.tobyspringsix.order.Order;

public class DataClient {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			DataConfig.class);
		OrderRepository repository = ac.getBean(OrderRepository.class);
		JpaTransactionManager transactionManager = ac.getBean(JpaTransactionManager.class);

		try {
			new TransactionTemplate(transactionManager).execute(
				status -> {
					Order order = new Order("100", BigDecimal.TEN);
					repository.save(order);
					System.out.println(order);

					Order order2 = new Order("100", BigDecimal.TEN);
					repository.save(order2);

					return null;
				});
		} catch (DataIntegrityViolationException e) {
			System.out.println("주문번호 중복 복구 작업");
		}

	}
}

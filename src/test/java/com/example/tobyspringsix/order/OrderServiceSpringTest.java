package com.example.tobyspringsix.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

	@Autowired
	OrderService orderService;

	@Autowired
	DataSource dataSource;

	@Test
	void createOrder() {
		var order = orderService.createOrder("0100", BigDecimal.TEN);

		assertThat(order.getId()).isGreaterThan(0);
	}

	@Test
	void createOrders() {
		List<OrderRequest> orderRequests = List.of(
			new OrderRequest("0200", BigDecimal.ONE),
			new OrderRequest("0201", BigDecimal.TWO)
		);

		List<Order> orders = orderService.createOrders(orderRequests);

		assertThat(orders).hasSize(2);
		orders.forEach(order -> {
			assertThat(order.getId()).isGreaterThan(0);
		});
	}

	@Test
	void createDuplicatedOrders() {
		List<OrderRequest> orderRequests = List.of(
			new OrderRequest("0300", BigDecimal.ONE),
			new OrderRequest("0300", BigDecimal.TWO)
		);


		assertThatThrownBy(() -> orderService.createOrders(orderRequests))
			.isInstanceOf(DataIntegrityViolationException.class);

		JdbcClient client = JdbcClient.create(dataSource);
		Long count = client.sql("select count(*) from orders where no = '0300'").query(Long.class).single();
		assertThat(count).isEqualTo(0);
	}
}

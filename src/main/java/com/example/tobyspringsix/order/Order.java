package com.example.tobyspringsix.order;

import java.math.BigDecimal;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Order {

	@Id
	@GeneratedValue
	private Long id;

	private String no; // 주문번호.

	private BigDecimal total;

	public Order(String no, BigDecimal total) {
		this.no = no;
		this.total = total;
	}

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public String getNo() {
		return no;
	}

	public BigDecimal getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "Order{" +
			"id=" + id +
			", no='" + no + '\'' +
			", total=" + total +
			'}';
	}
}

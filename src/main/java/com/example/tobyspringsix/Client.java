package com.example.tobyspringsix;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
	public static void main(String[] args) throws IOException {
		PaymentService paymentService = new SimplePaymentService();
		Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(payment);
	}
}

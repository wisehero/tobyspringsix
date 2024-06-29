package com.example.tobyspringsix;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.tobyspringsix.payment.Payment;
import com.example.tobyspringsix.payment.PaymentService;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
		PaymentService paymentService = beanFactory.getBean(PaymentService.class);

		Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("payment1 = " + payment1);
		System.out.println("-------------------------------------------");

		TimeUnit.SECONDS.sleep(1);

		Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("payment2 = " + payment2);
		System.out.println("------------------------------------------");

		TimeUnit.SECONDS.sleep(2);

		Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println("payment3 = " + payment3);
	}
}

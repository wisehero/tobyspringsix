package com.example.tobyspringsix.payment;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.tobyspringsix.TestObjectFactory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
public class PaymentServiceSpringTest {

	@Autowired
	PaymentService paymentService;
	@Autowired
	ExRateProviderStub stub;
	@Autowired
	private ExRateProvider exRateProvider;

	@Test
	void convertedAmount() throws IOException {
		Payment payment = paymentService.prepare(1L, "USD", TEN);

		assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1000));
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

		// exRateStub을 제어 exRate : 500
		stub.setExRate(valueOf(500));
		Payment payment2 = paymentService.prepare(1L, "USD", TEN);

		assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
		assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
	}
}

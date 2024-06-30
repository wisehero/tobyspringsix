package com.example.tobyspringsix.payment;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

	@Test
	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	void prepare() throws IOException {
		getPayment(valueOf(500), valueOf(5_000));
		getPayment(valueOf(1000), valueOf(10_000));
		getPayment(valueOf(3000), valueOf(30_000));

		// 원화환산금액의 유효시간 계산
		// assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
		// assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
	}

	private static void getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

		Payment payment = paymentService.prepare(1L, "USD", TEN);

		// BigDecimal은 isEqualTo를 사용하지 않는다.

		// 환율 정보를 가져온다.
		assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

		// 원화환산금액 계산
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
	}
}
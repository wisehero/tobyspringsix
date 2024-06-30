package com.example.tobyspringsix.payment;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

	Clock clock;

	@BeforeEach
	void beforEach() {
		this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
	}

	@Test
	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	void prepare() throws IOException {

		testAmount(valueOf(500), valueOf(5_000), clock);
		testAmount(valueOf(1000), valueOf(10_000), clock);
		testAmount(valueOf(3000), valueOf(30_000), clock);

		// 원화환산금액의 유효시간 계산
		// assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
		// assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
	}

	@Test
	void validUntil() throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);

		Payment payment = paymentService.prepare(1L, "USD", TEN);

		// valid until이 prepare() 30분 뒤로 설정됐는가?
		LocalDateTime now = LocalDateTime.now(clock);
		LocalDateTime expectedValidUntil = now.plusMinutes(30);

		Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
	}

	private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

		Payment payment = paymentService.prepare(1L, "USD", TEN);

		// BigDecimal은 isEqualTo를 사용하지 않는다.

		// 환율 정보를 가져온다.
		assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

		// 원화환산금액 계산
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
	}
}
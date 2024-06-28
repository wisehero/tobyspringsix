package com.example.tobyspringsix;

import java.io.IOException;
import java.math.BigDecimal;

public class SimplePaymentService {

	BigDecimal getExRate(String currency) throws IOException {
		if (currency.equals("USD"))
			return BigDecimal.valueOf(1000);

		throw new IllegalArgumentException("지원되지 않는 통화입니다.");
	}
}

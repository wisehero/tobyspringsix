package com.example.tobyspringsix;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // JSON안에 없는 것들은 무시함
public record ExRateData(
	String result,
	Map<String, BigDecimal> rates
) {
}

package com.example.tobyspringsix;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortTest {
	Sort sort;

	@BeforeEach
	void beforeEach() {
		sort = new Sort();
		System.out.println(this);
	}

	@Test
	void sort() {
		List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

		Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));
	}

	@Test
	void sort3Items() {
		List<String> list = sort.sortByLength(Arrays.asList("aa", "b", "c"));

		Assertions.assertThat(list).isEqualTo(List.of("b", "c", "aa"));
	}

	@Test
	void sortAlreadySorted() {
		List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

		Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
	}
}

package com.masterdevskills.cha2.ext1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.contains;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
class NumericStreamsTest {

	@Test
	void generate() {
		List<Integer> fib = NumericStreams.generate(10);

		assertThat(fib, contains(1, 1, 2, 3, 5, 8, 13, 21, 34, 55));
	}
}
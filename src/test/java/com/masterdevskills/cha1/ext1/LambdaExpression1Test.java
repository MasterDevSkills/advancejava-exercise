/*
 * #%L
 * Advanced Java LIVE course-2020
 * %%
 * Copyright (C) 2020 MasterDevSkills.com
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package com.masterdevskills.cha1.ext1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 04 August 2020
 */
class LambdaExpression1Test {

	@Test
	void checkMoreThan5Chars() {
		assertTrue(LambdaExpression1.checkMoreThan5Chars("HelloWorld"));
		assertFalse(LambdaExpression1.checkMoreThan5Chars("hello"));
		assertFalse(LambdaExpression1.checkMoreThan5Chars("helo"));
	}

	@Test
	void isStringEmpty() {
		assertTrue(LambdaExpression1.isStringEmpty("   "));
		assertFalse(LambdaExpression1.isStringEmpty("hello"));
	}

	@Test
	void convertToUpperCase() {
		assertEquals(LambdaExpression1.convertToUpperCase("hello"), "HELLO");
		assertEquals(LambdaExpression1.convertToUpperCase("Abc"), "ABC");
		assertEquals(LambdaExpression1.convertToUpperCase("Abc33"), "ABC33");
	}
}
/*
 *
 *  * #%L
 *  * Advanced Java LIVE course-2020
 *  * %%
 *  * Copyright (C) 2020 MasterDevSkills.com
 *  * %%
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as
 *  * published by the Free Software Foundation, either version 2 of the
 *  * License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public
 *  * License along with this program.  If not, see
 *  * <http://www.gnu.org/licenses/gpl-2.0.html>.
 *  * #L%
 *
 */

package com.masterdevskills.cha1.ext1;


import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExpression1 {

	/**
	 * TODO 1:  Write a lambda expression using Predicate<String> to check if a string has more then 5 characters or not
	 *
	 * @param value given value
	 * @see Predicate<String>
	 */
	public static boolean checkMoreThan5Chars(String value) {

		Predicate<String> predicate = (text) -> text.length() > 5;

		return predicate.test(value);
	}

	/* TODO 2: Write a lambda expression using Predicate<String> to check if string is empty or not
	 * @param value given value
	 * @see Predicate<String>
	 */
	public static boolean isStringEmpty(String value) {
		Predicate<String> isEmpty =  String::isBlank;

		return isEmpty.test(value);
	}

	/**
	 * TODO 3:  Write lambda expression using Function<String, String> to converter a text to uppercase
	 *
	 * @param value given value
	 * @see Predicate<String>
	 */
	public static String convertToUpperCase(String value) {
		Function<String, String> converter = String::toUpperCase;

		return converter.apply(value);
	}
}

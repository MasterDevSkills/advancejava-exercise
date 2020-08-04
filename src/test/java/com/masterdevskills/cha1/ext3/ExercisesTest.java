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

package com.masterdevskills.cha1.ext3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 04 August 2020
 */
class ExercisesTest {

	@Test
	void doubling() {
		var ints = Arrays.asList(1, 2, 3, 4, 5, 6);

		//method under test
		var doubled = Exercises.doubling(ints);

		assertThat(doubled, hasSize(ints.size()));
		assertThat(doubled, is(equalTo(List.of(2, 4, 6, 8, 10, 12))));
	}

	@Test
	void addSuffix() {

		var items = Arrays.asList("a", "b", "c");

		//method under test
		var withSuffix = Exercises.addSuffix(items, ":)");

		assertThat(withSuffix, hasSize(items.size()));
		assertThat(withSuffix, is(equalTo(List.of("a:)", "b:)", "c:)"))));

	}

	@Test
	void sortItemByFirstNameOrderAscending() {
		var people = getPeople();

		//method under test
		var sorted = Exercises.sortItemByFirstNameOrderAscending(copy(people));

		sortByFirsName(people);
		assertThat(sorted, equalTo(people));
	}

	@Test
	void sortByLastNameOrderDescending() {
		var people = getPeople();

		//method under test
		var sorted = Exercises.sortByLastNameOrderDescending(copy(people));

		sortByLastNameDescendingOrder(people);
		assertThat(sorted, equalTo(people));
	}

	@Test
	void sortByFirstNameAndThenLastNameAndThenAge() {
		var people = getPeople();

		//method under test
		var sorted = Exercises.sortByFirstNameAndThenLastNameAndThenAge(copy(people));
		sortByFirstNameAndThenLastNameAndThenAge(people);
		assertThat(sorted, equalTo(people));
	}

	private void sortByFirstNameAndThenLastNameAndThenAge(List<Person> people) {
		people.sort(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName).thenComparing(Person::getAge));
	}

	private void sortByLastNameDescendingOrder(List<Person> people) {
		people.sort(Comparator.comparing(Person::getLastName).reversed());
	}

	private void sortByFirsName(List<Person> people) {
		people.sort(Comparator.comparing(Person::getFirstName));
	}

	private List<Person> copy(List<Person> people) {

		return new ArrayList<>(people);
	}

	private List<Person> getPeople() {

		return Arrays.asList(new Person("Khatib", "Fahad", 20),
						new Person("Tahniat", "Ashraf", 21),
						new Person("Tahniat", "Ahmed", 45),
						new Person("Ashfaque", "Ahmed", 35),
						new Person("Abdullah", "Khan", 18),
						new Person("Abdullah", "Alam", 19),
						new Person("Ashraful", "Alam", 19),
						new Person("Ashraful", "Alam", 34),
						new Person("Sajid", "Samsad", 29),
						new Person("Ruhshan", "Ahmed", 40));
	}
}
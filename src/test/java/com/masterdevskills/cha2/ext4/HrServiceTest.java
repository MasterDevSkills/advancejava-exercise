package com.masterdevskills.cha2.ext4;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 08 August 2020
 */
class HrServiceTest {

	@Test
	void findAllDepartmentsExceedingSalaries() {
		var employees = Stream.of(getEmployee(60_000, "Hr"),
						getEmployee(1_000, "Stuff"),
						getEmployee(90_000, "IT"))
						.flatMap(Collection::stream)
						.collect(Collectors.toList());

//Method under test
		var map = new HrService().findAllDepartmentNameExceedingSalaries(employees, 1_000_000);
		assertThat(map.size(), is(2));
		assertThat(map, IsMapContaining.hasEntry("IT", 1350000L));
		assertThat(map, IsMapContaining.hasEntry("Hr", 1050000L));
		assertThat(map, not(IsMapContaining.hasKey("Stuff")));
	}

	private List<RegularEmployee> getEmployee(int salariesFrom, String deptName) {
		return IntStream.iterate(salariesFrom, (seed) -> seed + 10_000)
						.mapToObj(value -> new RegularEmployee(new MyDept(deptName), value))
						.limit(10)
						.collect(Collectors.toList());
	}

	static class MyDept implements Department, Comparable<MyDept> {
		private final String name;

		public MyDept(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return getName();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof MyDept)) return false;
			MyDept myDept = (MyDept) o;
			return Objects.equals(getName(), myDept.getName());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getName());
		}

		@Override
		public int compareTo(MyDept o) {
			return name.compareTo(o.name);
		}
	}

	static class RegularEmployee implements Employee {
		private final Department department;
		private final long salaries;

		public RegularEmployee(Department department, long salaries) {
			this.department = department;
			this.salaries = salaries;
		}

		@Override
		public Department getDepartment() {
			return department;
		}

		@Override
		public long getSalary() {
			return salaries;
		}

		@Override
		public String toString() {
			return department.getName() + ": " + salaries;
		}
	}
}
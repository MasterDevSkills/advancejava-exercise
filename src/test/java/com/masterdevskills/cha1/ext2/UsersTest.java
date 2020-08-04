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

package com.masterdevskills.cha1.ext2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 03 August 2020
 */
class UsersTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void activatedAll() {
		var users = getUsers();
		//method under test
		Users.activatedAll(users, Status.ACTIVATED);

		assertThat(users, everyItem(hasProperty("status", is(equalTo(Status.ACTIVATED)))));
		users.forEach(user -> assertEquals(user.getStatus(), Status.ACTIVATED));
	}

	@Test
	void makeStringOfAllUsernames() {
		var users = getUsers();

		//Method under test
		final String allUsernames = Users.makeStringOfAllUsernames(users);

		assertThat(allUsernames, equalTo("user1,user2,user3"));
	}

	private List<User> getUsers() {
		return List.of(new User("user1"), new User("user2"), new User("user3"));
	}
}
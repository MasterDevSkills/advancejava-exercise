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

import java.util.List;
import java.util.StringJoiner;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 03 August 2020
 */
public class Users {

	/**
	 * TODO 1: activate all users - change the status of all the users
	 * <p>
	 * Example-
	 * given a list of users - User ("user1"), Users("User2")
	 * by default status of users is Status.CREATED
	 *
	 * @param users  list of users
	 * @param status a new status
	 * @see User#setStatus(Status)
	 */
	public static void activatedAll(List<User> users, Status status) {
		throw new RuntimeException("NotImplemented");
	}

	/**
	 * TODO 2: Create a string representation of all users
	 * use toString method of users to get String representation of user
	 *
	 * @param users list of string
	 * @see User#toString()
	 * @see StringJoiner#add(CharSequence)
	 * @see StringJoiner#toString()
	 */

	public static String makeStringOfAllUsernames(List<User> users) {

		throw new RuntimeException("NotImplemented");
	}
}

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

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 04 August 2020
 */
public class LambdaExpression2 {

	/**
	 * TODO Create a functional interface called Executable
	 * Add a method called execute()
	 * it doesn't take anything and returns void
	 * use this functional interface as argument of the following method and log
	 * the time it takes to execute the method
	 */
	public void executionTime(Executable executable) {
		//TODO add your code here;
		long l = System.nanoTime();

		executable.execute();

		System.out.println("time: " + (System.nanoTime() - l));
	}

	/* TODO: use the above of method here
	 */
	public void run() {
		executionTime(() -> {
			for (int i = 0; i < 100; i++) {
				//do ranmdom work]
			};
		});
	}

	interface Executable {
		void execute();
	}
}

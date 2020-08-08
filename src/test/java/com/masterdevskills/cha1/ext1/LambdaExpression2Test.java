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
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 04 August 2020
 */
class LambdaExpression2Test {

	@Test
	void executionTime() throws IOException {
		var resources = LambdaExpression2.class.getClassLoader().getResources("");
		var urlIterator = resources.asIterator();

		var executable = Stream.generate(() -> null)
						.takeWhile(x -> urlIterator.hasNext())
						.map(n -> urlIterator.next())
						.filter(n -> n.getPath().contains("main"))
						.map(url -> new File(url.getPath()))
						.flatMap(this::walkThrough)
						.filter(path -> path.endsWith("Executable.class"))
						.findAny();

		assertTrue(executable.isPresent(), "Functional Interface Executable don't exist in classpath");

		var spy = Mockito.spy(new LambdaExpression2());
		spy.run();
		Mockito.verify(spy, Mockito.times(1)).executionTime();
	}

	private Stream<? extends Path> walkThrough(File file) {
		try {
			return Files.walk(Paths.get(file.getPath()));
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}
}
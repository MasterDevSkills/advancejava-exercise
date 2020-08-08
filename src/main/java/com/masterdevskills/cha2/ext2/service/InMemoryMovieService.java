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

package com.masterdevskills.cha2.ext2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterdevskills.cha2.ext2.model.Movie;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
public class InMemoryMovieService {
	private final static InMemoryMovieService instance = new InMemoryMovieService();

	private final ObjectMapper objectMapper = new ObjectMapper();

	private InMemoryMovieService() {
	}

	public static InMemoryMovieService getInstance() {
		return instance;
	}

	public List<Movie> findAllMovies() {
		try {
			var src = new File(
							getClass().getClassLoader().getResource("movies.json").getFile()
			);
			return objectMapper.readValue(src, new TypeReference<>() {
			});
		} catch (IOException ioException) {
			throw new RuntimeException("Unable to parse movie db", ioException);
		}
	}

}

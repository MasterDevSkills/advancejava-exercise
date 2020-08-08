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

import com.masterdevskills.cha2.ext2.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
class RealMovieServiceTest {
	private RealMovieService realMovieService;

	@BeforeEach
	public void setup() {
		realMovieService = new RealMovieService();
	}

	@Test
	void findAllMoviesInYear() {
		assertThat(realMovieService.findAllMoviesInYear(2019), hasSize(3));
		assertThat(realMovieService.findAllMoviesInYear(2020), hasSize(0));
	}

	@Test
	void findAllMovieRated() {
		assertThat(realMovieService.findAllMovieRated("R"), hasSize(93));
		assertThat(realMovieService.findAllMovieRated("PG-13"), hasSize(33));
	}

	@Test
	void findMoviesWithIMdbRating() {
		assertThat(realMovieService.findMoviesWithImdbRatingEqualAndGreaterThan(7), hasSize(255));
		assertThat(realMovieService.findMoviesWithImdbRatingEqualAndGreaterThan(8), hasSize(132));
		assertThat(realMovieService.findMoviesWithImdbRatingEqualAndGreaterThan(8.5), hasSize(33));
		assertThat(realMovieService.findMoviesWithImdbRatingEqualAndGreaterThan(9), hasSize(6));
	}

	@Test
	void findMoviesOfDirector() {
		assertThat(realMovieService.findMoviesOfDirector("Barry Jenkins"), hasSize(3));
	}

	@Test
	void listMovieTitleRated() {
		assertThat(realMovieService.listMovieTitleRated("PG-13"), hasSize(33));
	}

	@Test
	void listUniqueMovieTitleRated() {
		var movieTitles = realMovieService.listUniqueMovieTitleRated("PG-13");
		assertThat(movieTitles, hasSize(11));
	}

	@Test
	void sortMovieByTitle() {
		var allMovies = InMemoryMovieService.getInstance().findAllMovies();
		var moviesToTest = realMovieService.sortMovieByTitle();
		var sortedMovies = allMovies.stream().sorted(comparing(Movie::getTitle)).collect(Collectors.toList());

		assertThat(moviesToTest, equalTo(sortedMovies));
	}

	@Test
	void sortByImdbRatingAndThenTitle() {
		var allMovies = InMemoryMovieService.getInstance().findAllMovies();

		allMovies.sort(comparing(Movie::getImdbRating)
						.thenComparing(Movie::getTitle));

		List<Movie> moviesToTest = realMovieService.sortByImdbRatingAndThenTitle();
		assertThat(moviesToTest, equalTo(allMovies));
	}

	@Test
	void findAnyMovieTitleWithImdbRatingEqualOrGreater() {
		Optional<String> movie = realMovieService.findAnyMovieTitleWithImdbRatingEqualOrGreater(8);
		assertTrue(movie.isPresent());
		assertThat(movie.get(), is("The King's Speech"));

		assertFalse(realMovieService.findAnyMovieTitleWithImdbRatingEqualOrGreater(10).isPresent());

	}

	@Test
	void findFirstMovieTitleWithImdbRatingEqualOrGreater() {
		var movie = realMovieService.findFirstMovieTitleWithImdbRatingEqualOrGreater(7);

		assertTrue(movie.isPresent());
		assertThat(movie.get(), is("Going My Way"));

		assertFalse(realMovieService.findFirstMovieTitleWithImdbRatingEqualOrGreater(10).isPresent());
	}

	@Test
	void findTopRatedMovie() {
		assertTrue(realMovieService.findTopRatedMovie().isPresent());
		assertEquals(realMovieService.findTopRatedMovie().get().getImdbRating(), 9.2);
	}

	@Test
	void findMinRatedMovie() {
		assertTrue(realMovieService.findMinRatedMovie().isPresent());
		assertEquals(realMovieService.findMinRatedMovie().get().getImdbRating(), 5.7);
	}

	@Test
	void getMoviesByYear() {
		//method under test
		Map<String, String> moviesByYear = realMovieService.getMoviesByYear();

		assertThat(moviesByYear, is(getMoviesByYearImperatively()));
		assertThat(moviesByYear.size(), is(89));
		assertThat(moviesByYear, hasEntry("1976", "Rocky, Rocky, Rocky"));
		assertThat(moviesByYear, not(hasEntry("2020", "Extraction")));
		assertThat(moviesByYear, hasKey("2013"));
		assertThat(moviesByYear, hasValue("Wings, Wings, Wings, Sunrise, Sunrise, Sunrise"));
	}

	@Test
	void findNumberOfDistinctMoviesOfEachDirector() {
		var numberOfDistinctMoviesOfEachDirector = realMovieService.findNumberOfDistinctMoviesOfEachDirector();

		assertThat(numberOfDistinctMoviesOfEachDirector, is(findNumberOfDistinctMoviesOfEachDirectorImperatively()));

	}


	private Map<String, String> getMoviesByYearImperatively() {
		var allMovies = InMemoryMovieService.getInstance().findAllMovies();
		Map<String, String> map = new HashMap<>();
		for (Movie allMovie : allMovies) {
			String year = allMovie.getYear();
			if (map.containsKey(year)) {
				map.put(year, map.get(year) + ", " + allMovie.getTitle());
			} else {
				map.put(year, allMovie.getTitle());
			}
		}
		return map;
	}

	private Map<String, Long> findNumberOfDistinctMoviesOfEachDirectorImperatively() {
		var movies = InMemoryMovieService.getInstance().findAllMovies().stream().distinct().collect(Collectors.toList());
		Map<String, Long> map = new HashMap<>();
		for (Movie allMovie : movies) {
			String director = allMovie.getDirector();
			if (map.containsKey(director)) {
				map.put(director, map.get(director) + 1);
			} else {
				map.put(director, 1L);
			}
		}
		return map;
	}
}
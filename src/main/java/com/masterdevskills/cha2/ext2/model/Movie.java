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

package com.masterdevskills.cha2.ext2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
@Data
@EqualsAndHashCode(of = {"title", "director"})
public class Movie {
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("Rated")
	private String rated;
	@JsonProperty("Released")
	private String released;
	@JsonProperty("Runtime")
	private String runtime;
	@JsonProperty("Genre")
	private String genre;
	@JsonProperty("Director")
	private String director;
	@JsonProperty("Writer")
	private String writer;
	@JsonProperty("Actors")
	private String actors;
	@JsonProperty("Plot")
	private String plot;
	@JsonProperty("Language")
	private String language;
	@JsonProperty("Country")
	private String country;
	@JsonProperty("Awards")
	private String awards;
	@JsonProperty("Poster")
	private String poster;
	@JsonProperty("Ratings")
	private List<Rating> ratings = new ArrayList<>();
	@JsonProperty("Metascore")
	private Integer metascore;
	@JsonProperty("imdbRating")
	private Double imdbRating;
	@JsonProperty("imdbVotes")
	private String imdbVotes;
	@JsonProperty("imdbID")
	private String imdbID;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("DVD")
	private String dVD;
	@JsonProperty("BoxOffice")
	private String boxOffice;
	@JsonProperty("Production")
	private String production;
	@JsonProperty("Website")
	private String website;
	@JsonProperty("Response")
	private String response;
}

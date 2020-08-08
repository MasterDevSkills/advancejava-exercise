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

package com.masterdevskills.cha2.ext2.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
public class HttpMovieService {
	private final HttpClient httpClient;
	private final String[] movies = "tt6751668, tt6751668, tt6751668, tt6966692, tt6966692, tt6966692, tt5580390, tt5580390, tt5580390, tt4975722, tt4975722, tt4975722, tt1895587, tt1895587, tt1895587, tt2562232, tt2562232, tt2562232, tt2024544, tt2024544, tt2024544, tt1024648, tt1024648, tt1024648, tt1655442, tt1655442, tt1655442, tt1504320, tt1504320, tt1504320, tt1010048, tt1010048, tt1010048, tt0887912, tt0887912, tt0887912, tt0477348, tt0477348, tt0477348, tt0407887, tt0407887, tt0407887, tt0405159, tt0405159, tt0405159, tt0375679, tt0375679, tt0375679, tt0167260, tt0167260, tt0167260, tt0299658, tt0299658, tt0299658, tt0268978, tt0268978, tt0268978, tt0172495, tt0172495, tt0172495, tt0169547, tt0169547, tt0169547, tt0138097, tt0138097, tt0138097, tt0120338, tt0120338, tt0120338, tt0116209, tt0116209, tt0116209, tt0112573, tt0112573, tt0112573, tt0109830, tt0109830, tt0109830, tt0108052, tt0108052, tt0108052, tt0105695, tt0105695, tt0105695, tt0102926, tt0102926, tt0102926, tt0099348, tt0099348, tt0099348, tt0097239, tt0097239, tt0097239, tt0095953, tt0095953, tt0095953, tt0093389, tt0093389, tt0093389, tt0091763, tt0091763, tt0091763, tt0089755, tt0089755, tt0089755, tt0086879, tt0086879, tt0086879, tt0086425, tt0086425, tt0086425, tt0083987, tt0083987, tt0083987, tt0082158, tt0082158, tt0082158, tt0081283, tt0081283, tt0081283, tt0079417, tt0079417, tt0079417, tt0077416, tt0077416, tt0077416, tt0075686, tt0075686, tt0075686, tt0075148, tt0075148, tt0075148, tt0073486, tt0073486, tt0073486, tt0071562, tt0071562, tt0071562, tt0070735, tt0070735, tt0070735, tt0068646, tt0068646, tt0068646, tt0067116, tt0067116, tt0067116, tt0066206, tt0066206, tt0066206, tt0064665, tt0064665, tt0064665, tt0063385, tt0063385, tt0063385, tt0061811, tt0061811, tt0061811, tt0060665, tt0060665, tt0060665, tt0059742, tt0059742, tt0059742, tt0058385, tt0058385, tt0058385, tt0057590, tt0057590, tt0057590, tt0056172, tt0056172, tt0056172, tt0055614, tt0055614, tt0055614, tt0053604, tt0053604, tt0053604, tt0052618, tt0052618, tt0052618, tt0051658, tt0051658, tt0051658, tt0050212, tt0050212, tt0050212, tt0048960, tt0048960, tt0048960, tt0048356, tt0048356, tt0048356, tt0047296, tt0047296, tt0047296, tt0045793, tt0045793, tt0045793, tt0044672, tt0044672, tt0044672, tt0043278, tt0043278, tt0043278, tt0042192, tt0042192, tt0042192, tt0041113, tt0041113, tt0041113, tt0040416, tt0040416, tt0040416, tt0039416, tt0039416, tt0039416, tt0036868, tt0036868, tt0036868, tt0037884, tt0037884, tt0037884, tt0036872, tt0036872, tt0036872, tt0035093, tt0035093, tt0035093, tt0034583, tt0034583, tt0034583, tt0033729, tt0033729, tt0033729, tt0032976, tt0032976, tt0032976, tt0031381, tt0031381, tt0031381, tt0030993, tt0030993, tt0030993, tt0029146, tt0029146, tt0029146, tt0027698, tt0027698, tt0027698, tt0026752, tt0026752, tt0026752, tt0025316, tt0025316, tt0025316, tt0023876, tt0023876, tt0023876, tt0022958, tt0022958, tt0022958, tt0021746, tt0021746, tt0021746, tt0020629, tt0020629, tt0020629, tt0019729, tt0019729, tt0019729, tt0018578, tt0018578, tt0018578, tt0018455, tt0018455, tt0018455".split(",");

	private HttpMovieService() {
		httpClient = HttpClient.newBuilder().build();
	}

	public static HttpMovieService getHttpClient() {
		return new HttpMovieService();
	}

	public void downloadMovieDB() {
		var start = System.nanoTime();
		var apiEndPoint = "http://www.omdbapi.com/?i=%s&apikey=e056203e";
		var futures = List.of(movies)
						.parallelStream()
						.map(id -> HttpRequest.newBuilder()
										.uri(URI.create(String.format(apiEndPoint, id.trim())))
										.build()).map(request -> httpClient.sendAsync(request, BodyHandlers.ofString()))
						.collect(Collectors.toList());

		var result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
						.thenApply(f -> futures.stream().map(CompletableFuture::join)
										.map(HttpResponse::body)
										.collect(Collectors.toList()));
		try {
			System.out.println(result.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("total time: " + (System.nanoTime() - start) / 100_000);
	}
}

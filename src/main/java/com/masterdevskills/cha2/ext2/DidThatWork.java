package com.masterdevskills.cha2.ext2;


import com.masterdevskills.cha2.ext2.service.InMemoryMovieService;

public class DidThatWork {
	public static void main(String[] args) {
		var allMovies = InMemoryMovieService.getInstance().findAllMovies();
		System.out.println("Total Movies: " + allMovies.size());
	}
}

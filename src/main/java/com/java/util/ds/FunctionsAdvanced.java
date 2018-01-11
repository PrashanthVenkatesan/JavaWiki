package com.java.util.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionsAdvanced {

	public static void main(String[] args) {
		List<Movie> moviesList = cmdb();

		// List of Functions
		BiFunction<String, List<Movie>, List<Movie>> filterByMovieName = 
		    (movieName, movies) -> movies.stream()
				.filter(movie -> movie.getName()
				.equals(movieName))
				.collect(Collectors.toList());
		
		BiFunction<String, List<Movie>, List<Movie>> filterByGenre = 
			(genre, movies) -> movies.stream()
				.filter(movie -> movie.getGenre()
				.contains(genre))
				.collect(Collectors.toList());
				
		Function<List<Movie>, List<Movie>> sortByReleaseYear = 
			movies -> movies.stream()
				.sorted((x, y) -> y.getReleaseYear().compareTo(x.getReleaseYear()))
				.collect(Collectors.toList());
		
		Function<List<Movie>, Optional<Movie>> firstMovieinList = 
			movies -> movies.stream()
				.findFirst();
			
		Function<List<Movie>, Optional<Movie>> recentReleaseMovie = 
				firstMovieinList.compose(sortByReleaseYear);
		
		BiFunction<String, List<Movie>, List<Movie>> sortedMovieByGenre = 
				filterByGenre.andThen(sortByReleaseYear);
		
		BiFunction<String, List<Movie>, Optional<Movie>> newestMovieByGenre = 
				filterByGenre.andThen(sortByReleaseYear).andThen(firstMovieinList);
		
		
			
		// Query : Find a Movie "The Godfather"
		// Ans: "The Godfather", "Marlon Brando", "Crime, Drama", 1972
		System.out.println(filterByMovieName.apply("The Godfather", moviesList));

		// Query : Find a first recent released movie
		// Ans: "The Dark Knight"
		System.out.println(recentReleaseMovie.apply(moviesList));

		// Query : Find a list of recent movies with given genre "Drama"
		// Ans: order :- 3-5-1-6-4-2
		System.out.println(sortedMovieByGenre.apply("Drama", moviesList));

		// Query : Find a first recent movie with given genre "Crime"
		// Ans: "The Dark Knight"
		System.out.println(newestMovieByGenre.apply("Drama", moviesList));
	}

	private static List<Movie> cmdb() {
		List<Movie> m = new ArrayList<>();
		m.add(new Movie("The Shawshank Redemption", "Tim Robbins", "Crime, Drama", 1994));
		m.add(new Movie("The Godfather", "Marlon Brando", "Crime, Drama", 1972));
		m.add(new Movie("The Dark Knight", "Christian Bale", "Action, Crime, Drama", 2008));
		m.add(new Movie("The Godfather: Part II", "Al Pacino", "Crime, Drama", 1974));
		m.add(new Movie("The Lord of the Rings: The Return of the King", "Elijah Wood", "Adventure, Drama, Fantasy",
				2003));
		m.add(new Movie("Schindler's List ", "Liam Neeson", "Biography, Drama, History", 1993));
		return m;
	}
}

class Movie {
	private String name;
	private String actorName;
	private String genre;
	private Integer releaseYear;

	public Movie(String name, String actorName, String genre, Integer releaseYear) {
		super();
		this.name = name;
		this.actorName = actorName;
		this.genre = genre;
		this.releaseYear = releaseYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	@Override
	public String toString() {
		
		return getName()+" "+getActorName()+" "+getReleaseYear()+" "+getGenre();
	}
}
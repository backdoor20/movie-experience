package io.backdoor20.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.backdoor20.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoService {

	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId) {
		return new Movie("PK", "Pk Movie Name");
	}
}

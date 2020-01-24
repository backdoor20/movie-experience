package io.backdoor20.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.backdoor20.models.CatalogItem;
import io.backdoor20.models.Movie;
import io.backdoor20.models.Rating;
import io.backdoor20.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		/*
		 * List<Rating> ratings=Arrays.asList(new Rating("1234", 4), new Rating("5678",
		 * 3));
		 */
		 UserRating ratings=restTemplate.getForObject("http://localhost:8083/ratingdata/users/"+userId, UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> {
			Movie movie=restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getMovieName(), "Description for Movie", rating.getRating());
		}).collect(Collectors.toList());
		//return Collections.singletonList(new CatalogItem("Pk", "Movie by amir khan", 4));
	}
}


/*Movie movie=webClientBuilder.build()
.get()
.uri("http://localhost:8082/movies/"+rating.getMovieId())
.retrieve()
.bodyToMono(Movie.class)
.block();*/

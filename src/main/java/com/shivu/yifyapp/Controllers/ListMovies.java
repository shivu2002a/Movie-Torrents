package com.shivu.yifyapp.Controllers;

import java.net.http.HttpTimeoutException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.shivu.yifyapp.Models.MovieDetails.SearchMovie;
import com.shivu.yifyapp.Models.Search.Search;

import io.netty.handler.timeout.ReadTimeoutException;
import reactor.core.publisher.Mono;

// @RestController
@Controller
public class ListMovies {

    @Autowired
    private WebClient webClient;

    @RequestMapping("/movies")
    public String list_movies(@RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "query_term", required = false) String query,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            Model model) {
        if(query != null)
        query= query.trim();
        String uri = "list_movies.json?";
        boolean and = false;
        if (query != null) {
            and = true;
            uri += "query_term=" + query;
        }
        if (genre != null) {
            if(and) uri += '&';
            and = true;
            uri += "genre=" + genre;
        }
        if (page != null) {
            if(and) uri += '&';
            and = true;
            uri += "page=" + page;
        }
        if (limit != null) {
            if(and) uri += '&';
            and = true;
            uri += "limit=" + limit;
        }
        if (sortBy != null) {
            if(and) uri += '&';
            uri += "sortBy=" + sortBy;
        }
        System.out.println(Home.BASE +  uri + Home.END);
        Mono<Search> se = webClient.get()
                .uri(uri + Home.END)
                .retrieve()
                .bodyToMono(Search.class)
                .timeout(Duration.ofSeconds(10));
        Search result = se.block();
        if(result.data.movies != null) {
            Home.imageSetup(result);
            Home.magnetTorrent(result);
            model.addAttribute("movie", result);
            model.addAttribute("query_term", query);
            return "browse";
        }
        return "movie-not-found";

    }

    @RequestMapping("/movies/{id}")
    public String movieDetails(@PathVariable("id") Integer id, Model model){
        //Get movie details
        String uri = "movie_details.json?movie_id=" + id;
        Mono<SearchMovie> mono = webClient
                                    .get()
                                    .uri(uri + Home.END)
                                    .retrieve()
                                    .bodyToMono(SearchMovie.class)
                                    .timeout(Duration.ofSeconds(10));
        SearchMovie movieDetails = mono.block();
        Home.magnetTorrent(movieDetails);
        Home.imageSetup(movieDetails);
        model.addAttribute("movie", movieDetails);

        String imdbCode = movieDetails.data.movie.imdb_code;

        //Get top crew from imdb-code Use rapid api
        RapidApis.getCrew(model, imdbCode);
        
        //Cast
        RapidApis.getCast(model, imdbCode);
        
        //Get reviews from imdb-code Use rapid api
        RapidApis.getReviews(model, imdbCode);
        
        //Get images from imdb-code Use rapid api
        RapidApis.getImages(model, imdbCode);
        

        //suggestions
        uri = "movie_suggestions.json?movie_id=" + id;
        Mono<Search> suggesMono = webClient
                                    .get()
                                    .uri(uri + Home.END)
                                    .retrieve()
                                    .bodyToMono(Search.class)
                                    .timeout(Duration.ofSeconds(5))
                                    .onErrorMap(ReadTimeoutException.class, ex -> new HttpTimeoutException("ReadTimeout"));
        Search suggested = suggesMono.block();
        if(suggested == null) model.addAttribute("Request Timeout", "req");
        Home.magnetTorrent(suggested);
        Home.imageSetup(suggested);
        System.out.println(suggested);
        model.addAttribute("suggestions", suggested);
        return "movie-details";
    }

}

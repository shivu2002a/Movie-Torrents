package com.shivu.yifyapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.shivu.yifyapp.Models.Search;

import reactor.core.publisher.Mono;

@RestController
public class ListMovies {
    
    public final String END = "&_x_tr_sl=auto&_x_tr_tl=en&_x_tr_hl=en&_x_tr_pto=wapp";
    
    @Autowired
    private WebClient webClient;
    
    @RequestMapping("/movies")
    public Search list_movies(@RequestParam(value = "limit", required = false) Integer limit,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "query_term", required = false) String query,
                              @RequestParam(value = "genre", required = false) String genre,
                              @RequestParam(value = "sortBy", required = false) String sortBy ){
        
        String uri = "list_movies.json?";

        if(query != null){
            uri += "query_term=" + query;
        }
        if(genre != null) {
            uri += "genre=" + genre; 
        }
        if(page != null) {
            uri += "page=" + page; 
        }
        if(limit != null) {
            uri += "limit=" + limit; 
        }
        if(sortBy != null) {
            uri += "sortBy=" + sortBy; 
        }
        Mono<Search> se = webClient.get()
                                     .uri(uri + END)
                                     .retrieve()
                                     .bodyToMono(Search.class);
        Search result = se.block();
        Home.magnetTorrent(result);
        return result;
    }
}

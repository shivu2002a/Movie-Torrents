package com.shivu.yifyapp.Controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.shivu.yifyapp.Models.MovieDetails.ExtendedMovieDetails;
import com.shivu.yifyapp.Models.MovieDetails.SearchMovie;
import com.shivu.yifyapp.Models.Search.Search;

import lombok.Getter;
import lombok.Setter;
import reactor.netty.http.client.HttpClient;

// @RestController
@Controller
@CrossOrigin
@Getter
@Setter
public class Home {

    public static final String BASE = "https://yts-mx.translate.goog/api/v2/";

    public static final String END = "&_x_tr_sl=auto&_x_tr_tl=en&_x_tr_hl=en&_x_tr_pto=wapp";

    public static final String IMG = "https://img-yts-mx.translate.goog/";
    public static final String IMGEND = "?_x_tr_sl=auto&_x_tr_tl=en&_x_tr_hl=en&_x_tr_pto=wapp";


    private WebClient webClient;

    @Bean
    public WebClient buildClient(WebClient.Builder wBuilder) {

        this.webClient = wBuilder
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .baseUrl(BASE)
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient.create().proxyWithSystemProperties()))
                .build();
        return webClient;

    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    public static void magnetTorrent(Search se) {
        se.data.movies.stream().forEach(md -> {
            md.getTorrents().stream().forEach(to -> {
                to.magnetTorrent = "magnet:?xt=urn:btih:" + to.hash;
            });
        });
    }

    public static void magnetTorrent(SearchMovie movieDetails) {
        movieDetails.data.movie.getTorrents().stream().forEach(to -> {
            to.magnetTorrent = "magnet:?xt=urn:btih:" + to.hash;
        });
    }

    public static void imageSetup(Search se) {
        se.data.movies.stream().forEach(mov -> {
            String org = mov.getMedium_cover_image();
            String cover = org.substring(org.indexOf('x') + 2);
            cover = IMG + cover + IMGEND;
            mov.setMedium_cover_image(cover);
            System.out.println(cover);
        });
    }

    public static void imageSetup(SearchMovie movieDetails) {
        ExtendedMovieDetails mov = movieDetails.data.movie;
        String org = mov.getLarge_cover_image();
        String cover = org.substring(org.indexOf('x') + 2);
        cover = IMG + cover + IMGEND;
        mov.setLarge_cover_image(cover);
        System.out.println(cover);

        org = mov.getBackground_image();
        cover = org.substring(org.indexOf('x') + 2);
        cover = IMG + cover + IMGEND;
        mov.setBackground_image(cover);
        System.out.println(cover);
    }
}

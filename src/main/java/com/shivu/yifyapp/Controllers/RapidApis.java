package com.shivu.yifyapp.Controllers;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;

public class RapidApis {

    private static WebClient wb;
    static {
        wb = WebClient.builder()
                .defaultHeaders(headers -> {
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.set("X-RapidAPI-Key",
                            "8a57a5d4a4msh36cc65d9cf1786ap11ced0jsnc5310f4696cf");
                    headers.set("X-RapidAPI-Host", "imdb8.p.rapidapi.com");
                })
                // .clientConnector(new ReactorClientHttpConnector((HttpClient.create()
                // .attr(AttributeKey.valueOf("X-RapidAPI-Key"),
                // "8a57a5d4a4msh36cc65d9cf1786ap11ced0jsnc5310f4696cf")
                // .attr(AttributeKey.valueOf("X-RapidAPI-Host"), "imdb8.p.rapidapi.com"))))
                .build();
    }
    RapidApis() {

    }

    // public static void getCrew(Model model, String imdbCode) {
    //     if (imdbCode == null)
    //         return;
    //     String topcrew = wb.get()
    //             .uri("https://imdb8.p.rapidapi.com/title/get-top-crew?tconst=tt0944947")
    //             .retrieve()
    //             .bodyToMono(String.class)
    //             .block();
    //     System.out.println(topcrew);
    // }

    // public static void getReviews(Model model, String imdbCode) {
    //     if (imdbCode == null)
    //         return;

    // }

    // public static void getImages(Model model, String imdbCode) {
    //     if (imdbCode == null)
    //         return;

    //     String images = wb.get()
    //             .uri("https://imdb8.p.rapidapi.com/title/get-images?tconst=tt0944947&limit=5")
    //             .retrieve()
    //             .bodyToMono(String.class)
    //             .block();
    //     System.out.println(images);
    // }

    // public static void getCast(Model model, String imdbCode) {
    //     if (imdbCode == null)
    //         return;

    //     String cast = wb.get()
    //             .uri("https://imdb8.p.rapidapi.com/title/get-top-cast?tconst=tt0944947")
    //             .retrieve()
    //             .bodyToMono(String.class)
    //             .block();
    //     System.out.println(cast);
    // }
}

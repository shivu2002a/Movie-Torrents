package com.shivu.yifyapp.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;

import com.shivu.yifyapp.Models.MovieDetails.Crew;

import reactor.core.publisher.Mono;

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
                .build();
    }

    RapidApis() {

    }

    // public static void main(String[] args) {
    //     getCrew(null, "tt0137523");
    // }

    public static void getCrew(Model model, String imdbCode) {
        if(imdbCode == null)
            return;
        Mono<String> monocrew = wb.get()
                .uri("https://imdb8.p.rapidapi.com/title/get-top-crew?tconst=" + imdbCode)
                .retrieve()
                .bodyToMono(String.class);
                
        String topcrew = monocrew.block();
        System.out.println(topcrew);
        JSONObject json = new JSONObject(topcrew); 
        JSONObject jdirs = json.getJSONArray("directors").getJSONObject(0);
        JSONObject jwriters = json.getJSONArray("writers").getJSONObject(0);
        Crew crew = new Crew();

        crew.d_name = jdirs.getString("name");
        crew.d_category = jdirs.getString("category");
        crew.d_image = jdirs.getJSONObject("image").getString("url");
        
        crew.w_name = jwriters.getString("name");
        crew.w_category = jwriters.getString("category");
        crew.w_job = jwriters.optString("job");
        crew.w_image = jwriters.getJSONObject("image").getString("url");
        
        // model.addAttribute("crew", crew);
        System.out.println("\n\n" + crew);
    }

    public static void getReviews(Model model, String imdbCode) {
        if (imdbCode == null)
            return;
        String images = wb.get()
                .uri("https://imdb8.p.rapidapi.com/title/get-images?tconst=" + imdbCode + "&limit=5")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        JSONObject j = new JSONObject(images);
        JSONArray img = j.getJSONArray("images");
        for (int i = 0; (i < 5) && i < img.length() ; i++) {
            
        }

        System.out.println("\n\n" + images);

    }

    public static void getImages(Model model, String imdbCode) {
        if (imdbCode == null)
            return;

        String images = wb.get()
                .uri("https://imdb8.p.rapidapi.com/title/get-images?tconst=" + imdbCode + "&limit=5")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        System.out.println(images);
    }

    public static void getCast(Model model, String imdbCode) {
        if (imdbCode == null)
            return;
        String cast = wb.get()
                .uri("https://imdb8.p.rapidapi.com/title/get-top-cast?tconst=" + imdbCode)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(cast);
    }
}

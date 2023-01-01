package com.shivu.yifyapp.Models.MovieDetails;

import java.util.List;

import com.shivu.yifyapp.Models.Search.MovieDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtendedMovieDetails extends MovieDetails {

    public String language;
    public String title_long;
    public int rating;
    public int runtime;
    public List<String> genres;

    public String yt_trailer_code;
    public String summary;
    public String description_full;
    public int download_count;
    public String imdb_code;
    public String large_cover_image;
    public String background_image;

}

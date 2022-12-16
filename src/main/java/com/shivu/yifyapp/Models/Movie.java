package com.shivu.yifyapp.Models;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Movie {

    public int movie_count;

    public int limit;
    
    public int page_number;

    public List<MovieDetails> movies;
    
}

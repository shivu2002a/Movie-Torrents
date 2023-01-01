package com.shivu.yifyapp.Models.Search;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Movie {

    @JsonInclude(Include.NON_ABSENT)
    public int movie_count;

    @JsonInclude(Include.NON_ABSENT)
    public int limit;
    
    @JsonInclude(Include.NON_ABSENT)
    public int page_number;

    public List<MovieDetails> movies;
    
}

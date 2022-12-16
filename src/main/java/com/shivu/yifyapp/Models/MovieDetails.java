package com.shivu.yifyapp.Models;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class MovieDetails {

    public String title;

    public String title_long;

    public int year;
    public int rating;
    public int runtime;
    public List<String> genres;

    public String summary;

    public List<Torrents> torrents;

    
    
}

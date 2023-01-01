package com.shivu.yifyapp.Models.Search;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class MovieDetails {

    private String title;
    private int id;
    private String medium_cover_image;
    private int year;
    
    private List<Torrents> torrents;              

}

package com.shivu.yifyapp.Models.MovieDetails;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class SearchMovie {

    public String status;

    public String status_message;

    public Movie data;
}

package com.shivu.yifyapp.Models.MovieDetails;

import java.util.List;

import lombok.ToString;

@ToString
public class Crew {

    public String d_name;
    public String d_category;
    public String d_image;

    public String w_name;
    public String w_category;
    public String w_job;
    public String w_image;

    public List<String> images;

}

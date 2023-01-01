package com.shivu.yifyapp.Models.Search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Torrents {

    @JsonIgnore
    public String url;

    public String hash;
    public String quality;
    public String type;
    public String size;
    public String magnetTorrent;

}

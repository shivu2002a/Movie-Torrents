package com.shivu.yifyapp.Controllers;

import java.net.InetAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.shivu.yifyapp.Models.Search;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Controller
@CrossOrigin
public class Home {
    
    public final String BASE = "https://yts-mx.translate.goog/api/v2/";
    
    public final String END = "&_x_tr_sl=auto&_x_tr_tl=en&_x_tr_hl=en&_x_tr_pto=wapp";
    
    private WebClient webClient;
    
    @Bean
    public WebClient buildClient(WebClient.Builder wBuilder){

        this.webClient = wBuilder
                                .exchangeStrategies(ExchangeStrategies.builder()
                                                    .codecs(configurer -> configurer 
                                                            .defaultCodecs()
                                                            .maxInMemorySize(16 *1024 * 1024))
                                                    .build())
                                .baseUrl(BASE)
                                .clientConnector(
                                                new ReactorClientHttpConnector(
                                                            HttpClient.create().proxyWithSystemProperties()))
                                .build();
        return webClient;

    }
    
    @RequestMapping("/")
    public String home() {
        return "Home";
    }

    public static void magnetTorrent(Search se){
        se.data.movies.stream().forEach(md -> {
            md.torrents.stream().forEach(to -> {
                to.magnetTorrent = "magnet:?xt=urn:btih:" + to.hash;
            });
        });
    }


}

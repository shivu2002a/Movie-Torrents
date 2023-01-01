package com.shivu.yifyapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YifyAppApplicationTests {

	@Test
	void contextLoads() throws IOException {
		//Given
		String jsonType = "text/html";
		//Request
		HttpUriRequest req = new HttpGet("http://localhost:8082/movies");
		//Response
		HttpResponse res = HttpClientBuilder.create().build().execute(req);
		String mimeType = ContentType.getOrDefault(res.getEntity()).getMimeType();
		//Assert
		assertEquals(jsonType, mimeType);
	}

	@Test
	void searchMovieById() throws IOException {
		//Given
		HttpUriRequest req = new HttpGet("http://localhost:8082/movies?movie_id=" + String.valueOf(new Random().nextInt(65000) + 1));
		HttpResponse res = HttpClientBuilder.create().build().execute(req);
		assertEquals(HttpStatus.SC_OK, res.getStatusLine().getStatusCode());
	}

	


}

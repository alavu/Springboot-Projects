package com.alavu.postalapi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class PostalapiApplication {
	private static final String POST_API_URL= "https://api.postalpincode.in/pincode/110001";
	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(PostalapiApplication.class, args);

		HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POST_API_URL))
                .build();

    HttpResponse<String> response  = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("The resoonse is : "+response.body());

    String Str = response.body();
    BufferedWriter writer = new BufferedWriter(new FileWriter("tst.csv"));
    writer.write(Str);
    
    writer.close();
     
    //Parse JSON into object    
    ObjectMapper mapper = new ObjectMapper();
    List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>(){});
	posts.forEach(System.out::println);
	}

}

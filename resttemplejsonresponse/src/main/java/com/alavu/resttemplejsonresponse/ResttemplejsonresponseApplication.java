package com.alavu.resttemplejsonresponse;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ResttemplejsonresponseApplication {

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(ResttemplejsonresponseApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
	// 	String response =  restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
	// 	System.out.println(response);

		//Method - 2

		/* 
		RestTemplate restTemplate = new RestTemplate();
		List response =  restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);
		response.forEach(post-> {
			System.out.println(post);
			System.out.println("");
		}); */

			//Method - 3
		
		/*RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity =  restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", String.class);
		String body = responseEntity.getBody();
		HttpHeaders headers = responseEntity.getHeaders();
		System.out.println(body);
		System.out.println(headers);
		*/
		
			//Method - 4- only getting the specific user details
			/* 
			ResponseEntity<Post> postEntity =  restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", Post.class);
			Post post = postEntity.getBody();
			System.out.println(post.getBody());
			*/

			//Method - 5- Using resttemplete.exchange

			RequestEntity request = new RequestEntity<>(HttpMethod.GET, new URI("https://jsonplaceholder.typicode.com/posts/1"));
			ResponseEntity<Post> postEntity = restTemplate.exchange(request, Post.class);
			Post post = postEntity.getBody();
			System.out.println(post.getUserId());
			System.out.println(post);
			
	}

}

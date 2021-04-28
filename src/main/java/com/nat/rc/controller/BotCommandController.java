package com.nat.rc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nat.rc.model.UserRatingModel;
import com.nat.rc.repository.QuoteRatingRepository;
import com.nat.rc.ui.Quote;
import com.nat.rc.ui.UserQuote;

@RestController
@RequestMapping("/api/v1")
public class BotCommandController {

	@Autowired
	private QuoteRatingRepository qrRepository;
	@Value("${robo.url}")
	private String robatUrl;
	
	@Value("${ds.url}")
	private String dsURL;
	
	private RestTemplate restTemplate=new RestTemplate();
	
	@GetMapping("/quotes/{userId}")
	public ResponseEntity<Map<String,Object>> listPastReviews(@PathVariable(value="userId") String userId, Pageable pageable){
		Page<UserRatingModel> data=qrRepository.findAll(pageable);
		Map<String,Object> response=new HashMap<String, Object>();
		response.put("pastQuotes", data);
		response.put("currPage", data.getNumber());		
		response.put("totalReviews", data.getTotalElements());	
		response.put("totalPages", data.getTotalPages());	
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	@PostMapping("/reviews")
	public ResponseEntity<Map<String,Object>> saveReview(@RequestBody UserQuote userQuote){
		UserRatingModel model=UserQuote.createUserRating(userQuote);
		qrRepository.save(model);
		Map<String,Object> response=new HashMap<String, Object>();
		response.put("quote", getNextQuote(userQuote));
		response.put("message", "Quote rated successfully");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/quotes")
	public ResponseEntity<Quote> nextQuote(@RequestBody UserQuote userQuote){
		return new ResponseEntity<>(getNextQuote(userQuote),HttpStatus.OK);
		
	}
	
	@GetMapping("/images/{userId}")
	public ResponseEntity<Map<String,Object>> getImage(@PathVariable(value="userId") String userId){
		Map<String,Object> response=new HashMap<String, Object>();
		response.put("imagesrc", robatUrl+userId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	private Quote getNextQuote(UserQuote userQuote) {
		
		String url=dsURL;
		//Process the user information for DS service 
		
		//Set some get headers applicable based on agreement with DS service
		HttpHeaders headers=new HttpHeaders();
		HttpEntity<String> entity=new HttpEntity<>(headers);
		ResponseEntity<String> resp=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
		return parseQuoteString(resp.getBody());
	}
	
	
	private Quote parseQuoteString(String str) {
		Quote quote=null;
		ObjectMapper mapper=new ObjectMapper();
		try {
			JsonNode root=mapper.readTree(str);
			root=root.get(0);
			quote=new Quote();
			JsonNode idNode=root.path("id");
			quote.setQuoteId(idNode.asText());
			JsonNode titleNode=root.path("title");
			quote.setAuthor(titleNode.path("rendered").asText());
			JsonNode quoteNode=root.path("content");
			quote.setQuote(quoteNode.path("rendered").asText());			
		} catch (JsonProcessingException e) {
			
		}
		return quote;
	}
}

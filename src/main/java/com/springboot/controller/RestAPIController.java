package com.springboot.controller;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.springboot.model.Fibonacci;
import com.springboot.model.ReverseWord;
import com.springboot.model.TriangleType;
/**
 * 
 * @author Prashant_Barhate
 * @PathVariable - gets value from specified path
 *
 *
 */

@RestController
@RequestMapping("/api")
public class RestAPIController{

	public static final Logger logger = Logger.getLogger(RestAPIController.class.getName());
	public static final String NO_CACHE = "no-cache";
	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	
	@RequestMapping(value = "/Fibbonacci", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> getFibboSeries(@RequestParam() String n){
		
		HttpHeaders responseHeaders = new HttpHeaders();
		Long number;
		
		try {
		  number = Long.parseLong(n);
		} catch (NumberFormatException e) {
			
			logger.error("Number Format Exception, Please check input parameter");			
		
			return new ResponseEntity<>("Please Enter Integer Number", responseHeaders, HttpStatus.BAD_REQUEST);
			
		}
		
		if(number<=0) {
			logger.info("Invalid Number -->" +n);
				
			return new ResponseEntity<>("Invalid Number", responseHeaders, HttpStatus.BAD_REQUEST);
		}
		
		Fibonacci fibo = new Fibonacci() ;
		Long indexnumber = fibo.getFiboSequence(number);
		
		setResponseHeader(responseHeaders);
		
		logger.info("Index Number ->"+indexnumber);
		return new ResponseEntity<>(indexnumber.toString(),responseHeaders, HttpStatus.OK);
	}
	
	/**
	 * Reverse of String
	 */
	@RequestMapping(value = "/ReverseWords", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> getReverseString(@RequestParam("sentence") String s,Model model){
		
		model.addAttribute("sentence", s);
		HttpHeaders responseHeaders = new HttpHeaders();
		if(s == null) {
			logger.error("Invalid String");
			
			return new ResponseEntity<>("Invalid String", responseHeaders, HttpStatus.BAD_REQUEST);
		}
		ReverseWord r = new ReverseWord();
		String reverse = r.getReverseString(s);
		
		setResponseHeader(responseHeaders);
		return new ResponseEntity<>(reverse,responseHeaders, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Triangle Type
	 * 
	 */
	@RequestMapping(value = "/TriangleType", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> getTriangleType(@RequestParam("a") Integer a, @RequestParam("b") Integer b, @RequestParam("c") Integer c, Model model){
		
		HttpHeaders responseHeaders = new HttpHeaders();
		model.addAttribute("side1", a);
		model.addAttribute("side2", a);
		model.addAttribute("side3", a);
		if(a <=0 || b<=0 || c<=0) {
			logger.error("Invalid Parameters");
			return new ResponseEntity<>("Invalid Parameters", responseHeaders, HttpStatus.BAD_REQUEST);
		}
		
		TriangleType t = new TriangleType();
		String type = t.checkTriangleType(a, b, c);
		
		setResponseHeader(responseHeaders);
		
		logger.info("Type of Triangle -->"+ type);
		
		return new ResponseEntity<>(type,responseHeaders, HttpStatus.OK);
	}
	
	/**
	 * Sort Array
	 *  
	 */
	@RequestMapping(value = "/makeonearray", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=UTF-8")
	public ResponseEntity<ArrayList<Integer>> createArray(@RequestBody Map<String,List<Integer>> json, UriComponentsBuilder ucBuilder){
		
		//Used to store Arrays
		List<List<Integer>> twoDim = new ArrayList<>();
		
		//To store individual Array
		ArrayList<Integer> list = new ArrayList<>();
		
		//Get each array entry in list  
		for (Map.Entry<String, List<Integer>> entry : json.entrySet()) {
		    logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    twoDim.add(entry.getValue());
		    		    
		}
		int size = twoDim.size();
		//Store each element from array to list
		for (int i=0 ; i<size;i++) {
			Iterator<Integer> iterator = twoDim.get(i).iterator();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		}
		//Sort list
		Collections.sort(list);
		
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			logger.info("Sorted List -->"+iterator.next());
		}
				
		// Remove Duplicates
		ArrayList<Integer> nonDupList = new ArrayList<>();
        Iterator<Integer> dupIter = list.iterator();
        while(dupIter.hasNext())
        {
	        int dupNumber = dupIter.next();
	        if(nonDupList.contains(dupNumber))
	        {
	            dupIter.remove();
	        }else
	        {
	            nonDupList.add(dupNumber);
	        }
        }
        logger.info("Unique List -->"+nonDupList);
		HttpHeaders responseHeaders = new HttpHeaders();
		setResponseHeader(responseHeaders);
		
		return new ResponseEntity<>(nonDupList,responseHeaders, HttpStatus.OK);
	}
	
	public void setResponseHeader(HttpHeaders responseHeaders) {
		responseHeaders.setCacheControl(NO_CACHE);
		responseHeaders.setPragma(NO_CACHE);
		responseHeaders.set(HttpHeaders.EXPIRES, "-1");
		responseHeaders.set(HttpHeaders.VARY, ACCEPT_ENCODING);
		
	}
	
}

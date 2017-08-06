package com.capgemini.chess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.chess.service.UserProfileSearchService;
import com.capgemini.chess.service.to.UserProfileTO;

@RestController
@RequestMapping("/search")
public class SearchRestController {

	@Autowired
	UserProfileSearchService userProfileSearchService;
	
	@RequestMapping(value = "/getByName", method = RequestMethod.GET, produces = "application/json")
	 List<UserProfileTO> getUsersByName(@RequestParam("name") String name) {
			
		 return userProfileSearchService.getUserProfilesByName(name);
	 }
	
	@RequestMapping(value = "/getBySurname", method = RequestMethod.GET, produces = "application/json")
	 List<UserProfileTO> getUsersBySurname(@RequestParam("surname") String surname) {
			
		 return userProfileSearchService.getUserProfilesBySurname(surname);
	 }
	
	@RequestMapping(value = "/getByNameSurname", method = RequestMethod.GET, produces = "application/json")
	 List<UserProfileTO> getUsersBySurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
			
		 return userProfileSearchService.getUserProfilesByNameAndSurname(name, surname);
	 }
	
}

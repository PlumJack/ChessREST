package com.capgemini.chess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.chess.dataaccess.enums.GameResult;
import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.UserServiceFacade;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

@RestController
@RequestMapping("/chess")
public class ChessRestController {

	@Autowired
	UserServiceFacade userServiceFacade;
	
	 @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
	public UserStatsTO getStats(@RequestParam("login") String login) throws UserProfileValidationException{
		 return userServiceFacade.getStats(login);
	}
	
	 @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	 void updateUserProfile(@RequestBody UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException {
		 userServiceFacade.updateUserProfile(userUpdateTO);
	 }
	 
	 @RequestMapping(value = "/registerMatch", method = RequestMethod.POST, consumes = "application/json")
	 void registerMatch(@RequestBody MatchTO matchTO) throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		 userServiceFacade.registerNewMatch(matchTO);
	 }
	 
	 @RequestMapping(value = "/registerMatch2", method = RequestMethod.GET)
	 void registerMatch2() throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		 MatchTO match = new MatchTO();
		 match.setHostPlayerId(1L);
		 match.setGuestPlayerId(2L);
		 match.setGameResult(GameResult.HOST_WON);
		 userServiceFacade.registerNewMatch(match);
	 }
	 
	 
	 @RequestMapping(value = "/upd", method = RequestMethod.GET, produces = "application/json")
	 UserUpdateTO upd(@RequestParam("i") int i, @RequestParam("il") int il, @RequestParam("io") int io) throws UserProfileValidationException, InvalidPasswordException {
		 return createUserUpdateTO(i, il, io);
	 }
	 
	 @RequestMapping(value = "/getMatch", method = RequestMethod.GET, produces = "application/json")
	 MatchTO getMatch() {
		 MatchTO match = new MatchTO();
		 match.setHostPlayerId(1L);
		 match.setGuestPlayerId(2L);
		 match.setGameResult(GameResult.HOST_WON);
		 return match;
	 }
	 
	 private UserUpdateTO createUserUpdateTO(int i, int iLogin, int iOldPassword){
			UserUpdateTO userUpdateTO = new UserUpdateTO();
			
			userUpdateTO.setLogin("login" + iLogin);
			userUpdateTO.setOldPassword("password" + iOldPassword);
			userUpdateTO.setNewPassword("password" + i);
			userUpdateTO.setName("name" + i);
			userUpdateTO.setSurname("surname" + i);
			userUpdateTO.setEmail("email" + i + "@email.pl");
			userUpdateTO.setAboutMe("aboutMe" + i);
			userUpdateTO.setLifeMotto("lifeMotto" + i);
			
			return userUpdateTO;
		}
	 
}

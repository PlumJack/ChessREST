package com.capgemini.chess.service;

import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

public interface UserServiceFacade {
	
	UserStatsTO getStats(String login) throws UserProfileValidationException;
	void updateUserProfile(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException;
	void registerNewMatch(MatchTO matchTO) throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException;

}

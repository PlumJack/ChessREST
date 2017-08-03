package com.capgemini.chess.service;

import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.to.MatchTO;

public interface MatchRegistrationService {

	void registerNewMatch(MatchTO matchTO) throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException;
	
}

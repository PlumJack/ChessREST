package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.to.UserStatsTO;

public interface UserStatsReaderService {

	public UserStatsTO getStats(String login) throws UserProfileValidationException;
	public UserStatsTO getStats(Long id) throws UserProfileValidationException;
	
}

package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.to.MatchTO;

public interface UserStatsUpdateService {
	
	void updateStats(MatchTO matchto) throws UserProfileValidationException;

}

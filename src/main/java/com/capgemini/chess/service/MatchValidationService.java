package com.capgemini.chess.service;

import com.capgemini.chess.exception.MatchValidationException;

public interface MatchValidationService {
	
	void validateMatch(Long id) throws MatchValidationException;

}

package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserProfileValidationException;

public interface UserProfileValidationService {	
	
	void validateId(Long id) throws UserProfileValidationException;
	void validateLogin(String login) throws UserProfileValidationException;

}

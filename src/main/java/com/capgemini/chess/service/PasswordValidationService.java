package com.capgemini.chess.service;

import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.service.to.UserUpdateTO;

public interface PasswordValidationService {

	void validatePassword(UserUpdateTO userUpdateTO) throws InvalidPasswordException;
	
}
